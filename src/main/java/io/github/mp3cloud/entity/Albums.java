package io.github.mp3cloud.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Albums")
public class Albums {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String name;
	@Column
	private Date releasedDate;
	@Column
	private int totalTracks;
	@Column
	private boolean downloadPermit;

	@OneToMany(mappedBy = "albums", cascade = CascadeType.ALL)
	private Collection<Song> songs;

	@OneToOne
	@JoinColumn(name = "ImageID")
	private Image image;

	@ManyToOne
	@JoinColumn(name = "GenerID")
	private Genre gener;

	public long getId() {
		return id;
	}

	public Collection<Song> getSongs() {
		return songs;
	}

	public void setSongs(Collection<Song> songs) {
		this.songs = songs;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Genre getGener() {
		return gener;
	}

	public void setGener(Genre gener) {
		this.gener = gener;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getReleasedDate() {
		return releasedDate;
	}

	public void setReleasedDate(Date releasedDate) {
		this.releasedDate = releasedDate;
	}

	public int getTotalTracks() {
		return totalTracks;
	}

	public void setTotalTracks(int totalTracks) {
		this.totalTracks = totalTracks;
	}

	public boolean isDownloadPermit() {
		return downloadPermit;
	}

	public void setDownloadPermit(boolean downloadPermit) {
		this.downloadPermit = downloadPermit;
	}

}
