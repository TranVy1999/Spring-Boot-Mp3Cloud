package io.github.mp3cloud.service;

import java.util.Collection;

import org.springframework.data.domain.Pageable;

import io.github.mp3cloud.dto.SongDTO;

public interface ISongService {

	Collection<SongDTO> getAll(Pageable pageable);

	SongDTO getById(long id);

	SongDTO save(SongDTO newDTO);

	void delete(long id);

	int totalItem();
}
