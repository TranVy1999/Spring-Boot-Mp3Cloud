package io.github.mp3cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.mp3cloud.entity.Image;

public interface IImageRepository extends JpaRepository<Image, Long> {

}
