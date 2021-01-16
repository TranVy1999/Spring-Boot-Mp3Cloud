package io.github.mp3cloud.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import io.github.mp3cloud.entity.SungBy;
import io.github.mp3cloud.dto.SongDTO;
import io.github.mp3cloud.dto.SungByDTO;

@Component
public class SungByConvert {

	public SungBy toEntity(SungByDTO dto, SongDTO songDto) {
		SungBy entity = new SungBy();
		SongConvert song = new SongConvert();
		ArtistConvert artist = new ArtistConvert();
		entity.setAlbum(song.toEntity(dto.getAlbum()));
		entity.setArtist(artist.toEntity(dto.getArtist()));
		entity.setSong(song.toEntity(songDto));
		return entity;
	}

	public SungByDTO toDTO(List<SungBy> entity) {
		SungByDTO dto = new SungByDTO();
		SongConvert song = new SongConvert();
		List<SongDTO> listSong = new ArrayList<SongDTO>();
		ArtistConvert artist = new ArtistConvert();
		for (SungBy sungBy : entity) {
			if (sungBy.getId() != 0)
				dto.setId(sungBy.getId());
			dto.setAlbum(song.toDTO(sungBy.getAlbum()));
			dto.setArtist(artist.toDTO(sungBy.getArtist()));
			listSong.add(song.toDTO(sungBy.getSong()));
		}
		dto.setSong(listSong);
		return dto;
	}

	public SungBy toEntity(SungByDTO dto, SungBy entity) {
//		SongConvert song = new SongConvert();
//		ArtistConvert artist = new ArtistConvert();
//		entity.setAlbum(song.toEntity(dto.getAlbum()));
//		entity.setArtist(artist.toEntity(dto.getArtist()));
//		entity.setSong(song.toEntity(dto.getSong()));
		return entity;
	}
}
