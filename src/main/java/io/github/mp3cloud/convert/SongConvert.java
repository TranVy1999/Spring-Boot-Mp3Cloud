package io.github.mp3cloud.convert;

import org.springframework.stereotype.Component;

import io.github.mp3cloud.entity.Song;
import io.github.mp3cloud.dto.SongDTO;

@Component
public class SongConvert {

	public Song toEntity(SongDTO dto) {
		Song entity = new Song();
		AlbumsComvert album = new AlbumsComvert();
		ImageConvert image = new ImageConvert();
		entity.setTitle(dto.getTitle());
		entity.setDownloadPremit(dto.isDownloadPremit());
		entity.setShareLinks(dto.getShareLinks());
//		entity.setAlbums(album.toEntity(dto.getAlbum()));
//		entity.setImage(image.toEntity(dto.getImage()));
		return entity;
	}

	public SongDTO toDTO(Song entity) {
		SongDTO dto = new SongDTO();
		AlbumsComvert album = new AlbumsComvert();
		ImageConvert image = new ImageConvert();
		if (entity.getId() != 0)
			dto.setId(entity.getId());
		dto.setDownloadPremit(entity.isDownloadPremit());
		dto.setTitle(entity.getTitle());
		dto.setShareLinks(entity.getShareLinks());
//		dto.setImage(image.toDTO(entity.getImage()));
//		dto.setAlbum(album.toDTO(entity.getAlbums()));
		return dto;
	}

	public Song toEntity(SongDTO dto, Song entity) {
		AlbumsComvert album = new AlbumsComvert();
		ImageConvert image = new ImageConvert();
		entity.setTitle(dto.getTitle());
		entity.setDownloadPremit(dto.isDownloadPremit());
		entity.setShareLinks(dto.getShareLinks());
		entity.setAlbums(album.toEntity(dto.getAlbum()));
		entity.setImage(image.toEntity(dto.getImage()));
		return entity;
	}
}
