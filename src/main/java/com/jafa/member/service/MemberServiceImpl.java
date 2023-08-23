package com.jafa.member.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jafa.error.PasswordMisMatchException;
import com.jafa.member.domain.AuthVO;
import com.jafa.member.domain.MemberVO;
import com.jafa.member.repository.AuthRepository;
import com.jafa.member.repository.MemberRepository;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private AuthRepository authRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public MemberVO read(String memberId) {
		return memberRepository.selectById(memberId);
	}

	@Value("${uploadPath}") // application.properties에서 설정한 업로드 경로
    private String uploadPath;
	
	@Transactional
	@Override
	public void join(MemberVO vo, MultipartFile profileImage) {
		log.info(vo);
		log.info(passwordEncoder);
		
		// 프로필 이미지를 저장할 경로 설정 (실제 경로에 맞게 수정해야 함)
        String uploadPath = "c:/uploads/profiles/";
		 // 이미지 업로드 처리 및 경로 저장
        if (!profileImage.isEmpty()) {
            String fileName = UUID.randomUUID().toString() + "_" + profileImage.getOriginalFilename();
            Path filePath = Paths.get(uploadPath, fileName);
            
            try {
                profileImage.transferTo(filePath);
                vo.setProfileImagePath(fileName);
            } catch (IOException e) {
                // 업로드 실패 처리
            }
        }
		
		vo.setMemberPwd(passwordEncoder.encode(vo.getMemberPwd()));
		AuthVO authVO = new AuthVO(vo.getMemberId(),"ROLE_MEMBER");
		memberRepository.insert(vo);
		authRepository.insert(authVO);
	}

	@Transactional
	@Override
	public void changePwd(Map<String, String> memberMap) {
		String memberId = memberMap.get("memberId");
		String newPwd = memberMap.get("newPwd");
		String currentPwd = memberMap.get("currentPwd");
		MemberVO vo = memberRepository.selectById(memberId);
		if(!passwordEncoder.matches(currentPwd, vo.getMemberPwd())) {
			throw new PasswordMisMatchException();
		}
		memberRepository.updatePwd(memberId, passwordEncoder.encode(newPwd));
	}

	@Override
	public void delete(String memberId) {
		memberRepository.delete(memberId);
	}
}
