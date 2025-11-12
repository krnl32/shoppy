package com.krnl32.shoppy.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class ProfileSecurityRules implements SecurityRules {
	@Override
	public void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
		registry
			.requestMatchers(HttpMethod.GET, "/api/products/**").permitAll();
	}
}
