package com.jafa.member.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jafa.error.InvalidPasswordException;
import com.jafa.error.PasswordMisMatchException;
import com.jafa.member.controller.ProfileUploadController;
import com.jafa.member.domain.AuthVO;
import com.jafa.member.domain.MemberAttachVO;
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
	
	@Autowired
	ProfileUploadController profile;
	
	@Override
	public MemberVO read(String memberId) {
		return memberRepository.selectById(memberId);
	}

	@Transactional
	@Override
	public void join(MemberVO vo) {
		log.info(vo);
		log.info(passwordEncoder);
		
		vo.setMemberPwd(passwordEncoder.encode(vo.getMemberPwd()));
		AuthVO authVO = new AuthVO(vo.getMemberId(),"ROLE_MEMBER");
		memberRepository.insert(vo);
		authRepository.insert(authVO);
	}

	@Transactional
	@Override
	public void modify(Map<String, String> memberMap) {
		String memberId = memberMap.get("memberId");
		String newPwd = memberMap.get("newPwd");
		String currentPwd = memberMap.get("currentPwd");
		
		MemberVO vo = memberRepository.selectById(memberId);
		
		if(!passwordEncoder.matches(currentPwd, vo.getMemberPwd())) {
			throw new PasswordMisMatchException("현재 비밀번호가 일치하지 않습니다.");
		} 
		
		// 여기서 새 비밀번호 조건을 검증합니다.
	    if (newPwd.length() < 4 || newPwd.length() > 6) {
	        throw new InvalidPasswordException("새 비밀번호는 4글자 이상 6글자 이하이어야 합니다");
	    }
	    
	    //회원 비번 수정
	    memberRepository.updatePwd(memberId, passwordEncoder.encode(newPwd));
	    
		//첨부 파일 등록
		List<MemberAttachVO> attachList = vo.getAttachList();//첨부파일 목록 가져옴
		if(attachList!=null && !attachList.isEmpty()) {
			for(MemberAttachVO attachFile : attachList) {
				attachFile.setMno(memberId);//회원 아이디 설정
				memberRepository.insertImage(attachFile);
			};
		}
	}

	@Override
	public void delete(String memberId) {
		memberRepository.delete(memberId);
	}
}
