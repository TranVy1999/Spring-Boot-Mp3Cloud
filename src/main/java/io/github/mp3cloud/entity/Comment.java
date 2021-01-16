package io.github.mp3cloud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "Comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	@Type(type = "org.hibernate.type.StringNVarcharType")
	private String Contents;
	@Column
	private String createAt;

	@ManyToOne
	@JoinColumn(name = "SongID")
	private Song song;

	@ManyToOne
	@JoinColumn(name = "UserID")
	private User user;

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return Contents;
	}

	public void setContent(String content) {
		Contents = content;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public User getUser() {
		return user;
	}

	public void setUserComment(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

}
