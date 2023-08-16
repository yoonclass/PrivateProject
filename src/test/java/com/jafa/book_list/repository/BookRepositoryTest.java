package com.jafa.book_list.repository;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jafa.book_list.domain.BookVO;
import com.jafa.book_report.domain.ReportVO;
import com.jafa.common.Criteria;
import com.jafa.config.RootConfig;
import com.jafa.config.ServletConfig;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, ServletConfig.class} )
@WebAppConfiguration
@Log4j
public class BookRepositoryTest {

	@Autowired
	private BookRepository mapper;
	
	@Test
//	@Ignore
	public void test() {
		Criteria criteria = new Criteria();
		criteria.setPageNum(3);
		List<BookVO> list = mapper.getList(criteria);
		log.info(list.size());
		list.forEach(test->log.info(test));
	}
	
	@Test
	@Ignore
	public void testInsert() {
		BookVO book = new BookVO();
		book.setTitle("새 글");
		book.setContent("새 내용");
		book.setWriter("홍길동");
		mapper.insert(book);
		log.info(book);
	}

	@Test
	@Ignore
	public void testInsertSelectKey() {
		BookVO book = new BookVO();
		book.setTitle("새 글");
		book.setContent("새 내용");
		book.setWriter("장길동");
		mapper.insert(book);
		log.info(book);
	}
	
	@Test
	@Ignore
	public void testRead() {
		BookVO book = mapper.read(5L);
		log.info(book);
	}
	
	@Test
	@Ignore
	public void testUpdate() {
		BookVO book = new BookVO();
		// 실행전 존재하는 번호인지 확인할 것
		book.setBno(5L);
		book.setTitle("수정된 제목");
		book.setContent("수정된 내용");
		book.setWriter("수정자");

		int count = mapper.update(book);
		log.info("UPDATE COUNT: " + count);
	}
	
	@Test
	@Ignore
	public void testDelete() {
		log.info("DELETE COUNT: " + mapper.delete(6L));
	}
}
