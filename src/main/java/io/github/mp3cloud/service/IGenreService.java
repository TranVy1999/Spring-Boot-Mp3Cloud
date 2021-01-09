package io.github.mp3cloud.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;

import io.github.mp3cloud.dto.GenreDTO;

public interface IGenreService {

	Collection<GenreDTO> getAll(Pageable pageable);

	GenreDTO getById(long id);

	String save(List<GenreDTO> newDTO);

	void delete(long id);

	int totalItem();
}
