package io.github.mp3cloud.convert;

import org.springframework.stereotype.Component;

import io.github.mp3cloud.entity.Logs;
import io.github.mp3cloud.dto.LogsDTO;

@Component
public class LogsConvert {

	public Logs toEntity(LogsDTO dto) {
		Logs entity = new Logs();
		UserConvert user = new UserConvert();
		entity.setModified(dto.getModified());
		entity.setContent(dto.getContent());
		entity.setCreateAt(dto.getCreateAt());
		entity.setUser(user.toEntity(dto.getUser()));
		return entity;
	}

	public LogsDTO toDTO(Logs entity) {
		LogsDTO dto = new LogsDTO();
		UserConvert user = new UserConvert();
		if (entity.getId() != 0) {
			dto.setId(entity.getId());
		}
		dto.setContent(entity.getContent());
		dto.setCreateAt(entity.getCreateAt());
		dto.setModified(entity.getModified());
		dto.setUser(user.toDTO(entity.getUser()));
		return dto;
	}

	public Logs toEntity(LogsDTO dto, Logs entity) {
		UserConvert user = new UserConvert();
		entity.setModified(dto.getModified());
		entity.setContent(dto.getContent());
		entity.setCreateAt(dto.getCreateAt());
		entity.setUser(user.toEntity(dto.getUser()));
		return entity;
	}
}
