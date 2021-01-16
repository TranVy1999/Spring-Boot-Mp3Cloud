package io.github.mp3cloud.service.imp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.mp3cloud.convert.AlbumsComvert;
import io.github.mp3cloud.convert.GenreConvert;
import io.github.mp3cloud.dto.AlbumsDTO;
import io.github.mp3cloud.entity.Albums;
import io.github.mp3cloud.entity.Genre;
import io.github.mp3cloud.repository.IAlbumRepository;
import io.github.mp3cloud.service.IAlbumService;
import io.github.mp3cloud.service.IGenreService;

@Service
public class AlbumService implements IAlbumService {

	@Autowired
	private IAlbumRepository albumRepository;

	@Autowired
	private AlbumsComvert albumsComvert;

	@Autowired
	private IGenreService genreService;

	@Autowired
	private GenreConvert genreConvert;

	@Override
	public String save(List<AlbumsDTO> newDTO) {
		Albums entity = new Albums();
		Genre genre = null;
		for (AlbumsDTO albumsDTO : newDTO) {
			genre = genreConvert.toEntity(genreService.getById(albumsDTO.getGenreDTO().getId()));
			if (albumsDTO.getId() != 0) {
				Albums oldAlbum = albumRepository.findById(albumsDTO.getId()).get();
				entity.setGener(genre);
				entity = albumsComvert.toEntity(albumsDTO, oldAlbum);
			} else {
				entity.setGener(genre);
				entity = albumsComvert.toEntity(albumsDTO);
			}
			entity = albumRepository.save(entity);
		}
		return "ok";
	}

	@Override
	public void delete(long id) {
		albumRepository.deleteById(id);
	}

	@Override
	public Collection<AlbumsDTO> getAll(Pageable pageable) {
		Collection<AlbumsDTO> listDTO = new ArrayList<AlbumsDTO>();
		Collection<Albums> list = albumRepository.findAll(pageable).getContent();
		for (Albums album : list) {
			listDTO.add(albumsComvert.toDTO(album));
		}
		return listDTO;
	}

	@Override
	public AlbumsDTO getById(long id) {
		Albums entity = new Albums();
		entity = albumRepository.findById(id).get();
		return albumsComvert.toDTO(entity);
	}

	@Override
	public int totalItem() {
		return (int) albumRepository.count();
	}
}
