package com.jafa.book_report.service;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jafa.book_report.AppTest;
import com.jafa.book_report.domain.ReplyPageDTO;
import com.jafa.book_report.domain.ReplyVO;
import com.jafa.common.Criteria;

import lombok.extern.log4j.Log4j;

@Log4j
public class ReplyServiceImplTest extends AppTest{

	@Autowired
	private ReplyService replyService;

	
	@Test
	@Ignore
	public void testList() {
		ReplyPageDTO replyPageDTO = replyService.getList(new Criteria(), 1L);
		log.info(replyPageDTO);
	}
	
	@Test
	@Ignore
	public void testRegister() {
		ReplyVO vo = ReplyVO.builder()
				.bno(2L)
				.reply("서비스 : 댓글 테스트")
				.replyer("사용자")
				.build();
		replyService.register(vo);
	}
	
	@Test
	@Ignore
	public void testGet() {
		ReplyVO vo = replyService.get(5L);
		log.info(vo);
	}
	
	
	@Test
	@Ignore
	public void modifyTest() {
		ReplyVO vo = new ReplyVO(); 
		vo.setRno(5L);
		vo.setReply("서비스 : 댓글 테스트 -- 수정");
		replyService.modify(vo);
	}
	
	@Test
	@Ignore
	public void remove() {
		replyService.remove(6L);
	}
}