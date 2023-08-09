package com.jafa.book_list.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jafa.book_list.domain.BookVO;
import com.jafa.config.RootConfig;
import com.jafa.config.ServletConfig;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)	//스프링 컨테이너를 구동하고 테스트를 수행
@ContextConfiguration(classes = {RootConfig.class, ServletConfig.class} )//스프링 컨테이너를 구성하는 설정 클래스들을 지정
@WebAppConfiguration//웹 관련 설정이 자동으로 로딩되어 테스트 환경이 구성
@Log4j
public class BookServiceImplTest {

	@Autowired
	BookService service;
	
	@Test
	@Ignore
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}

	@Test
	@Ignore
	public void testGetList() {
		service.getList().forEach(service -> log.info(service));
	}
	
	@Test
	@Ignore
	public void testInsert() {
		BookVO book= new BookVO();
		book.setTitle("새 글");
		book.setContent("새 내용");
		book.setWriter("서비스");
		service.register(book);
		log.info("생성된 게시물의 번호 : " + book.getBno());
	}
	
	@Test
	@Ignore
	public void testGet() {
		log.info(service.get(1L));
	}
	
	@Test
	@Ignore
	public void testUpdate() {
		BookVO book = service.get(1L);
		assertNotNull(book);
		log.info(book);
		book.setContent("서비스에서 제목 수정합니다.");
		log.info("MODIFY RESULT: " + service.modify(book));
	}

	@Test
	@Ignore
	public void testDelete() {
		log.info("REMOVE RESULT: " + service.remove(2L));
	}
}