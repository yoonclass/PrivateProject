package com.jafa.book_list.repository;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.book_list.domain.BookAttachVO;
import com.jafa.book_report.AppTest;

import lombok.extern.log4j.Log4j;

@Log4j
public class BookAttachRepositoryTest extends AppTest {

	@Autowired
	BookAttachRepository bookAttachRepository;
	
	@Test
	@Ignore
	public void testInsert() {
		BookAttachVO vo = new BookAttachVO();
		vo.setBno(1L);
		vo.setFileName("test01.txt");
//		vo.setFileName("test02.txt");
		vo.setFileType(false);
		vo.setUploadPath("c:/upload");
		String uuid = UUID.randomUUID().toString();
		vo.setUuid(uuid);
		bookAttachRepository.insert(vo);
	}
	
	@Test
	@Ignore
	public void testSelectByBno() {
		bookAttachRepository.selectByBno(1L)
			.forEach(file -> log.info(file));
	}
	
	@Test
//	@Ignore
	public void testDelete() {
		// 데이터베이스에 저장된 uuid값을 참고
		String uuid = "b134b802-88e9-481c-9aca-8c7d033382cb";
		bookAttachRepository.delete(uuid);
	}
}
