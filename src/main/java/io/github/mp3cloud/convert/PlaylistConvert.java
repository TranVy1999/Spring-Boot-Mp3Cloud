package io.github.mp3cloud.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.mp3cloud.entity.Playlist;
import io.github.mp3cloud.entity.Song;
import io.github.mp3cloud.repository.ISongRepository;
import io.github.mp3cloud.repository.IUserRepository;
import io.github.mp3cloud.dto.PlaylistDTO;
import io.github.mp3cloud.dto.SongDTO;

@Component
public class PlaylistConvert {
	@Autowired
	private ISongRepository iSongRepository;

	@Autowired
	private IUserRepository iUserRepository;

	public Playlist toEntity(PlaylistDTO dto, SongDTO songDto) {
		Playlist entity = new Playlist();
		entity.setName(dto.getName());
		entity.setUserPlaylist(iUserRepository.getOne(dto.getUserPlaylist().getId()));
		entity.setSong(iSongRepository.getOne(songDto.getId()));
		return entity;
	}

	public PlaylistDTO toDTO(List<Playlist> entity) {
		PlaylistDTO dto = new PlaylistDTO();
		UserConvert user = new UserConvert();
		Song song = new Song();
		SongConvert songConvert = new SongConvert();
		ArrayList<SongDTO> songDto = new ArrayList<>();
		for (Playlist playlist : entity) {
			if (playlist.getId() != 0)
				dto.setId(playlist.getId());
			dto.setName(playlist.getName());
			song = iSongRepository.getOne(playlist.getSong().getId());
			
			songDto.add(songConvert.toDTO(song));
			dto.setUserPlaylist(user.toDTO(playlist.getUserPlaylist()));
		}
		dto.setSong(songDto);
		return dto;
//		List<PlaylistDTO> songDto =entity.stream().map(e -> {
//			PlaylistDTO dto = new PlaylistDTO();
//			if (e.getId() != 0)
//				dto.setId(e.getId());
//			dto.setName(e.getName());
//			dto.setUserPlaylist(user.toDTO(e.getUserPlaylist()));
//			return dto;
//		}).collect(Collectors.toList());
	}

	public Playlist toEntity(PlaylistDTO dto, Playlist entity, SongDTO songDto) {
		UserConvert user = new UserConvert();
		SongConvert song = new SongConvert();
		entity.setName(dto.getName());
		entity.setUserPlaylist(user.toEntity(dto.getUserPlaylist()));
		entity.setSong(song.toEntity(songDto));
		return entity;
	}
}
