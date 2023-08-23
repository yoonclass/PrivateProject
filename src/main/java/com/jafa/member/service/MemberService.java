package com.jafa.member.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.jafa.member.domain.MemberVO;

public interface MemberService {
	
	MemberVO read(String memberId);

	void changePwd(Map<String, String> memberMap);

	void delete(String memberId);

	void join(MemberVO vo, MultipartFile profileImage);
}
