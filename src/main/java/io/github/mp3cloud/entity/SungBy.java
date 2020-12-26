package io.github.mp3cloud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "SungBy")
public class SungBy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "SongID")
	private Song song;

	@ManyToOne
	@JoinColumn(name = "ArtistID")
	private Artist artist;

	@ManyToOne
	@JoinColumn(name = "AlbumID")
	private Song album;

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public Song getAlbum() {
		return album;
	}

	public void setAlbum(Song album) {
		this.album = album;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
