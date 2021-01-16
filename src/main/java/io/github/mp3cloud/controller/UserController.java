package io.github.mp3cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.mp3cloud.controller.output.UserOutput;
import io.github.mp3cloud.service.IUserService;
import io.github.mp3cloud.dto.UserDTO;

@RestController
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping(value = "/user")
	public UserOutput getListUser(@RequestParam("page") int page, @RequestParam("limit") int limit) {
		UserOutput userOutput = new UserOutput();
		userOutput.setPage(page);
		Pageable pageable = PageRequest.of(page - 1, limit);
		userOutput.setListResult(userService.getAll(pageable));
		userOutput.setTotalPage((int) Math.ceil((double) (userService.totalItem()) / limit));
		return userOutput;
	}

	@PutMapping(value = "/user/{id}")
	public UserDTO updateNew(@RequestBody UserDTO model, @PathVariable("id") long id) {
		model.setId(id);
		return userService.save(model);
	}

	@DeleteMapping(value = "/user/{id}")
	public String deleteNew(@PathVariable("id") long id) {
		userService.delete(id);
		return "ok";
	}
}
