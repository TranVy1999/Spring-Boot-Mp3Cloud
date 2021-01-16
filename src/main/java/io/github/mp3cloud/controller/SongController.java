package io.github.mp3cloud.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping(value = "/song")
	public SongOutput showNew(@RequestParam("page") int page, @RequestParam("limit") int limit) {
		SongOutput singerOutput = new SongOutput();
		singerOutput.setPage(page);
		Pageable pageable = PageRequest.of(page - 1, limit);
		singerOutput.setListResult(songService.getAll(pageable));
		singerOutput.setTotalPage((int) Math.ceil((double) (songService.totalItem()) / limit));
		return singerOutput;
	}

	@GetMapping(value = "/songName")
	public SongDTO findNameSong(@RequestParam("title") String title) {
		return songService.findByTitle(title);
	}

	@PostMapping(value = "/song")
	public String createArtist(@RequestBody Map<String, List<SongDTO>> model) {
		model.entrySet().forEach(e -> songService.save(e.getValue()));
		return "ok";
	}

//	@PutMapping(value = "/song/{id}")
//	public SongDTO updateNew(@RequestBody SongDTO model, @PathVariable("id") long id) {
//		model.setId(id);
//		return songService.save(model);
//	}

	@DeleteMapping(value = "/song/{id}")
	public String deleteNew(@PathVariable("id") long id) {
		songService.delete(id);
		return "ok";
	}

}
