package io.github.mp3cloud.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.mp3cloud.entity.Artist;
import io.github.mp3cloud.entity.Image;
import io.github.mp3cloud.repository.IImageRepository;
import io.github.mp3cloud.dto.ArtistDTO;

@Component
public class ArtistConvert {

	@Autowired
	private IImageRepository imageRepository;

	public Artist toEntity(ArtistDTO dto) {
		Image imageEntity = imageRepository.getOne(dto.getImage().getId());
		Artist entity = new Artist();
		entity.setName(dto.getName());
		entity.setBirthDay(dto.getBirthDay());
		entity.setGender(dto.getGender());
		entity.setDescription(dto.getDescription());
		entity.setNationality(dto.getNationality());
		entity.setImage(imageEntity);
		return entity;

	}

	public ArtistDTO toDTO(Artist entity) {
		ArtistDTO dto = new ArtistDTO();
		ImageConvert image = new ImageConvert();
		if (entity.getId() != 0)
			dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setNationality(entity.getNationality());
		dto.setBirthDay(entity.getBirthDay());
		dto.setDescription(entity.getDescription());
		dto.setGender(entity.getGender());
		dto.setImage(image.toDTO(entity.getImage()));
		return dto;

	}

	public Artist toEntity(ArtistDTO dto, Artist entity) {
		ImageConvert image = new ImageConvert();
		entity.setName(dto.getName());
		entity.setBirthDay(dto.getBirthDay());
		entity.setGender(dto.getGender());
		entity.setDescription(dto.getDescription());
		entity.setNationality(dto.getNationality());
		entity.setImage(image.toEntity(dto.getImage()));
		return entity;

	}
}
