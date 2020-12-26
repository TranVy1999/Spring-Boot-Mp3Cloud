package io.github.mp3cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.mp3cloud.entity.User;

public interface IUserRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);

	User findByEmail(String email);

}
