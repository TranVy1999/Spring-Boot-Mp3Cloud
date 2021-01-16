package io.github.mp3cloud.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.mp3cloud.convert.SungByConvert;
import io.github.mp3cloud.dto.PlaylistDTO;
import io.github.mp3cloud.dto.SongDTO;
import io.github.mp3cloud.dto.SungByDTO;
import io.github.mp3cloud.entity.Artist;
import io.github.mp3cloud.entity.Playlist;
import io.github.mp3cloud.entity.Song;
import io.github.mp3cloud.entity.SungBy;
import io.github.mp3cloud.repository.ISingerRepository;
import io.github.mp3cloud.repository.ISongRepository;
import io.github.mp3cloud.repository.ISungByRepository;
import io.github.mp3cloud.service.ISungByService;

@Service
public class SungByService implements ISungByService {

	@Autowired
	private ISungByRepository sungByRepository;

	@Autowired
	private SungByConvert sungByConvert;

	@Autowired
	private ISingerRepository singerRepository;
	
	@Override
	public String save(List<SungByDTO> newDTO) {
		SungBy entity = new SungBy();
		for (SungByDTO sungByDTO : newDTO) {
			for (SongDTO songDto : sungByDTO.getSong()) {
				entity = sungByConvert.toEntity(sungByDTO, songDto);
				entity = sungByRepository.save(entity);
			}
		}
		return "ok";
	}

	@Override
	public String save(SungByDTO newDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public SungByDTO getSongsByNameArtist(String nameArtist) {
		Artist artist = singerRepository.findByName(nameArtist);
		List<SungBy> entity = sungByRepository.findByIdArtist(artist.getId());
		return sungByConvert.toDTO(entity);
	}

}
