package io.github.mp3cloud.dto;

import java.util.Date;

public class AlbumsDTO {

	private long id;
	private String name;
	private Date releasedDate;
	private int totalTracks;
	private boolean downloadPermit;
	private ImageDTO image;
	private GenreDTO genreDTO;

	public long getId() {
		return id;
	}

	public GenreDTO getGenreDTO() {
		return genreDTO;
	}

	public void setGenreDTO(GenreDTO genreDTO) {
		this.genreDTO = genreDTO;
	}

	public ImageDTO getImage() {
		return image;
	}

	public void setImage(ImageDTO image) {
		this.image = image;
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
