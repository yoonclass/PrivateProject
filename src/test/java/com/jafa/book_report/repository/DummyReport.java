package com.jafa.book_report.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.book_report.AppTest;
import com.jafa.book_report.domain.ReportVO;

public class DummyReport extends AppTest{
	
	@Autowired
	ReportRepository repository; 
	
	
	// 408개 데이터 삽입 총 412개의 게시물
	@Test
	public void test() {
		
		for(int i=1;i<=302;i++) {
			ReportVO vo = ReportVO.builder()
					.title("제목 : 페이징 처리 " + i)
					.content("내용 : 페이징 처리 " + i)
					.writer("작성자" + (i%5))
					.build();
			repository.insert(vo);			
		}
	}
}