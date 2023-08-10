package com.jafa.book_report.repository;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.stream.IntStream;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.book_report.AppTest;
import com.jafa.book_report.domain.ReplyVO;
import com.jafa.common.Criteria;

import lombok.extern.log4j.Log4j;

@Log4j
public class ReplyRepositoryTest extends AppTest{

	@Autowired
	ReplyRepository replyRepository;
	
	@Test
	@Ignore
	public void insertTest() {
		//IntStream.rangeClosed(1, 10).forEach(i->{			
			ReplyVO vo = ReplyVO.builder()
					.bno(1L)
					.id("YOON")
					.reply("댓글 테스트")
					.replyer("사용자")
					.build();
			replyRepository.insert(vo);
			log.info(vo);
		//});
	}

	@Test
	@Ignore
	public void readTest() {
		ReplyVO vo = replyRepository.read(2L);
		log.info(vo);
	}
	
	@Test
	@Ignore
	public void updateTest() {
		ReplyVO vo = new ReplyVO();
		vo.setReply("댓글 테스트 -- 수정");
		vo.setRno(2L);
		replyRepository.update(vo);
	}
	
	@Test
	@Ignore
	public void deleteTest() {
		replyRepository.delete(3L);
	}
	
	@Test
	@Ignore
	public void getListTest() {
		replyRepository.getList(1L, new Criteria())
			.forEach(r -> log.info(r));
	}
	
	@Test
	public void getReplyCount() {
		replyRepository.getReplyCount(1L);
	}
}