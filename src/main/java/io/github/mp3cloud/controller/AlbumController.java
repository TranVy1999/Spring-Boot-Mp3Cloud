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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.mp3cloud.controller.output.AlbumOutput;
import io.github.mp3cloud.dto.AlbumsDTO;
import io.github.mp3cloud.service.IAlbumService;

@RestController
public class AlbumController {

	@Autowired
	private IAlbumService albumService;

	@GetMapping(value = "/album")
	public AlbumOutput showNew(@RequestParam("page") int page, @RequestParam("limit") int limit) {
		AlbumOutput albumOutput = new AlbumOutput();
		albumOutput.setPage(page);
		Pageable pageable = PageRequest.of(page - 1, limit);
		albumOutput.setListResult(albumService.getAll(pageable));
		albumOutput.setTotalPage((int) Math.ceil((double) (albumService.totalItem()) / limit));
		return albumOutput;
	}

	@PostMapping(value = "/album")
	public String createAlbum(@RequestBody Map<String, List<AlbumsDTO>> model) {
		model.entrySet().forEach(e -> albumService.save(e.getValue()));
		return "ok";
	}

//	@PutMapping(value = "/album/{id}")
//	public AlbumsDTO updateNew(@RequestBody AlbumsDTO model, @PathVariable("id") long id) {
//		model.setId(id);
//		return albumService.save(model);
//	}

	@DeleteMapping(value = "/album/{id}")
	public String deleteNew(@PathVariable("id") long id) {
		albumService.delete(id);
		return "ok";
	}

}
