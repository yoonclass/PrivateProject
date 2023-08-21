package com.jafa.book_list.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jafa.book_list.domain.BookAttachVO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

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
				if(checkImageType(saveFile)) {	//저장된 파일의 형태를 체크
					attachVO.setFileType(true);
					FileOutputStream tumbnail = new FileOutputStream(new File(uploadPath,"s_"+uuid+"_"+filName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), tumbnail,40,40);
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
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(String fileName){
		File file = new File("C:/storage/"+fileName);
		ResponseEntity<byte[]> result = null; 
		
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(
					FileCopyUtils.copyToByteArray(file), 
					headers, 
					HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result; 
	}
	
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(BookAttachVO vo){
		File file = new File("C:/storage/"+vo.getUploadPath(),vo.getUuid() + "_" + vo.getFileName());
		log.info(file);
		file.delete();
		if(vo.isFileType()) {
			file = new File("C:/storage/"+vo.getUploadPath(),"s_"+vo.getUuid() + "_" + vo.getFileName());
			file.delete();
		}
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
	
	@GetMapping("/download")
	public ResponseEntity<Resource> dowonloadFile(
		 @RequestHeader("User-Agent") String userAgent, String fileName){
		Resource resource = new FileSystemResource("C:/storage/"+fileName);
		HttpHeaders headers = new HttpHeaders(); 
		
		if(!resource.exists()) {
			log.info("파일이 존재하지 않음");
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		String resourceName = resource.getFilename();
		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_")+1);
		String downloadName = null; 
		try {
			downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");
			headers.add("Content-Disposition", "attachment; fileName="+downloadName); // 다운로드 시 저장되는 이름
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
	}
}