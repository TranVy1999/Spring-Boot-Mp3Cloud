package io.github.mp3cloud.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "Genre")
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	@Type(type = "org.hibernate.type.StringNVarcharType")
	private String name;

	@OneToMany(mappedBy = "gener", cascade = CascadeType.ALL)
	private Collection<Albums> albums;

	public long getId() {
		return id;
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

	public Collection<Albums> getAlbums() {
		return albums;
	}

	public void setAlbums(Collection<Albums> albums) {
		this.albums = albums;
	}

}
