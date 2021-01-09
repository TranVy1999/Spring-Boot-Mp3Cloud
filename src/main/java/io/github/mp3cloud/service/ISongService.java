package io.github.mp3cloud.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;

import io.github.mp3cloud.dto.SongDTO;

public interface ISongService {

	Collection<SongDTO> getAll(Pageable pageable);

	SongDTO getById(long id);

	String save(List<SongDTO> newDTO);

	void delete(long id);

	int totalItem();
}
