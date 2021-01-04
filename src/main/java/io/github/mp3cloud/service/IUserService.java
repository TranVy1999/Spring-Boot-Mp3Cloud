package io.github.mp3cloud.service;

import java.util.Collection;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.data.domain.Pageable;

import io.github.mp3cloud.dto.UserDTO;

public interface IUserService {

	Collection<UserDTO> getAll(Pageable pageable);

	UserDTO getById(long id);

	UserDTO save(UserDTO newDTO);

	void delete(long ids);

	UserDTO findByUserName(String username);

	UserDTO findByEmail(String email);

	int totalItem();

	void updateResetPasswordToken(String token, String email) throws AccountNotFoundException;

	UserDTO getByResetPasswordToken(String token);

	void updatePassword(UserDTO user, String newPassword);

}
