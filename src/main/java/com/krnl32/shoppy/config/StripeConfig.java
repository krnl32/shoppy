package com.krnl32.shoppy.config;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class StripeConfig {
	@Value("${app.payment.stripe.secret-key}")
	private String secretKey;

	@Value("${app.payment.stripe.webhook-secret-key}")
	private String webhookSecretKey;

	@PostConstruct
	public void setup() {
		Stripe.apiKey = secretKey;
	}
}
