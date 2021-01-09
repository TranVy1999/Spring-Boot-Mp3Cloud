package io.github.mp3cloud.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;

import io.github.mp3cloud.dto.ArtistDTO;

public interface ISingerService {

	Collection<ArtistDTO> getAll(Pageable pageable);

	ArtistDTO getById(long id);

	String save(List<ArtistDTO> newDTO);

	void delete(long ids);

	int totalItem();
}
