package com.jafa.member.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jafa.member.domain.MemberAttachVO;
import com.jafa.member.repository.MemberRepository;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Log4j
@RestController
@RequestMapping("/profile")
public class ProfileUploadController {
	
	@Autowired
	MemberRepository memberRepository;
	
	@PostMapping("/upload")
	public ResponseEntity<List<MemberAttachVO>> upload(@RequestParam("uploadFile") MultipartFile[] multipartFiles,
					Authentication authentication) {
		log.info(authentication.getName());
		
		List<MemberAttachVO> list = new ArrayList<MemberAttachVO>();
		File uploadPath = new File("C:/storage", getFolder());	//파일 경로를 정의하고
		if(!uploadPath.exists()) {	//존재하지 않는다면
			uploadPath.mkdirs(); 	//만들어라
		}
		for(MultipartFile multipartFile : multipartFiles) {	//멀티파일 객체코드의 반복문을 시작할건데
			MemberAttachVO attachVO = new MemberAttachVO();  //회원첨부 필드를 쓸 것이다.
			
			String fileName = multipartFile.getOriginalFilename(); // 파일이름
			String uuid = UUID.randomUUID().toString();	//랜덤으로 UUID 만듬
			File saveFile = new File(uploadPath,uuid + "_" + fileName);	//파일경로 uuid_ 파일명 = 저장파일
			
			log.info("filName : "+fileName);
			log.info("savFile : "+saveFile);
				
			if(authentication.getName()!=null) {
				log.info("이전 파일 삭제");
				memberRepository.deleteImage(authentication.getName());
				}
				attachVO.setFileName(fileName);	//파일이름 
				attachVO.setUuid(uuid);	//uuid
				attachVO.setUploadPath("/member");	//파일경로 설정
				attachVO.setMno(authentication.getName());
			
			try {//썸네일 생성
				File thumnailFile = new File(uploadPath, "s_"+ uuid + "_" + fileName);
				try(
				FileOutputStream tumbnail = new FileOutputStream(thumnailFile)) {
				Thumbnailator.createThumbnail(multipartFile.getInputStream(), tumbnail,40,40);
				}
				
				multipartFile.transferTo(saveFile); // 파일 저장
				list.add(attachVO); //회원첨부 테이블에 추가
				memberRepository.insertImage(attachVO);
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			} 
		} // for end
		return new ResponseEntity<List<MemberAttachVO>>(list, HttpStatus.OK);	//list배열로 추가한다 
	}

	private String getFolder() {	//파일경로 설정
		return "/member";  
	}
	
	// 파일 이름을 받아 해당 파일을 서버에서 읽어온 후, 바이트 배열 형태로 클라이언트에게 전송하는 역할을 수행
	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(String fileName){
		String folderPath = getFolder();
		File file = new File("C:/storage/"+folderPath+"/"+fileName);
		
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
	
	@GetMapping("/getProfileImage")
	public ResponseEntity<MemberAttachVO> getProfileImage(Authentication authentication) {
	    String memberId = authentication.getName();
	    MemberAttachVO profileImage = memberRepository.getImage(memberId);
	    return new ResponseEntity<>(profileImage, HttpStatus.OK);
	}
	
	@PostMapping("/deleteProfileImage")
	public ResponseEntity<String> deleteProfileImage(Authentication authentication) {
		if(authentication.getName()!=null) {
			log.info("기존 이미지 삭제");
			memberRepository.deleteImage(authentication.getName());
			}
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
}
