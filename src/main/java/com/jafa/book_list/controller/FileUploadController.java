package com.jafa.book_list.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jafa.book_list.domain.BookAttachVO;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/files")
public class FileUploadController {
	
	@PostMapping("/upload")
	public ResponseEntity<List<BookAttachVO>> upload(@RequestParam("uploadFile") MultipartFile[] multipartFiles) {
		List<BookAttachVO> list = new ArrayList<BookAttachVO>(); 
		File uploadPath = new File("C:/storage", getFolder());
		if(!uploadPath.exists()) {
			uploadPath.mkdirs(); 
		}
		for(MultipartFile multipartFile : multipartFiles) {
			BookAttachVO attachVO = new BookAttachVO();  

			String filName = multipartFile.getOriginalFilename(); // 파일이름
			String uuid = UUID.randomUUID().toString();
			File saveFile = new File(uploadPath,uuid + "_" + filName);
			
			log.info("filName : "+filName);
			log.info("savFile : "+saveFile);
			
			attachVO.setFileName(filName); 
			attachVO.setUuid(uuid);
			attachVO.setUploadPath(getFolder());
			
			try {
				if(checkImageType(saveFile)) {
					attachVO.setFileType(true);
				}
					multipartFile.transferTo(saveFile); // 파일 저장
				list.add(attachVO);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		} // for end
		return new ResponseEntity<List<BookAttachVO>>(list, HttpStatus.OK); 
	}

	// 이미지 파일 체크 여부
	private boolean checkImageType(File file) throws IOException {
		String contentType = Files.probeContentType(file.toPath());
		return contentType!=null ? contentType.startsWith("image") : false;
	}

	// 날짜별 디렉토리 생성 
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(new Date());  
	}
}