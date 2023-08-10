package com.jafa.book_report.service;

import com.jafa.book_report.domain.ReplyPageDTO;
import com.jafa.book_report.domain.ReplyVO;
import com.jafa.common.Criteria;

public interface ReplyService {
	
	int register(ReplyVO vo);
	
	ReplyVO get(Long rno);
	
	int modify(ReplyVO vo);
	
	int remove(Long rno);
	
	//댓글 목록 조회
	ReplyPageDTO getList(Criteria criteria, Long bno);
}
