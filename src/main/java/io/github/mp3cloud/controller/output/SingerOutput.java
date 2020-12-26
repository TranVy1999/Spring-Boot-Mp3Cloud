package io.github.mp3cloud.controller.output;

import java.util.ArrayList;
import java.util.Collection;

import io.github.mp3cloud.dto.ArtistDTO;

public class SingerOutput {

	private int page;
	private int totalPage;
	private Collection<ArtistDTO> listResult = new ArrayList<>();

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public Collection<ArtistDTO> getListResult() {
		return listResult;
	}

	public void setListResult(Collection<ArtistDTO> listResult) {
		this.listResult = listResult;
	}

}
