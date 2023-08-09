package com.jafa.book_list.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.jafa.config.RootConfig;
import com.jafa.config.ServletConfig;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, ServletConfig.class} )
@WebAppConfiguration
@Log4j
public class BookControllerTest {

	//스프링 웹 애플리케이션의 컨텍스트를 의미
	//컨트롤러들, 뷰 리졸버, 핸들러 매핑 등과 같은 웹 관련 빈들이 이 컨텍스트에서 관리됨
	@Autowired
	private WebApplicationContext ctx;
	
	//스프링 MVC 컨트롤러를 테스트하는 데 사용되는 클래스
	//HTTP 요청을 시뮬레이션하고 컨트롤러의 동작을 테스트할 수 있습니다
	private MockMvc mockMvc; 
	
	//MockMvc 객체를 초기화하고, 웹 애플리케이션 컨텍스트를 설정하여 웹 환경에서 테스트할 준비
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test
	@Ignore
	public void testList() throws Exception {
		ModelAndView modelAndView = mockMvc.perform(get("/book_list/list"))
			.andReturn() //get요청 수행에 대한 결과 반환+MvcResult 객체로 반환됨
			.getModelAndView(); //MvcResult 객체에서 컨트롤러가 반환한 ModelAndView 객체 받아옴 
		Map<String,Object> model = modelAndView.getModel();
		//ModelAndView 객체에서 Model에 해당하는 Map(컨트롤러에서 뷰로 전달할 데이터)을 가져옴
		//컨트롤러에서 뷰로 전달한 데이터
		log.info("model : " + model);
		
		//컨트롤러에서 반환된 뷰의 이름
		log.info("modelAndView.getViewName : " + modelAndView.getViewName());
	}
	
	@Test
	@Ignore
	public void testGet() throws Exception {
		ModelAndView modelAndView = mockMvc.perform(get("/book_list/get").param("bno", "3"))
				.andReturn()
				.getModelAndView();
		Map<String, Object> model = modelAndView.getModel();
		log.info(model);
		log.info(modelAndView.getViewName());
		assertEquals("book_list/get",modelAndView.getViewName());
		//modelAndView.getViewName()으로 가져온 뷰 이름이 "book_report/get"과 일치하는지 확인
	}
	
	@Test
	@Ignore
	public void testRegister() throws Exception {
		ModelAndView modelAndView = mockMvc.perform(
				post("/book_list/register") //post요청 생성하고 수행
				.param("title", "컨트롤러 새 제목 테스트")
				.param("content", "컨트롤러 새 내용 테스트")
				.param("writer", "컨트롤러 새 작가"))//파라미터 값 설정
			.andReturn()
			.getModelAndView();
		log.info(modelAndView.getViewName());
		log.info(modelAndView.getStatus());
		Map<String,Object> model = modelAndView.getModel();
		log.info(model);	
	}

	@Test
	@Ignore
	public void testModify() throws Exception {
		ModelAndView modelAndView = mockMvc.perform(
				post("/book_list/modify") //post요청 생성하고 수행
				.param("bno", "5")
				.param("title", "컨트롤러 제목 수정")
				.param("content", "컨트롤러 내용 수정")
				.param("writer", "작가"))//파라미터 값 설정
				.andReturn()
				.getModelAndView();
		log.info(modelAndView.getViewName());
		log.info(modelAndView.getStatus());
		Map<String,Object> model = modelAndView.getModel();
		log.info(model);	
	}

	@Test
	@Ignore
	public void testRemove() throws Exception {
		ModelAndView modelAndView = mockMvc.perform(
				post("/book_list/remove")
				.param("bno", "3"))
		.andReturn()
		.getModelAndView();
		log.info(modelAndView.getViewName());
	}
}
