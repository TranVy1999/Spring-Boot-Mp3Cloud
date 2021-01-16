package io.github.mp3cloud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.github.mp3cloud.entity.SungBy;

public interface ISungByRepository extends JpaRepository<SungBy, Long> {

	@Query(value = "select * from sung_by s where artistid = :id ", nativeQuery = true)
	List<SungBy> findByIdArtist(long id);

}
