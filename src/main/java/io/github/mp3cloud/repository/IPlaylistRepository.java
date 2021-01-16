package io.github.mp3cloud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.mp3cloud.entity.Playlist;

public interface IPlaylistRepository extends JpaRepository<Playlist, Long> {

	//@Query(value = "select * from playlist p where userid = :userPlaylist and name = :name", nativeQuery = true)
	//List<Playlist> findByPlayListOfUser(@Param("userPlaylist") long userPlaylist, @Param("name") String name);

	List<Playlist> findByNameAndUserPlaylist_Id(String name, long userPlaylist);

}
