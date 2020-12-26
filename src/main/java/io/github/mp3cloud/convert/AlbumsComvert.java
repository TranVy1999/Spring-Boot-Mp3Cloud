package io.github.mp3cloud.convert;

import org.springframework.stereotype.Component;

import io.github.mp3cloud.entity.Albums;
import io.github.mp3cloud.dto.AlbumsDTO;

@Component
public class AlbumsComvert {

	public Albums toEntity(AlbumsDTO dto) {
		Albums entity = new Albums();
		ImageConvert image = new ImageConvert();
		GenreConvert genre = new GenreConvert();
		entity.setName(dto.getName());
		entity.setDownloadPermit(dto.isDownloadPermit());
		entity.setReleasedDate(dto.getReleasedDate());
		entity.setTotalTracks(dto.getTotalTracks());
		entity.setGener(genre.toEntity(dto.getGener()));
		entity.setImage(image.toEntity(dto.getImage()));
		return entity;
	}

	public AlbumsDTO toDTO(Albums entity) {
		AlbumsDTO dto = new AlbumsDTO();
		ImageConvert image = new ImageConvert();
		GenreConvert genre = new GenreConvert();
		if (entity.getId() != 0)
			dto.setId(entity.getId());
		dto.setDownloadPermit(entity.isDownloadPermit());
		dto.setGener(genre.toDTO(entity.getGener()));
		dto.setImage(image.toDTO(entity.getImage()));
		dto.setName(entity.getName());
		dto.setReleasedDate(entity.getReleasedDate());
		dto.setTotalTracks(entity.getTotalTracks());
		return dto;
	}

	public Albums toEntity(AlbumsDTO dto, Albums entity) {
		ImageConvert image = new ImageConvert();
		GenreConvert genre = new GenreConvert();
		entity.setName(dto.getName());
		entity.setDownloadPermit(dto.isDownloadPermit());
		entity.setReleasedDate(dto.getReleasedDate());
		entity.setTotalTracks(dto.getTotalTracks());
		entity.setGener(genre.toEntity(dto.getGener()));
		entity.setImage(image.toEntity(dto.getImage()));
		return entity;
	}

}
