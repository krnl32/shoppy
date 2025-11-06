package com.krnl32.shoppy.type;

import com.krnl32.shoppy.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

import javax.crypto.SecretKey;
import java.util.Date;

@RequiredArgsConstructor
public class Jwt {
	private final Claims claims;
	private final SecretKey secretKey;

	public boolean isExpired() {
		return claims.getExpiration().before(new Date());
	}

	public Long getUserId() {
		return Long.valueOf(claims.getSubject());
	}

	public Role getUserRole() {
		return Role.valueOf(claims.get("role", String.class));
	}

	@Override
	public String toString() {
		return Jwts.builder()
			.claims(claims)
			.signWith(secretKey)
			.compact();
	}
}
