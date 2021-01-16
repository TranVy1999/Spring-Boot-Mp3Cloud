package io.github.mp3cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.mp3cloud.entity.UserType;

public interface IUserTypeRepository extends JpaRepository<UserType, Long> {

}
