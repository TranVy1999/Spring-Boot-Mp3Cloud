package io.github.mp3cloud.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.mp3cloud.convert.PlaylistConvert;
import io.github.mp3cloud.dto.PlaylistDTO;
import io.github.mp3cloud.dto.SongDTO;
import io.github.mp3cloud.dto.UserDTO;
import io.github.mp3cloud.entity.Playlist;
import io.github.mp3cloud.repository.IPlaylistRepository;
import io.github.mp3cloud.service.IPlaylistService;
import io.github.mp3cloud.service.IUserService;

@Service
public class PlaylistService implements IPlaylistService {

	@Autowired
	private IPlaylistRepository playlistRepository;

	@Autowired
	private PlaylistConvert playlistConvert;

	@Autowired
	private IUserService userService;

	@Transactional
	@Override
	public String save(List<PlaylistDTO> newDTO) {
		Playlist entity = new Playlist();
		UserDTO user = new UserDTO();
		for (PlaylistDTO playlistDTO : newDTO) {
			for (SongDTO songDto : playlistDTO.getSong()) {
				user = userService.getById(playlistDTO.getUserPlaylist().getId());
				playlistDTO.setUserPlaylist(user);
				entity = playlistConvert.toEntity(playlistDTO, songDto);
				entity = playlistRepository.save(entity);
			}
		}
		return "ok";
	}

	@Override
	public String save(PlaylistDTO newDTO) {
		Playlist entity = new Playlist();
		for (SongDTO songDto : newDTO.getSong()) {
			if (newDTO.getId() != 0) {
				Playlist oldPlaylist = playlistRepository.findById(newDTO.getId()).get();
				entity = playlistConvert.toEntity(newDTO, oldPlaylist, songDto);
			}
			entity = playlistRepository.save(entity);
		}
		return "ok";
	}

	@Override
	public void delete(String name, long userid) {
		List<Playlist> p = playlistRepository.findByNameAndUserPlaylist_Id(name, userid);
		for (Playlist playlist : p) {
			playlistRepository.delete(playlist);
		}
	}

	@Override
	public int totalItem() {
		return (int) playlistRepository.count();
	}

	@Override
	public PlaylistDTO getByPlayListOfUser(long userPlaylist, String name) {
		List<Playlist> entity = playlistRepository.findByNameAndUserPlaylist_Id(name, userPlaylist);
		return playlistConvert.toDTO(entity);
	}

}
