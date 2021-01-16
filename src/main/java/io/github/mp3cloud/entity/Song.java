package io.github.mp3cloud.entity;

import java.util.Collection;

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

import org.hibernate.annotations.Type;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Entity
@Table(name = "Song")
//@ConfigurationProperties(prefix = "file")
public class Song {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	@Type(type = "org.hibernate.type.StringNVarcharType")
	private String title;
	@Column
	private boolean downloadPremit;
	@Column
	private String shareLinks;

	@OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
	private Collection<Comment> comments;

	@OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
	private Collection<Playlist> playlists;

	@OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
	private Collection<SungBy> sungBySong;

	@OneToOne
	@JoinColumn(name = "ImageID")
	private Image image;

	@ManyToOne
	@JoinColumn(name = "AlbumID")
	private Albums albums;

	@OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
	private Collection<SungBy> sungByAlbum;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

	public Collection<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(Collection<Playlist> playlists) {
		this.playlists = playlists;
	}

	public Collection<SungBy> getSungBySong() {
		return sungBySong;
	}

	public void setSungBySong(Collection<SungBy> sungBySong) {
		this.sungBySong = sungBySong;
	}

	public Albums getAlbums() {
		return albums;
	}

	public void setAlbums(Albums albums) {
		this.albums = albums;
	}

	public Collection<SungBy> getSungByAlbum() {
		return sungByAlbum;
	}

	public void setSungByAlbum(Collection<SungBy> sungByAlbum) {
		this.sungByAlbum = sungByAlbum;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isDownloadPremit() {
		return downloadPremit;
	}

	public void setDownloadPremit(boolean downloadPremit) {
		this.downloadPremit = downloadPremit;
	}

	public String getShareLinks() {
		return shareLinks;
	}

	public void setShareLinks(String shareLinks) {
		this.shareLinks = shareLinks;
	}

}
