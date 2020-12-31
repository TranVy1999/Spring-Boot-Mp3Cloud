package io.github.mp3cloud.security;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.jodah.expiringmap.ExpiringMap;

@Component
public class Cache {
		@Autowired
		private JWTTokenProvider jwtTokenProvider;
	
		private final ExpiringMap<String, String> exprireMap;
		
		public Cache() {
			this.exprireMap = ExpiringMap.builder().variableExpiration().build();
		}
		
		public void addTokenToExpireMap(String token) {
			long timeExpire = jwtTokenProvider.getTimeExpireFromJWT(token).toInstant().getEpochSecond();
			long realTime = new Date().toInstant().getEpochSecond();
			this.exprireMap.put(token, "ok", timeExpire - realTime,TimeUnit.SECONDS);
		}
		public boolean checkTokenExpire(String token) {
			return !this.exprireMap.containsKey(token);
		}
}
