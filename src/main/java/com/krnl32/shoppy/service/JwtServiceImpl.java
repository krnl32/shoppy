package com.krnl32.shoppy.service;

import com.krnl32.shoppy.config.JwtConfig;
import com.krnl32.shoppy.entity.User;
import com.krnl32.shoppy.type.Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
	private final JwtConfig jwtConfig;

	@Override
	public Jwt generateAccessToken(User user) {
		return generateToken(user, jwtConfig.getAccessTokenExpiration());
	}

	@Override
	public Jwt generateRefreshToken(User user) {
		return generateToken(user, jwtConfig.getRefreshTokenExpiration());
	}

	@Override
	public Jwt parseToken(String token) {
		try {
			Claims claims = Jwts.parser()
				.verifyWith(jwtConfig.getSecretKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();

			return new Jwt(claims, jwtConfig.getSecretKey());
		} catch (JwtException ex) {
			return null;
		}
	}

	private Jwt generateToken(User user, long expirationSeconds) {
		Claims claims = Jwts.claims()
			.subject(user.getId().toString())
			.add("role", user.getRole())
			.issuedAt(new Date())
			.expiration(new Date(System.currentTimeMillis() + 1000 * expirationSeconds))
			.build();

		return new Jwt(claims, jwtConfig.getSecretKey());
	}
}
