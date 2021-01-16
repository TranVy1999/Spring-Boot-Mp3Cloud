package io.github.mp3cloud.service;

import java.util.List;

import io.github.mp3cloud.dto.SungByDTO;

public interface ISungByService {

	String save(List<SungByDTO> newDTO);

	SungByDTO getSongsByNameArtist(String nameArtist);
}
