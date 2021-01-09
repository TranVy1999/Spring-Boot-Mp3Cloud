package io.github.mp3cloud.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.mp3cloud.dto.ImageDTO;
import io.github.mp3cloud.service.IImageService;

@RestController
public class ImageController {

	@Autowired
	private IImageService imageService;

	@PostMapping(value = "/image")
	public String createArtist(@RequestBody Map<String,List<ImageDTO>> item) {
		item.entrySet().forEach(e -> imageService.save(e.getValue()));
		return "ok";
	}
}
