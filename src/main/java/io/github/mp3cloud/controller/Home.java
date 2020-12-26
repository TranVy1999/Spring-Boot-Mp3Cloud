package io.github.mp3cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

	@GetMapping(value = "/")
	public String getHome() {
		return "Hello";
	}
}
