package com.jafa.book_report.service;

import java.util.List;

import com.jafa.book_report.domain.ReplyVO;
import com.jafa.common.Criteria;

public interface ReplyService {
	
	int register(ReplyVO vo);
	
	ReplyVO get(Long rno);
	
	int modify(ReplyVO vo);
	
	int remove(Long rno);
	
	List<ReplyVO> getList(Criteria criteria, Long bno);
}
