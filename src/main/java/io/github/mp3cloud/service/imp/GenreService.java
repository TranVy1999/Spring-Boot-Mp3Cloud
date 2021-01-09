package io.github.mp3cloud.service.imp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.mp3cloud.convert.GenreConvert;
import io.github.mp3cloud.dto.GenreDTO;
import io.github.mp3cloud.dto.GenreDTO;
import io.github.mp3cloud.entity.Albums;
import io.github.mp3cloud.entity.Genre;
import io.github.mp3cloud.repository.IGenreRepository;
import io.github.mp3cloud.service.IGenreService;

@Service
public class GenreService implements IGenreService {

	@Autowired
	private IGenreRepository genreRepository;

	@Autowired
	private GenreConvert genreConvert;

	@Override
	public String save(List<GenreDTO> newDTO) {
		Genre entity = new Genre();
		for (GenreDTO GenreDTO : newDTO) {
			if (GenreDTO.getId() != 0) {
				Genre oldGenre = genreRepository.findById(GenreDTO.getId()).get();
				entity = genreConvert.toEntity(GenreDTO, oldGenre);
			} else {
				entity = genreConvert.toEntity(GenreDTO);
			}
			entity = genreRepository.save(entity);
		}
		return "ok";
	}

	@Override
	public void delete(long id) {
		genreRepository.deleteById(id);
	}

	@Override
	public Collection<GenreDTO> getAll(Pageable pageable) {
		Collection<GenreDTO> listDTO = new ArrayList<GenreDTO>();
		Collection<Genre> list = genreRepository.findAll(pageable).getContent();
		for (Genre album : list) {
			listDTO.add(genreConvert.toDTO(album));
		}
		return listDTO;
	}

	@Override
	public GenreDTO getById(long id) {
		Genre entity = new Genre();
		entity = genreRepository.findById(id).get();
		return genreConvert.toDTO(entity);
	}

	@Override
	public int totalItem() {
		return (int) genreRepository.count();
	}

}
