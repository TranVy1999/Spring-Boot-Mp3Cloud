package io.github.mp3cloud.convert;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.mp3cloud.entity.Albums;
import io.github.mp3cloud.entity.Image;
import io.github.mp3cloud.entity.Song;
import io.github.mp3cloud.repository.IAlbumRepository;
import io.github.mp3cloud.repository.IImageRepository;
import io.github.mp3cloud.dto.AlbumsDTO;
import io.github.mp3cloud.dto.ImageDTO;
import io.github.mp3cloud.dto.SongDTO;

@Component
public class SongConvert {

	@Autowired
	private IImageRepository imageRepository;
	@Autowired
	private IAlbumRepository albumRepository;

	public Song toEntity(SongDTO dto) {
		Song entity = new Song();
		Albums album = new Albums();
		Image image = new Image();
		AlbumsComvert albumsComvert = new AlbumsComvert();
		ImageConvert imageConvert = new ImageConvert();
		if (dto.getAlbums() != null) {
			album = albumRepository.getOne(dto.getAlbums().getId());
			dto.setAlbum(albumsComvert.toDTO(album));
		}
		if (dto.getImage() != null) {
			image = imageRepository.getOne(dto.getImage().getId());
			dto.setImage(imageConvert.toDTO(image));
		}
		entity.setTitle(dto.getTitle());
		entity.setDownloadPremit(dto.isDownloadPremit());
		entity.setShareLinks(dto.getShareLinks());
		entity.setAlbums(album);
		entity.setImage(image);
		return entity;
	}

	public SongDTO toDTO(Song entity) {
		SongDTO dto = new SongDTO();
		AlbumsComvert albumsComvert = new AlbumsComvert();
		ImageConvert imageConvert = new ImageConvert();
		if (entity.getAlbums() != null) {
			Albums album = albumRepository.findById(entity.getAlbums().getId()).get();
			dto.setAlbum(albumsComvert.toDTO(album));
		}
		if (entity.getImage() != null) {
			Image image = imageRepository.findById(entity.getImage().getId()).get();
			dto.setImage(imageConvert.toDTO(image));
		}
		if (entity.getId() != 0)
			dto.setId(entity.getId());
		dto.setDownloadPremit(entity.isDownloadPremit());
		dto.setTitle(entity.getTitle());
		dto.setShareLinks(entity.getShareLinks());
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

	public SongDTO toDTO(Song entity, long id) {
		SongDTO dto = new SongDTO();
		AlbumsDTO album = new AlbumsDTO();
		album.setId(entity.getAlbums().getId());
		ImageDTO image = new ImageDTO();
		image.setId(entity.getImage().getId());
		if (entity.getAlbums() != null) {
			dto.setAlbum(album);
		}
		if (entity.getImage() != null) {
			dto.setImage(image);
		}
		if (entity.getId() != 0)
			dto.setId(entity.getId());
		dto.setDownloadPremit(entity.isDownloadPremit());
		dto.setTitle(entity.getTitle());
		dto.setShareLinks(entity.getShareLinks());
		return dto;
	}

}
