package io.github.mp3cloud.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class SongDTO {

	private long id;
	private String title;
	private boolean downloadPremit;
	private String shareLinks;
	private ImageDTO image;
	private AlbumsDTO albums;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AlbumsDTO getAlbums() {
		return albums;
	}

	public void setAlbums(AlbumsDTO albums) {
		this.albums = albums;
	}

	public ImageDTO getImage() {
		return image;
	}

	public void setImage(ImageDTO image) {
		this.image = image;
	}

	public AlbumsDTO getAlbum() {
		return albums;
	}

	public void setAlbum(AlbumsDTO album) {
		this.albums = album;
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
