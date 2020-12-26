package io.github.mp3cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.mp3cloud.entity.Song;

public interface ISongRepository extends JpaRepository<Song, Long> {

}
