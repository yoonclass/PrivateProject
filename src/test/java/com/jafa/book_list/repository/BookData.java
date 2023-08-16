package com.jafa.book_list.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.book_list.domain.BookVO;
import com.jafa.book_report.AppTest;

public class BookData extends AppTest{
	
	@Autowired
	BookRepository book; 
	
	@Test
	public void test() {
		
		for(int i=1;i<=212;i++) {
			BookVO vo = BookVO.builder()
					.title("제목 : 위인전 " + i)
					.content("내용 : 이순신 " + i)
					.writer("작성자" + (i%5))
					.build();
			book.insert(vo);			
		}
		
		for(int i=1;i<=212;i++) {
			BookVO vo = BookVO.builder()
					.title("제목 : 동화책 " + i)
					.content("내용 : 나무꾼 " + i)
					.writer("글쓴이" + (i%5))
					.build();
			book.insert(vo);			
		}
		
		for(int i=1;i<=212;i++) {
			BookVO vo = BookVO.builder()
					.title("제목 : 소설 " + i)
					.content("내용 : 로맨스 " + i)
					.writer("관리자" + (i%5))
					.build();
			book.insert(vo);			
		}
	}
}