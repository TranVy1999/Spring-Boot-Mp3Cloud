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

import io.github.mp3cloud.controller.output.SongOutput;
import io.github.mp3cloud.service.ISongService;
import io.github.mp3cloud.dto.SongDTO;

@RestController
public class SongController {

	@Autowired
	private ISongService songService;

	@GetMapping(value = "/playlist")
	public SongOutput showNew(@RequestParam("page") int page, @RequestParam("limit") int limit) {
		SongOutput singerOutput = new SongOutput();
		singerOutput.setPage(page);
		Pageable pageable = PageRequest.of(page - 1, limit);
		singerOutput.setListResult(songService.getAll(pageable));
		singerOutput.setTotalPage((int) Math.ceil((double) (songService.totalItem()) / limit));
		return singerOutput;
	}

	@PostMapping(value = "/playlist")
	public SongDTO createArtist(@RequestBody SongDTO model) {
		return songService.save(model);
	}

	@PutMapping(value = "/playlist/{id}")
	public SongDTO updateNew(@RequestBody SongDTO model, @PathVariable("id") long id) {
		model.setId(id);
		return songService.save(model);
	}

	@DeleteMapping(value = "/playlist")
	public void deleteNew(@RequestBody long id) {
		songService.delete(id);
	}
}