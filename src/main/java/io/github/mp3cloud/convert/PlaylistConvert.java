package io.github.mp3cloud.convert;

import org.springframework.stereotype.Component;

import io.github.mp3cloud.entity.Playlist;
import io.github.mp3cloud.dto.PlaylistDTO;

@Component
public class PlaylistConvert {

	public Playlist toEntity(PlaylistDTO dto) {
		Playlist entity = new Playlist();
		UserConvert user = new UserConvert();
		SongConvert song = new SongConvert();
		entity.setName(dto.getName());
		entity.setUserPlaylist(user.toEntity(dto.getUserPlaylist()));
		entity.setSong(song.toEntity(dto.getSong()));
		return entity;
	}

	public PlaylistDTO toDTO(Playlist entity) {
		PlaylistDTO dto = new PlaylistDTO();
		UserConvert user = new UserConvert();
		SongConvert song = new SongConvert();
		if (entity.getId() != 0)
			dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setSong(song.toDTO(entity.getSong()));
		dto.setUserPlaylist(user.toDTO(entity.getUserPlaylist()));
		return dto;
	}

	public Playlist toEntity(PlaylistDTO dto, Playlist entity) {
		UserConvert user = new UserConvert();
		SongConvert song = new SongConvert();
		entity.setName(dto.getName());
		entity.setUserPlaylist(user.toEntity(dto.getUserPlaylist()));
		entity.setSong(song.toEntity(dto.getSong()));
		return entity;
	}
}
