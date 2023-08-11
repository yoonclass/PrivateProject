package com.jafa.member.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jafa.config.RootConfig;
import com.jafa.config.ServletConfig;
import com.jafa.member.domain.MemberVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)	//스프링 컨테이너를 구동하고 테스트를 수행
@ContextConfiguration(classes = {RootConfig.class, ServletConfig.class} )//스프링 컨테이너를 구성하는 설정 클래스들을 지정
@WebAppConfiguration//웹 관련 설정이 자동으로 로딩되어 테스트 환경이 구성
@Log4j
public class MemberServiceImplTest {

	@Autowired
	MemberService memberService;
	
	@Test
	@Ignore
	public void testExist() {
		log.info(memberService);
		assertNotNull(memberService);
	}
	
	@Test
	@Ignore
	public void readTest() {
		MemberVO vo = new MemberVO();
		vo = memberService.read("YOON");
		log.info(vo);
	}
	
	@Test
	@Ignore
	public void insertTest() {
		MemberVO vo = new MemberVO();
			vo = MemberVO.builder()
					.memberId("admin")
					.memberPwd("1234")
					.memberName("관리자")
					.memberEmail("admin@test").build();
			memberService.join(vo);
	}
	
	@Test
	@Ignore
	public void changePwdTest() {
		MemberVO vo = new MemberVO();
		vo = memberService.read("admin");
		String newPwd = "1111"; 
		memberService.changePwd(vo.getMemberId(), newPwd);
		log.info(newPwd);
	}
	
	@Test
	@Ignore
	public void deleteTest() {
		memberService.delete("admin");
	}
}
