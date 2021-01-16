package io.github.mp3cloud.service.imp;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.mp3cloud.convert.SongConvert;
import io.github.mp3cloud.entity.Song;
import io.github.mp3cloud.repository.ISongRepository;
import io.github.mp3cloud.service.ISongService;
import io.github.mp3cloud.dto.SongDTO;

@Service
public class SongService implements ISongService {

	private final Path fileStorageLocation = Paths.get("D:\\Spring_Boot_Music\\Spring-Boot-Mp3Cloud\\media\\upload");

	@Autowired
	private ISongRepository songRepository;

	@Autowired
	private SongConvert songConvert;

	@Override
	public String save(List<SongDTO> newDTO) {
		Song entity = new Song();
		for (SongDTO songDTO : newDTO) {
			if (songDTO.getId() != 0) {
				Song oldSong = songRepository.findById(songDTO.getId()).get();
				entity = songConvert.toEntity(songDTO, oldSong);
			} else {
				entity = songConvert.toEntity(songDTO);
			}
			entity = songRepository.save(entity);
		}
		return "ok";
	}

	@Override
	public void delete(long id) {
		songRepository.deleteById(id);
	}

	@Override
	public Collection<SongDTO> getAll(Pageable pageable) {
		Collection<SongDTO> listDTO = new ArrayList<SongDTO>();
		Collection<Song> list = songRepository.findAll(pageable).getContent();
		for (Song song : list) {
			listDTO.add(songConvert.toDTO(song));
		}
		return listDTO;
	}

	@Override
	public SongDTO getById(long id) {
		Song entity = new Song();
		entity = songRepository.findById(id).get();
		return songConvert.toDTO(entity);
	}

	@Override
	public int totalItem() {
		return (int) songRepository.count();
	}

	@Override
	public SongDTO getLinkSong(String shareLinks) {
		Song song = songRepository.findByShareLinks(shareLinks);
		return songConvert.toDTO(song);
	}

	@Override
	public Resource loadFileAsResource(String fileName) throws Exception {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			System.out.println(filePath.toUri() + " filePath");
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new FileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new FileNotFoundException("File not found " + fileName);
		}
//		return null;
	}

	@Override
	public SongDTO findByTitle(String title) {
		Song song = songRepository.findByTitle(title);
		return songConvert.toDTO(song);
	}

}
