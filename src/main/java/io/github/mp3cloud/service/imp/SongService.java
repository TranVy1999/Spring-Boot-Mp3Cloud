package io.github.mp3cloud.service.imp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import io.github.mp3cloud.convert.SongConvert;
import io.github.mp3cloud.entity.Song;
import io.github.mp3cloud.repository.ISongRepository;
import io.github.mp3cloud.service.ISongService;
import io.github.mp3cloud.dto.SongDTO;

@Service
public class SongService implements ISongService {

	private final Path fileStorageLocation = Paths.get("src\\main\\resources\\static\\music");
//			.toAbsolutePath()
//			.normalize();

	@Autowired
	public SongService(SongDTO fileStorageProperties) {

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
		}
	}

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
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new FileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new FileNotFoundException("File not found " + fileName);
		}
	}

	@Override
	public SongDTO findByTitle(String title) {
		Song song = songRepository.findByTitle(title);
		return songConvert.toDTO(song);
	}

	@Override
	public String storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return fileName;
		} catch (IOException ex) {
			throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

}
