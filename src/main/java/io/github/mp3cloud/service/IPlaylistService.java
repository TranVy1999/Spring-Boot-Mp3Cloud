package io.github.mp3cloud.service;

import java.util.List;

import io.github.mp3cloud.dto.PlaylistDTO;

public interface IPlaylistService {

	String save(List<PlaylistDTO> newDTO);

	String save(PlaylistDTO newDTO);

	void delete(String name, long userid);

	int totalItem();

	PlaylistDTO getByPlayListOfUser(long userPlaylist, String name);
}
