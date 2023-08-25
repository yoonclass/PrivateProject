package com.jafa.member.repository;

import org.apache.ibatis.annotations.Param;

import com.jafa.member.domain.MemberAttachVO;
import com.jafa.member.domain.MemberVO;

public interface MemberRepository {
	
	//Id로 회원정보 조회
	MemberVO selectById(String memberId);
	
	//회원 등록
	void insert(MemberVO vo);
	
	//회원 이미지 등록
	void insertImage(MemberAttachVO attachVO);
	
	//이메일로 회원 Id 조회
	String selectByEmail(String memberEmail); 

	//비밀번호 업데이트
	void updatePwd(
			@Param("memberId") String memberId,  
			@Param("memberPwd") String memberPwd);
	
	//회원 탈퇴
	void delete(String memberId);
}
