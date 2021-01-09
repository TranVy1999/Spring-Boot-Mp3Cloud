package io.github.mp3cloud.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;

import io.github.mp3cloud.dto.AlbumsDTO;


public interface IAlbumService {

	Collection<AlbumsDTO> getAll(Pageable pageable);

	AlbumsDTO getById(long id);

	String save(List<AlbumsDTO> newDTO);

	void delete(long id);

	int totalItem();

}
