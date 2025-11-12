package com.krnl32.shoppy.service;

import com.krnl32.shoppy.config.StripeConfig;
import com.krnl32.shoppy.dto.checkout.PaymentResultDTO;
import com.krnl32.shoppy.dto.checkout.WebhookRequestDTO;
import com.krnl32.shoppy.entity.Order;
import com.krnl32.shoppy.entity.PaymentStatus;
import com.krnl32.shoppy.exception.PaymentException;
import com.krnl32.shoppy.type.CheckoutSession;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StripePaymentService implements PaymentService {
	private final StripeConfig stripeConfig;

	@Value("${app.web.url}")
	private String websiteURL;

	@Value("${app.payment.url.success-path}")
	private String successPath;

	@Value("${app.payment.url.cancel-path}")
	private String cancelPath;

	@Override
	public CheckoutSession createCheckoutSession(Order order) {
		try {
			var paymentIntentData = SessionCreateParams.PaymentIntentData.builder()
				.putMetadata("order_id", order.getId().toString()).build();

			var builder = SessionCreateParams.builder()
				.setMode(SessionCreateParams.Mode.PAYMENT)
				.setSuccessUrl(websiteURL + successPath + "?orderId=" + order.getId())
				.setCancelUrl(websiteURL + cancelPath)
				.setPaymentIntentData(paymentIntentData);

			order.getItems().forEach(orderItem -> {
				var productData = SessionCreateParams.LineItem.PriceData.ProductData.builder()
					.setName(orderItem.getProduct().getName()).build();

				var unitPrice = BigDecimal.valueOf(orderItem.getUnitPrice()).multiply(BigDecimal.valueOf(100));
				var priceData = SessionCreateParams.LineItem.PriceData.builder()
					.setCurrency("USD")
					.setUnitAmountDecimal(unitPrice)
					.setProductData(productData).build();

				var lineItem = SessionCreateParams.LineItem.builder()
					.setQuantity(Long.valueOf(orderItem.getQuantity()))
					.setPriceData(priceData).build();

				builder.addLineItem(lineItem);
			});

			var session = Session.create(builder.build());
			return new CheckoutSession(session.getUrl());
		} catch (StripeException ex) {
			throw new PaymentException("Payment Gateway Failed: " + ex.getMessage());
		}
	}

	@Override
	public Optional<PaymentResultDTO> handleWebhookRequest(WebhookRequestDTO webhookRequest) {
		try {
			String signature = webhookRequest.getHeaders().get("stripe-signature");

			Event event = Webhook.constructEvent(webhookRequest.getPayload(), signature, stripeConfig.getWebhookSecretKey());
			StripeObject stripeObject = event.getDataObjectDeserializer().getObject()
				.orElseThrow(() -> new PaymentException("Stripe Deserialization Failed"));

			switch (event.getType()) {
				case "payment_intent.succeeded" -> {
					PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
					Long orderId = Long.valueOf(paymentIntent.getMetadata().get("order_id"));
					return Optional.of(new PaymentResultDTO(orderId, PaymentStatus.PAID));
				}
				case "payment_intent.payment_failed" -> {
					PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
					Long orderId = Long.valueOf(paymentIntent.getMetadata().get("order_id"));
					return Optional.of(new PaymentResultDTO(orderId, PaymentStatus.FAILED));
				}
				default -> {
					return Optional.empty();
				}
			}
		} catch (SignatureVerificationException e) {
			throw new PaymentException("Invalid Stripe Signature");
		}
	}
}
