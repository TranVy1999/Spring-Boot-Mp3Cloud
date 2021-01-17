package io.github.mp3cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import io.github.mp3cloud.dto.SongDTO;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableConfigurationProperties({ SongDTO.class })
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
