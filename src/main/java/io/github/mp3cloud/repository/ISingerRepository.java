package io.github.mp3cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.mp3cloud.entity.Artist;

public interface ISingerRepository extends JpaRepository<Artist, Long> {

}
