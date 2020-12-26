package io.github.mp3cloud.convert;

import org.springframework.stereotype.Component;

import io.github.mp3cloud.entity.Comment;
import io.github.mp3cloud.dto.CommentDTO;

@Component
public class CommentConvert {

	public Comment toEntity(CommentDTO dto) {
		Comment entity = new Comment();
		SongConvert song = new SongConvert();
		UserConvert user = new UserConvert();
		entity.setContent(dto.getContent());
		entity.setCreateAt(dto.getCreateAt());
		entity.setUserComment(user.toEntity(dto.getUser()));
		entity.setSong(song.toEntity(dto.getSong()));
		return entity;
	}

	public CommentDTO toDTO(Comment entity) {
		CommentDTO dto = new CommentDTO();
		SongConvert song = new SongConvert();
		UserConvert user = new UserConvert();
		if (entity.getId() != 0)
			dto.setId(entity.getId());
		dto.setContent(entity.getContent());
		dto.setCreateAt(entity.getCreateAt());
		dto.setSong(song.toDTO(entity.getSong()));
		dto.setUserComment(user.toDTO(entity.getUser()));
		return dto;
	}

	public Comment toEntity(CommentDTO dto, Comment entity) {
		SongConvert song = new SongConvert();
		UserConvert user = new UserConvert();
		entity.setContent(dto.getContent());
		entity.setCreateAt(dto.getCreateAt());
		entity.setUserComment(user.toEntity(dto.getUser()));
		entity.setSong(song.toEntity(dto.getSong()));
		return entity;
	}
}
