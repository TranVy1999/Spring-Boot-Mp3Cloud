package io.github.mp3cloud.convert;

import org.springframework.stereotype.Component;

import io.github.mp3cloud.entity.Genre;
import io.github.mp3cloud.dto.GenreDTO;

@Component
public class GenreConvert {

	public Genre toEntity(GenreDTO dto) {
		Genre entity = new Genre();
		if (dto.getId() != 0)
			entity.setId(dto.getId());
		entity.setName(dto.getName());
//		entity.setAlbums(albums);
		return entity;
	}

	public GenreDTO toDTO(Genre entity) {
		GenreDTO dto = new GenreDTO();
		if (entity.getId() != 0)
			dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	public Genre toEntity(GenreDTO dto, Genre entity) {
		entity.setName(dto.getName());
//		entity.setAlbums(albums);
		return entity;
	}
}
