package io.github.mp3cloud.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;

import io.github.mp3cloud.dto.ImageDTO;

public interface IImageService {

	Collection<ImageDTO> getAll(Pageable pageable);

	ImageDTO getById(long id);

	String save(List<ImageDTO> newDTO);

	void delete(long id);

	int totalItem();
}
