package io.github.mp3cloud.service.imp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.mp3cloud.convert.ImageConvert;
import io.github.mp3cloud.dto.ImageDTO;
import io.github.mp3cloud.entity.Image;
import io.github.mp3cloud.repository.IImageRepository;
import io.github.mp3cloud.service.IImageService;

@Service
public class ImageService implements IImageService {

	@Autowired
	private IImageRepository imageRepository;

	@Autowired
	private ImageConvert imageConvert;

	@Override
	public Collection<ImageDTO> getAll(Pageable pageable) {
		Collection<ImageDTO> listDTO = new ArrayList<ImageDTO>();
		Collection<Image> list = imageRepository.findAll(pageable).getContent();
		for (Image image : list) {
			listDTO.add(imageConvert.toDTO(image));
		}
		return listDTO;
	}

	@Override
	public ImageDTO getById(long id) {
		Image image = imageRepository.findById(id).get();
		return imageConvert.toDTO(image);
	}

	@Override
	public String save(List<ImageDTO> newDTO) {
		Image image = new Image();
		String tmp = "";
		try {
			for (ImageDTO imageDTO : newDTO) {
				if (imageDTO.getId() != 0) {
					Image oldImage = imageRepository.findById(imageDTO.getId()).get();
					image = imageConvert.toEntity(imageDTO, oldImage);
				} else {
					image = imageConvert.toEntity(imageDTO);
				}
				imageRepository.save(image);
			}
			tmp = "ok";
		} catch (Exception e) {
			tmp = "error" + e;
		}
		return tmp;
	}

	@Override
	public void delete(long id) {
		imageRepository.deleteById(id);
	}

	@Override
	public int totalItem() {
		return (int) imageRepository.count();
	}

}
