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
import com.jafa.member.domain.AuthVO;
import com.jafa.member.domain.MemberVO;
import com.jafa.member.repository.AuthRepository;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)	//스프링 컨테이너를 구동하고 테스트를 수행
@ContextConfiguration(classes = {RootConfig.class, ServletConfig.class} )//스프링 컨테이너를 구성하는 설정 클래스들을 지정
@WebAppConfiguration//웹 관련 설정이 자동으로 로딩되어 테스트 환경이 구성
@Log4j
public class MemberServiceImplTest {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private AuthRepository authRepository;
	
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
//	@Ignore
	public void insertTest() {
		MemberVO vo = new MemberVO();
			vo = MemberVO.builder()
					.memberId("test")
					.memberPwd("1234")
					.memberName("사용자")
					.memberEmail("user@test").build();
			memberService.join(vo);
	}
	
	@Test
//	@Ignore
	public void test() {
		MemberVO vo = new MemberVO(); 
		vo.setMemberId("admin");
		vo.setMemberPwd("1234");
		vo.setMemberName("관리자");
		vo.setMemberEmail("admin@test.com");
		memberService.join(vo);
		
		AuthVO authVO = new AuthVO("admin","ROLE_ADMIN");
		authRepository.insert(authVO);
	}
	
	@Test
	@Ignore
	public void test2() {
		AuthVO vo = new AuthVO("YOON","ROLE_MEMBER");
		authRepository.insert(vo);
	}
	
	@Test
	@Ignore
	public void changePwdTest() {
		MemberVO vo = new MemberVO();
		vo = memberService.read("admin");
		String newPwd = "1111"; 
		memberService.changePwd(null);
		log.info(newPwd);
	}
	
	@Test
	@Ignore
	public void deleteTest() {
		memberService.delete("admin");
	}
}
