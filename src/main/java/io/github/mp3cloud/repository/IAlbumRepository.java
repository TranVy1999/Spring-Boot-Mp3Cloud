package io.github.mp3cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.mp3cloud.entity.Albums;

public interface IAlbumRepository extends JpaRepository<Albums, Long> {

}
