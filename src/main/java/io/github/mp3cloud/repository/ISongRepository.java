package io.github.mp3cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.mp3cloud.entity.Song;

public interface ISongRepository extends JpaRepository<Song, Long> {

	Song findByShareLinks(@Param("songName") String shareLinks);

	@Query(value = "SELECT * FROM Song s WHERE s.title LIKE CONCAT(:title,'%')", nativeQuery = true)
	Song findByTitle(@Param("title") String title);

}
