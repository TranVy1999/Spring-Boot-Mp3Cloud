package io.github.mp3cloud.security;

import java.util.Date;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTTokenProvider {

	@Autowired
	private Cache cache;
	
	// Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
	private final String JWT_SECRET = "mp3Cloud_Music";

	// Thời gian có hiệu lực của chuỗi jwt
	private final long JWT_EXPIRATION = 604800000L;

	Logger logger;

	// Tạo ra jwt từ thông tin user
	public String generateToken(UserSercurity user) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
		// Tạo chuỗi json web token từ id của user.
		return Jwts.builder().setSubject(Long.toString(user.getDto().getId())).setIssuedAt(now)
				.setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
	}

	public String getUserNameFromJWT(String token) {
		return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject();
	}
	
	public Date getTimeExpireFromJWT(String token) {
		return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getExpiration();
	}


	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
			return cache.checkTokenExpire(authToken);
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature -> Message: {} ", e);
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token -> Message: {}", e);
		} catch (ExpiredJwtException e) {
			logger.error("Expired JWT token -> Message: {}", e);
		} catch (UnsupportedJwtException e) {
			logger.error("Unsupported JWT token -> Message: {}", e);
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty -> Message: {}", e);
		}
		return false;
	}

}
