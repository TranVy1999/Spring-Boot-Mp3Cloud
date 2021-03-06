package io.github.mp3cloud.controller.output;

import java.util.ArrayList;
import java.util.Collection;

import io.github.mp3cloud.dto.PlaylistDTO;

public class PlaylistOutput {
	private int page;
	private int totalPage;
	private Collection<PlaylistDTO> listResult = new ArrayList<>();

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

	public Collection<PlaylistDTO> getListResult() {
		return listResult;
	}

	public void setListResult(Collection<PlaylistDTO> listResult) {
		this.listResult = listResult;
	}

}
