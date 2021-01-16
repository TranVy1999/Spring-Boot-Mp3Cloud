package io.github.mp3cloud.convert;

import org.springframework.stereotype.Component;

import io.github.mp3cloud.entity.Image;
import io.github.mp3cloud.dto.ImageDTO;

@Component
public class ImageConvert {

	public Image toEntity(ImageDTO dto) {
		Image entity = new Image();
		if (dto != null) {
			entity.setImgLocation(dto.getImgLocation());
			entity.setName(dto.getName());
		}
		return entity;
	}

	public ImageDTO toDTO(Image entity) {
		ImageDTO dto = new ImageDTO();
		if (entity != null) {
			if (entity.getId() != 0)
				dto.setId(entity.getId());
			dto.setImgLocation(entity.getImgLocation());
			dto.setName(entity.getName());
		}
		return dto;
	}

	public Image toEntity(ImageDTO dto, Image entity) {
		entity.setImgLocation(dto.getImgLocation());
		entity.setName(dto.getName());
		return entity;
	}
}
