package io.github.mp3cloud.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import io.github.mp3cloud.dto.UserDTO;

public class UserSercurity extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDTO dto;

	public UserSercurity(String username, String password, Collection<? extends GrantedAuthority> authorities,
			UserDTO dto) {
		super(username, password, authorities);
		this.dto = dto;
	}

	public UserDTO getDto() {
		return dto;
	}

	public void setDto(UserDTO dto) {
		this.dto = dto;
	}

}
