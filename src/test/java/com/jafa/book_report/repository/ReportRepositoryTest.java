package com.jafa.book_report.repository;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jafa.book_report.domain.Criteria;
import com.jafa.book_report.domain.ReportVO;
import com.jafa.config.RootConfig;
import com.jafa.config.ServletConfig;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, ServletConfig.class} )
@WebAppConfiguration
@Log4j
public class ReportRepositoryTest {

	@Autowired
	private ReportRepository mapper;
	
	@Test
	@Ignore
	public void test() {
		Criteria criteria = new Criteria();
		criteria.setPageNum(3);
		List<ReportVO> list = mapper.getList(criteria);
		log.info(list.size());
		list.forEach(test->log.info(test));
	}
	
	@Test
	@Ignore
	public void testInsert() {
		ReportVO report = new ReportVO();
		report.setTitle("새 글");
		report.setContent("새 내용");
		report.setWriter("홍길동");
		mapper.insert(report);
		log.info(report);
	}

	@Test
	@Ignore
	public void testInsertSelectKey() {
		ReportVO report = new ReportVO();
		report.setTitle("새 글");
		report.setContent("새 내용");
		report.setWriter("장길동");
		mapper.insert(report);
		log.info(report);
	}
	
	@Test
	@Ignore
	public void testRead() {
		ReportVO report = mapper.read(5L);
		log.info(report);
	}
	
	@Test
	@Ignore
	public void testUpdate() {
		ReportVO report = new ReportVO();
		// 실행전 존재하는 번호인지 확인할 것
		report.setBno(5L);
		report.setTitle("수정된 제목");
		report.setContent("수정된 내용");
		report.setWriter("수정자");

		int count = mapper.update(report);
		log.info("UPDATE COUNT: " + count);
	}
	
	@Test
	@Ignore
	public void testDelete() {
		log.info("DELETE COUNT: " + mapper.delete(6L));
	}
}
