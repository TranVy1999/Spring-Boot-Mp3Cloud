package io.github.mp3cloud.dto;

public class ImageDTO {

	private long id;
	private String imgLocation;
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImgLocation() {
		return imgLocation;
	}

	public void setImgLocation(String imgLocation) {
		this.imgLocation = imgLocation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ImageDTO [id=" + id + ", imgLocation=" + imgLocation + ", name=" + name + "]";
	}

}
