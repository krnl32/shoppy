package com.krnl32.shoppy.service;

import com.krnl32.shoppy.dto.checkout.CheckoutRequestDTO;
import com.krnl32.shoppy.dto.checkout.CheckoutResponseDTO;
import com.krnl32.shoppy.dto.checkout.WebhookRequestDTO;
import com.krnl32.shoppy.entity.Cart;
import com.krnl32.shoppy.entity.Order;
import com.krnl32.shoppy.entity.OrderItem;
import com.krnl32.shoppy.entity.PaymentStatus;
import com.krnl32.shoppy.exception.EmptyCartOrderException;
import com.krnl32.shoppy.exception.ResourceNotFoundException;
import com.krnl32.shoppy.repository.CartRepository;
import com.krnl32.shoppy.repository.OrderRepository;
import com.krnl32.shoppy.type.CheckoutSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {
	private final CartRepository cartRepository;
	private final OrderRepository orderRepository;
	private final CartService cartService;
	private final AuthService authService;
	private final PaymentService paymentService;

	@Override
	public CheckoutResponseDTO checkout(CheckoutRequestDTO checkoutRequest) {
		Cart cart = cartRepository.findById(checkoutRequest.getCartId())
			.orElseThrow(() -> new ResourceNotFoundException("Cart ID Not Found: " + checkoutRequest.getCartId()));

		if (cart.getItems().isEmpty()) {
			throw new EmptyCartOrderException("Cart ID is Empty: " + checkoutRequest.getCartId());
		}

		Order order = new Order();
		order.setCustomer(authService.getAuthenticatedUser());
		order.setPaymentStatus(PaymentStatus.PENDING);
		order.setTotalPrice(cart.getTotalPrice());

		cart.getItems().forEach(cartItem -> {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setUnitPrice(cartItem.getProduct().getPrice());
			orderItem.setTotalPrice(cartItem.getTotalPrice());

			order.getItems().add(orderItem);
		});

		Order savedOrder = orderRepository.save(order);

		CheckoutSession session = paymentService.createCheckoutSession(savedOrder);
		cartService.clearCart(checkoutRequest.getCartId());

		return new CheckoutResponseDTO(savedOrder.getId(), session.getCheckoutURL());
	}

	@Override
	public void handleWebhookRequest(WebhookRequestDTO webhookRequest) {
		paymentService.handleWebhookRequest(webhookRequest)
			.ifPresent(paymentResultDTO -> {
				Order order = orderRepository.findById(paymentResultDTO.getOrderId())
					.orElseThrow();
				order.setPaymentStatus(paymentResultDTO.getPaymentStatus());
				orderRepository.save(order);
			});
	}
}
