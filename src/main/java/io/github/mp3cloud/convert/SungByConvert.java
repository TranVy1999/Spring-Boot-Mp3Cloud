package io.github.mp3cloud.convert;

import org.springframework.stereotype.Component;

import io.github.mp3cloud.entity.SungBy;
import io.github.mp3cloud.dto.SungByDTO;

@Component
public class SungByConvert {

	public SungBy toEntity(SungByDTO dto) {
		SungBy entity = new SungBy();
		SongConvert song = new SongConvert();
		ArtistConvert artist = new ArtistConvert();
		entity.setAlbum(song.toEntity(dto.getAlbum()));
		entity.setArtist(artist.toEntity(dto.getArtist()));
		entity.setSong(song.toEntity(dto.getSong()));
		return entity;
	}

	public SungByDTO toDTO(SungBy entity) {
		SungByDTO dto = new SungByDTO();
		SongConvert song = new SongConvert();
		ArtistConvert artist = new ArtistConvert();
		if (entity.getId() != 0)
			dto.setId(entity.getId());
		dto.setAlbum(song.toDTO(entity.getAlbum()));
		dto.setArtist(artist.toDTO(entity.getArtist()));
		dto.setSong(song.toDTO(entity.getSong()));
		return dto;
	}

	public SungBy toEntity(SungByDTO dto, SungBy entity) {
		SongConvert song = new SongConvert();
		ArtistConvert artist = new ArtistConvert();
		entity.setAlbum(song.toEntity(dto.getAlbum()));
		entity.setArtist(artist.toEntity(dto.getArtist()));
		entity.setSong(song.toEntity(dto.getSong()));
		return entity;
	}
}
