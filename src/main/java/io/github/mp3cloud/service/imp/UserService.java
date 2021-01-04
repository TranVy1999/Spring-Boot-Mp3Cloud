package io.github.mp3cloud.service.imp;

import java.util.ArrayList;
import java.util.Collection;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.mp3cloud.convert.UserConvert;
import io.github.mp3cloud.entity.User;
import io.github.mp3cloud.repository.IUserRepository;
import io.github.mp3cloud.service.IUserService;
import io.github.mp3cloud.dto.UserDTO;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserConvert userConvert;

	@Autowired
	private IUserRepository userReposity;

	@Override
	public Collection<UserDTO> getAll(Pageable pageable) {
		Collection<UserDTO> list = new ArrayList<UserDTO>();
		Collection<User> listUser = userReposity.findAll(pageable).getContent();
		for (User user : listUser) {
			list.add(userConvert.toDTO(user));
		}
		return list;
	}

	@Override
	public UserDTO getById(long id) {
		User user = userReposity.findById(id).get();
		return userConvert.toDTO(user);
	}

	@Override
	public UserDTO save(UserDTO newDTO) {
		User user = new User();
		if (newDTO.getId() != 0) {
			User oldUser = userReposity.findById(newDTO.getId()).get();
			user = userConvert.toEntity(newDTO, oldUser);
		} else {
			user = userConvert.toEntity(newDTO);
		}
		user = userReposity.save(user);
		return userConvert.toDTO(user);
	}

	@Override
	public void delete(long id) {
		userReposity.deleteById(id);
	}

	@Override
	public UserDTO findByUserName(String username) {
		User user = new User();
		user = userReposity.findByUserName(username);
		return userConvert.toDTO(user);
	}

	@Override
	public UserDTO findByEmail(String email) {
		User user = new User();
		user = userReposity.findByEmail(email);
		return userConvert.toDTO(user);
	}

	@Override
	public int totalItem() {
		return (int) userReposity.count();
	}

	@Override
	public void updateResetPasswordToken(String token, String email) throws AccountNotFoundException {
		User customer = userReposity.findByEmail(email);
		if (customer != null) {
			customer.setResetPasswordToken(token);
			userReposity.save(customer);
		} else {
			throw new AccountNotFoundException("Could not find any customer with the email " + email);
		}
	}

	@Override
	public UserDTO getByResetPasswordToken(String token) {
		return userConvert.toDTO(userReposity.findByResetPasswordToken(token));
	}

	@Override
	public void updatePassword(UserDTO user, String newPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(newPassword);
		user.setPassword(encodedPassword);
		user.setResetPasswordToken(null);
		save(user);
	}
}
