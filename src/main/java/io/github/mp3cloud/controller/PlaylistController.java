package io.github.mp3cloud.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.mp3cloud.dto.PlaylistDTO;
import io.github.mp3cloud.service.IPlaylistService;

@RestController
public class PlaylistController {

	@Autowired
	private IPlaylistService playlistService;

	@GetMapping(value = "/playlist")
	public PlaylistDTO showPlaylist(@RequestParam("userPlaylist") long userPlaylist,
			@RequestParam("name") String name) {
		PlaylistDTO dto = new PlaylistDTO();
		dto = playlistService.getByPlayListOfUser(userPlaylist, name);
		return dto;
	}

	@PostMapping(value = "/playlist")
	public String createPlaylist(@RequestBody Map<String, List<PlaylistDTO>> model) {
		model.entrySet().forEach(e -> playlistService.save(e.getValue()));
		return "ok";
	}

	@PutMapping(value = "/playlist/{id}")
	public String updateNew(@RequestBody Collection<PlaylistDTO> model, @PathVariable("id") long id) {
		for (PlaylistDTO plaDto : model) {
			plaDto.setId(id);
			playlistService.save(plaDto);
		}
		return "ok";
	}

	@DeleteMapping(value = "/playlist")
	public String deleteNew(@RequestBody PlaylistDTO playlist) {
		playlistService.delete(playlist.getName(), playlist.getUserPlaylist().getId());
		return "ok";
	}
}
