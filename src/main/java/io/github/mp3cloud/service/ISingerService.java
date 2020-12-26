package io.github.mp3cloud.service;

import java.util.Collection;

import org.springframework.data.domain.Pageable;

import io.github.mp3cloud.dto.ArtistDTO;

public interface ISingerService {

	Collection<ArtistDTO> getAll(Pageable pageable);

	ArtistDTO getById(long id);

	ArtistDTO save(ArtistDTO newDTO);

	void delete(long ids);

	int totalItem();
}
