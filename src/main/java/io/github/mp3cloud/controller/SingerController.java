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

import io.github.mp3cloud.controller.output.SingerOutput;
import io.github.mp3cloud.service.ISingerService;
import io.github.mp3cloud.dto.ArtistDTO;
import io.github.mp3cloud.dto.ImageDTO;

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
	public String createArtist(@RequestBody  Map<String,List<ArtistDTO>> model) {
		model.entrySet().forEach(e -> singerService.save(e.getValue()));
		return "ok";
	}

//	@PutMapping(value = "/singer/{id}")
//	public ArtistDTO updateNew(@RequestBody List<ArtistDTO> model, @PathVariable("id") long id) {
//		for (ArtistDTO artistDTO : model) {
//			artistDTO.setId(id);
//			singerService.save(artistDTO)
//		}
//		return ;
//	}

	@DeleteMapping(value = "/singer/{id}")
	public void deleteNew(@RequestBody long id) {
		singerService.delete(id);
	}
}
