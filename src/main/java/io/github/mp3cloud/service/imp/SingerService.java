package io.github.mp3cloud.service.imp;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.mp3cloud.convert.ArtistConvert;
import io.github.mp3cloud.entity.Artist;
import io.github.mp3cloud.repository.ISingerRepository;
import io.github.mp3cloud.service.ISingerService;
import io.github.mp3cloud.dto.ArtistDTO;

@Service
public class SingerService implements ISingerService {

	@Autowired
	private ArtistConvert artistConvert;
	@Autowired
	private ISingerRepository singerRepository;

	@Override
	public Collection<ArtistDTO> getAll(Pageable pageable) {
		Collection<ArtistDTO> listDTO = new ArrayList<ArtistDTO>();
		Collection<Artist> list = singerRepository.findAll(pageable).getContent();
		for (Artist artist : list) {
			listDTO.add(artistConvert.toDTO(artist));
		}
		return listDTO;
	}

	@Override
	public ArtistDTO getById(long id) {
		Artist artist = singerRepository.findById(id).get();
		return artistConvert.toDTO(artist);
	}

	@Override
	public ArtistDTO save(ArtistDTO newDTO) {
		Artist artist = new Artist();
		if (newDTO.getId() != 0) {
			Artist oldArtist = singerRepository.findById(newDTO.getId()).get();
			artist = artistConvert.toEntity(newDTO, oldArtist);
		} else {
			artist = artistConvert.toEntity(newDTO);
		}
		artist = singerRepository.save(artist);
		return artistConvert.toDTO(artist);
	}

	@Override
	public void delete(long id) {
		singerRepository.deleteById(id);

	}

	@Override
	public int totalItem() {
		return (int) singerRepository.count();
	}

}
