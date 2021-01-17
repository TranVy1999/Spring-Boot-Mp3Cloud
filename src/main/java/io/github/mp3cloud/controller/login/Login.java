package io.github.mp3cloud.controller.login;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.github.mp3cloud.security.Cache;
import io.github.mp3cloud.security.CustomUser;
import io.github.mp3cloud.security.JWTTokenProvider;
import io.github.mp3cloud.security.UserSercurity;
import io.github.mp3cloud.dto.UserDTO;

@RestController
public class Login {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTTokenProvider tokenProvider;

	@Autowired
	private CustomUser customUser;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private Cache cache;

	@PostMapping("/signin")
	public ResponseEntity<String> authenticateUser(@Valid @RequestBody UserDTO user) {
		// Xác thực từ username và password.
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));

		// Nếu không xảy ra exception tức là thông tin hợp lệ
		// Set thông tin authentication vào Security Context
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.generateToken((UserSercurity) authentication.getPrincipal());

		// Trả về jwt cho người dùng.
		UserDetails currentUser = customUser.loadUserByUsername(user.getUserName());
		System.out.println(currentUser.getUsername() + " username");
		return ResponseEntity.ok(jwt);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO dto) {
		if (customUser.existsByUsername(dto.getUserName())) {
			return ResponseEntity.badRequest().body("Error: Username is already taken!");
		}

		if (customUser.existsByEmail(dto.getEmail())) {
			return ResponseEntity.badRequest().body("Error: Email is already in use!");
		}

		// Create new user's account
//		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
//				encoder.encode(signUpRequest.getPassword()));
		String position = "USER";
		List<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
		authority.add(new SimpleGrantedAuthority(position));
		UserSercurity myUser = new UserSercurity(dto.getUserName(), encoder.encode(dto.getPassword()), authority, dto);

		customUser.save(myUser);

		return ResponseEntity.ok("User registered successfully!");
	}

	@PostMapping("/signout")
	public ResponseEntity<?> Logout(@RequestHeader("Authorization") String token) {
		cache.addTokenToExpireMap(token.substring(7).trim());
		SecurityContextHolder.clearContext();
		return ResponseEntity.ok("ok");
	}

}
