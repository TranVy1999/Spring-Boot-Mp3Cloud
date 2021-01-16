package io.github.mp3cloud.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.github.mp3cloud.convert.UserConvert;
import io.github.mp3cloud.service.IUserService;
import io.github.mp3cloud.dto.UserDTO;

@Component
public class CustomUser implements UserDetailsService {

	@Autowired
	private IUserService userService;

	@Autowired
	private UserConvert userCovert;

	// add context-can bas-packet
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO user = userService.findByUserName(username);
		if (user == null)
			new UsernameNotFoundException("user not found");
		List<GrantedAuthority> authortity = new ArrayList<GrantedAuthority>();
		authortity.add(new SimpleGrantedAuthority(user.getUserType().getUserPosition()));
		UserSercurity myUser = new UserSercurity(user.getUserName(), user.getPassword(), authortity, user);
		myUser.getDto().setEmail(user.getEmail());
		myUser.getDto().setFirstName(user.getFirstName());
		myUser.getDto().setLastName(user.getLastName());
		myUser.getDto().setId(user.getId());
		myUser.getDto().setGender(user.getGender());
		myUser.getDto().setBirthDay(user.getBirthDay());
		return myUser;
	}

	public UserDetails loadUserById(long id) {
		UserDTO user = userService.getById(id);
		if (user == null)
			new UsernameNotFoundException("user not found");
		Collection<GrantedAuthority> authortity = new ArrayList<GrantedAuthority>();
		authortity.add(new SimpleGrantedAuthority(user.getUserType().getUserPosition()));
		UserSercurity myUser = new UserSercurity(user.getUserName(), user.getPassword(), authortity, user);
		myUser.getDto().setEmail(user.getEmail());
		myUser.getDto().setFirstName(user.getFirstName());
		myUser.getDto().setLastName(user.getLastName());
		myUser.getDto().setId(user.getId());
		myUser.getDto().setGender(user.getGender());
		myUser.getDto().setBirthDay(user.getBirthDay());
		return myUser;
	}

	public String getPrincipleName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	public boolean existsByUsername(String userName) {
		if (userService.findByUserName(userName) != null)
			return true;

		return false;
	}

	public boolean existsByEmail(String email) {
		if (userService.findByEmail(email) != null)
			return true;
		return false;
	}

	public void save(UserSercurity myUser) {
		UserDTO dto = new UserDTO();
		dto = userCovert.toDTO(myUser);
		System.out.println(dto.getUserType().getId() + " id");
		userService.save(dto);
	}

//	public UserSercurity loadUserByUserName(String userName) {
//		UserDTO dto = new UserDTO();
//		dto = userService.findByUserName(userName);
//
//		return userCovert.toSecure(dto);
//	}

}
