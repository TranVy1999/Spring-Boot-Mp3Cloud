package io.github.mp3cloud.dto;

public class CommentDTO {

	private long id;
	private String Content;
	private String createAt;
	private SongDTO song;
	private UserDTO user;

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public SongDTO getSong() {
		return song;
	}

	public void setSong(SongDTO song) {
		this.song = song;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUserComment(UserDTO user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

}
