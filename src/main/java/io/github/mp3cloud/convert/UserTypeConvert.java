package io.github.mp3cloud.convert;

import org.springframework.stereotype.Component;

import io.github.mp3cloud.entity.UserType;
import io.github.mp3cloud.dto.UserTypeDTO;

@Component
public class UserTypeConvert {

	public UserType toEntity(UserTypeDTO dto) {
		UserType userType = new UserType();
		userType.setUserPosition(dto.getUserPosition());
		return userType;
	}

	public UserTypeDTO toDTO(UserType entity) {
		UserTypeDTO dto = new UserTypeDTO();
		if (entity.getId() != 0) {
			dto.setId(entity.getId());
		}
		dto.setUserPosition(entity.getUserPosition());
		return dto;
	}

	public UserType toEntity(UserTypeDTO dto, UserType entity) {
		entity.setUserPosition(dto.getUserPosition());
		return entity;
	}

}
