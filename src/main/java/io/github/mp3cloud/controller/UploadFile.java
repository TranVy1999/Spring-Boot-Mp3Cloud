package io.github.mp3cloud.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.mp3cloud.dto.SongDTO;
import io.github.mp3cloud.service.ISongService;

@RestController
public class UploadFile {

	@Autowired
	private ISongService songService;

	@PostMapping("/downloadFile")
	public ResponseEntity<Resource> downloadFile(@RequestParam("shareLinks") String shareLinks,
			HttpServletRequest request) {

		SongDTO dto = songService.getLinkSong(shareLinks);
		System.out.println(dto.getShareLinks() + " songName");
		String fileName = dto.getShareLinks();
		Resource resource = null;
		if (fileName != null && !fileName.isEmpty()) {
			try {
				resource = songService.loadFileAsResource(fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Try to determine file's content type
//			String contentType = null;
//			try {
//				contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//			} catch (IOException ex) {
//				// logger.info("Could not determine file type.");
//			}
//			// Fallback to the default content type if type could not be determined
//			if (contentType == null) {
//				contentType = "application/octet-stream";
//			}MediaType..contentType(parseMediaType(contentType))

			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
					.body(resource);
		} else {
			return ResponseEntity.notFound().build();
		}

	}
}
