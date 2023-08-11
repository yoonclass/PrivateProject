package com.jafa.member.repository;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.book_report.AppTest;
import com.jafa.member.domain.MemberVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class MemberRepositoryTest extends AppTest{

	@Autowired
	private MemberRepository memberRepository;
	
	MemberVO vo = new MemberVO();
	
	@Test
	@Ignore
	public void selectByIdTest() {
		vo = memberRepository.selectById("test");
		log.info(vo);
	}

	@Test
	@Ignore
	public void insertTest() {
		vo = MemberVO.builder()
		  .memberId("test")
		  .memberPwd("1234")
		  .memberName("사용자")
		  .memberEmail("test@naver").build();
		memberRepository.insert(vo);
		log.info(vo);
	}

	@Test
	@Ignore
	public void selectByEmailTest() {
		String memberId;
		memberId = memberRepository.selectByEmail("test@naver");
		log.info(memberId);
	}

	@Test
	@Ignore
	public void updatePasswordTest() {
		vo = memberRepository.selectById("test");
			String newPwd = "1234"; 
			memberRepository.updatePwd(vo.getMemberId(), newPwd);
			log.info(newPwd);
	}
	
	@Test
	@Ignore
	public void deleteTest() {
		memberRepository.delete("test");
	}
}
