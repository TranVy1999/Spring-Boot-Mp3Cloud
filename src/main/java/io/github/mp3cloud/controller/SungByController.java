package io.github.mp3cloud.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.mp3cloud.dto.SungByDTO;
import io.github.mp3cloud.service.ISungByService;

@RestController
public class SungByController {

	@Autowired
	private ISungByService sungByService;

	@GetMapping(value = "/presents")
	public SungByDTO showPresents(@RequestParam("name") String nameArtist) {
		SungByDTO dto = new SungByDTO();
		dto = sungByService.getSongsByNameArtist(nameArtist);
		return dto;
	}

	@PostMapping(value = "/presents")
	public String createPresents(@RequestBody Map<String, List<SungByDTO>> model) {
		model.entrySet().forEach(e -> sungByService.save(e.getValue()));
		return "ok";
	}

//	@PutMapping(value = "/presents/{id}")
//	public String updateNew(@RequestBody Collection<SungByDTO> model, @PathVariable("id") long id) {
//		for (SungByDTO plaDto : model) {
//			plaDto.setId(id);
//			sungByService.save(plaDto);
//		}
//		return "ok";
//	}

//	@DeleteMapping(value = "/playlist/{id}")
//	public void deleteNew(@RequestBody long id) {
//		sungByService.delete(id);
//	}
}
