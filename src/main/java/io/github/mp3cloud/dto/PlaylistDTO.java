package io.github.mp3cloud.dto;

import java.util.ArrayList;

public class PlaylistDTO {

	private long id;
	private String name;
	private UserDTO userPlaylist;
	private ArrayList<SongDTO> song;


	public ArrayList<SongDTO> getSong() {
		return song;
	}

	public void setSong(ArrayList<SongDTO> song) {
		this.song = song;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserDTO getUserPlaylist() {
		return userPlaylist;
	}

	public void setUserPlaylist(UserDTO userPlaylist) {
		this.userPlaylist = userPlaylist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
