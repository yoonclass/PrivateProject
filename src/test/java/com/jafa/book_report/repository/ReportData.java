package com.jafa.book_report.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.book_report.AppTest;
import com.jafa.book_report.domain.ReportVO;
import com.jafa.book_report.repository.ReportRepository;

public class ReportData extends AppTest{
	
	@Autowired
	ReportRepository report; 
	
	@Test
	public void test() {
		
		for(int i=1;i<=212;i++) {
			ReportVO vo = ReportVO.builder()
					.title("제목 : 위인전 " + i)
					.content("내용 : 이순신 " + i)
					.writer("작성자" + (i%5))
					.build();
			report.insert(vo);			
		}
		
		for(int i=1;i<=212;i++) {
			ReportVO vo = ReportVO.builder()
					.title("제목 : 동화책 " + i)
					.content("내용 : 나무꾼 " + i)
					.writer("글쓴이" + (i%5))
					.build();
			report.insert(vo);			
		}
		
		for(int i=1;i<=212;i++) {
			ReportVO vo = ReportVO.builder()
					.title("제목 : 소설 " + i)
					.content("내용 : 로맨스 " + i)
					.writer("관리자" + (i%5))
					.build();
			report.insert(vo);			
		}
	}
}