package com.jafa.book_report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jafa.book_report.domain.ReplyVO;
import com.jafa.book_report.repository.ReplyRepository;
import com.jafa.common.Criteria;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyRepository replyRepository;
	
	@Override
	public int register(ReplyVO vo) {
		return replyRepository.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		return replyRepository.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		return replyRepository.update(vo);
	}

	@Override
	public int remove(Long rno) {
		return replyRepository.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria criteria, Long bno) {
		return replyRepository.getList(bno, criteria);
	}
}