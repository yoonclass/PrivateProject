package com.jafa.member.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jafa.member.domain.MemberVO;

public interface MemberService {
	
	MemberVO read(String memberId);

	void join(MemberVO vo);
	
	void changePwd(
			@Param("memberId") String memberId,  
			@Param("memberPwd") String newPwd);

	void delete(String memberId);
}
