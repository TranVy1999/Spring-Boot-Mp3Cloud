package io.github.mp3cloud.dto;

public class PlaylistDTO {

	private long id;
	private String name;
	private UserDTO userPlaylist;
	private SongDTO song;

	public SongDTO getSong() {
		return song;
	}

	public void setSong(SongDTO song) {
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
