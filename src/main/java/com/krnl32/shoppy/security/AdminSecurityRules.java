package com.krnl32.shoppy.security;

import com.krnl32.shoppy.entity.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class AdminSecurityRules implements SecurityRules {
	@Override
	public void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
		registry
			.requestMatchers("/api/admin/**").hasRole(Role.ADMIN.name());
	}
}
