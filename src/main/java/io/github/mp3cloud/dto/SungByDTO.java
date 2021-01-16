package io.github.mp3cloud.dto;

import java.util.List;

public class SungByDTO {

	private long id;
	private List<SongDTO> song;
	private ArtistDTO artist;
	private SongDTO album;

	public List<SongDTO> getSong() {
		return song;
	}

	public void setSong(List<SongDTO> song) {
		this.song = song;
	}

	public ArtistDTO getArtist() {
		return artist;
	}

	public void setArtist(ArtistDTO artist) {
		this.artist = artist;
	}

	public SongDTO getAlbum() {
		return album;
	}

	public void setAlbum(SongDTO album) {
		this.album = album;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
