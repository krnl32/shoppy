package com.krnl32.shoppy.security;

import com.krnl32.shoppy.entity.Role;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class CategorySecurityRules implements SecurityRules {
	@Override
	public void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
		registry
			.requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
			.requestMatchers(HttpMethod.POST, "/api/categories/**").hasRole(Role.ADMIN.name())
			.requestMatchers(HttpMethod.PUT, "/api/categories/**").hasRole(Role.ADMIN.name())
			.requestMatchers(HttpMethod.PATCH, "/api/categories/**").hasRole(Role.ADMIN.name())
			.requestMatchers(HttpMethod.DELETE, "/api/categories/**").hasRole(Role.ADMIN.name());
	}
}
