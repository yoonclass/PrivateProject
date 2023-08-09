package com.jafa.book_report.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jafa.book_report.AppTest;
import com.jafa.book_report.domain.ReplyVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class ReplyControllerTest extends AppTest{
	
	@Autowired
	WebApplicationContext ctx;	//웹 애플리케이션 컨텍스트 주입
	
	ObjectMapper objectMapper;	//JSON 데이터를 다루는데 사용되는 객체 
	
	private MockMvc mockMvc;	// 스프링 컨테이너와 상호 작용하여 컨트롤러의 동작을 테스트하는 데 사용되는 인스턴스
	
	@Before	//인스턴스 초기화하여, 컨트롤러 동작 테스트 준비
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		objectMapper = Jackson2ObjectMapperBuilder.json().build(); 
	}
	
	@Test
	@Ignore
	public void testRegister() throws Exception {
		ReplyVO vo = ReplyVO.builder()	
				.bno(1L)
				.id("YOON")
				.reply("웹 계층 : 댓글추가2")
				.replyer("작성자")
				.build();
		String content = objectMapper.writeValueAsString(vo);	//vo객체를 json 변환
		mockMvc.perform(post("/replies/new")	//실제 컨트롤러 동작 시뮬레이션
				.content(content)	//json 데이터 추가
				.contentType(MediaType.APPLICATION_JSON)	//요청의 타입 설정
		).andReturn();	//요청 수행후 결과 받아옴
	}
	
	@Test
	@Ignore
	public void testModify() throws Exception {
		ReplyVO vo = ReplyVO.builder()
				.reply("웹 계층 : 댓글 -- 수정")
				.build();
		String content = objectMapper.writeValueAsString(vo);
		mockMvc.perform(put("/replies/5")	//수정할 댓글 번호
				.content(content)
				.contentType(MediaType.APPLICATION_JSON)
		).andReturn();
	}
	
	@Test
	public void testRemove() throws Exception {
		mockMvc.perform(delete("/replies/12")); 
	}
}