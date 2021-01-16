package io.github.mp3cloud.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.mp3cloud.entity.User;
import io.github.mp3cloud.entity.UserType;
import io.github.mp3cloud.repository.IUserTypeRepository;
import io.github.mp3cloud.security.UserSercurity;
import io.github.mp3cloud.dto.UserDTO;

@Component
public class UserConvert {

	@Autowired
	private IUserTypeRepository userTypeRepository;

	public User toEntity(UserDTO dto) {
		User user = new User();
		UserType convert = userTypeRepository.getOne(dto.getUserType().getId());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setGender(dto.getGender());
		user.setUserName(dto.getUserName());
		user.setBirthDay(dto.getBirthDay());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setActivityStatus(dto.isActivityStatus());
		user.setResetPasswordToken(dto.getResetPasswordToken());
		user.setUserType(convert);
		return user;
	}

	public UserDTO toDTO(User entity) {
		UserDTO dto = new UserDTO();
		UserTypeConvert convert = new UserTypeConvert();
		if (entity != null) {
			if (entity.getId() != 0)
				dto.setId(entity.getId());
			dto.setUserName(entity.getUserName());
			dto.setFirstName(entity.getFirstName());
			dto.setLastName(entity.getLastName());
			dto.setGender(entity.getGender());
			dto.setBirthDay(entity.getBirthDay());
			dto.setEmail(entity.getEmail());
			dto.setPassword(entity.getPassword());
			dto.setActivityStatus(entity.isActivityStatus());
			dto.setResetPasswordToken(entity.getResetPasswordToken());
			dto.setUserType(convert.toDTO(entity.getUserType()));
			return dto;
		} else {
			return null;
		}
	}

	public User toEntity(UserDTO dto, User entity) {
		UserTypeConvert convert = new UserTypeConvert();
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setUserName(dto.getUserName());
		entity.setGender(dto.getGender());
		entity.setBirthDay(dto.getBirthDay());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setActivityStatus(dto.isActivityStatus());
		entity.setUserType(convert.toEntity(dto.getUserType()));
		entity.setResetPasswordToken(dto.getResetPasswordToken());
		return entity;
	}

	public UserDTO toDTO(UserSercurity myUser) {
		UserDTO dto = new UserDTO();
		UserTypeConvert convert = new UserTypeConvert();
		dto.setUserName(myUser.getUsername());
		dto.setFirstName(myUser.getDto().getFirstName());
		dto.setLastName(myUser.getDto().getLastName());
		dto.setGender(myUser.getDto().getGender());
		dto.setBirthDay(myUser.getDto().getBirthDay());
		dto.setEmail(myUser.getDto().getEmail());
		dto.setPassword(myUser.getPassword());
		dto.setActivityStatus(myUser.getDto().isActivityStatus());
		dto.setUserType(convert.toDTO(myUser.getDto().getUserType()));
		dto.setResetPasswordToken(myUser.getDto().getResetPasswordToken());
		return dto;
	}

}
