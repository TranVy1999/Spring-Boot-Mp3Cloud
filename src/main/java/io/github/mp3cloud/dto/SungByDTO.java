package io.github.mp3cloud.dto;

public class SungByDTO {

	private long id;
	private SongDTO song;
	private ArtistDTO artist;
	private SongDTO album;

	public SongDTO getSong() {
		return song;
	}

	public void setSong(SongDTO song) {
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
