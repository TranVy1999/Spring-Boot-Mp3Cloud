package io.github.mp3cloud.service;

import java.util.Collection;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import io.github.mp3cloud.dto.SongDTO;

public interface ISongService {

	Collection<SongDTO> getAll(Pageable pageable);

	SongDTO getById(long id);

	String save(List<SongDTO> newDTO);

	void delete(long id);

	int totalItem();

	SongDTO getLinkSong(String shareLinks);

	Resource loadFileAsResource(String fileName) throws Exception;

	SongDTO findByTitle(String title);

	String storeFile(MultipartFile file);
}
