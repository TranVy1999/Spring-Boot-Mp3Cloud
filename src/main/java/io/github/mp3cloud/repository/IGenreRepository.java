package io.github.mp3cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.mp3cloud.entity.Genre;

public interface IGenreRepository extends JpaRepository<Genre, Long> {

}
