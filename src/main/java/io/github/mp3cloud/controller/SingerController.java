package io.github.mp3cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.mp3cloud.controller.output.SingerOutput;
import io.github.mp3cloud.service.ISingerService;
import io.github.mp3cloud.dto.ArtistDTO;

@RestController
public class SingerController {

	@Autowired
	private ISingerService singerService;

	@GetMapping(value = "/singer")
	public SingerOutput showNew(@RequestParam("page") int page, @RequestParam("limit") int limit) {
		SingerOutput singerOutput = new SingerOutput();
		singerOutput.setPage(page);
		Pageable pageable = PageRequest.of(page - 1, limit);
		singerOutput.setListResult(singerService.getAll(pageable));
		singerOutput.setTotalPage((int) Math.ceil((double) (singerService.totalItem()) / limit));
		return singerOutput;
	}

	@PostMapping(value = "/singer")
	public ArtistDTO createArtist(@RequestBody ArtistDTO model) {
		return singerService.save(model);
	}

	@PutMapping(value = "/singer/{id}")
	public ArtistDTO updateNew(@RequestBody ArtistDTO model, @PathVariable("id") long id) {
		model.setId(id);
		return singerService.save(model);
	}

	@DeleteMapping(value = "/singer/{id}")
	public void deleteNew(@RequestBody long id) {
		singerService.delete(id);
	}
}
