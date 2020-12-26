package io.github.mp3cloud.service.imp;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.mp3cloud.convert.SongConvert;
import io.github.mp3cloud.entity.Song;
import io.github.mp3cloud.repository.ISongRepository;
import io.github.mp3cloud.service.ISongService;
import io.github.mp3cloud.dto.SongDTO;

@Service
public class SongService implements ISongService {

	@Autowired
	private ISongRepository songRepository;

	@Autowired
	private SongConvert songConvert;

	@Override
	public SongDTO save(SongDTO newDTO) {
		Song entity = new Song();
		if (newDTO.getId() != 0) {
			Song oldSong = songRepository.findById(newDTO.getId()).get();
			entity = songConvert.toEntity(newDTO, oldSong);
		} else {
			entity = songConvert.toEntity(newDTO);
		}
		entity = songRepository.save(entity);
		return songConvert.toDTO(entity);
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

}
