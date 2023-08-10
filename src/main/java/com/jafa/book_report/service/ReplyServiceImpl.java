package com.jafa.book_report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jafa.book_report.domain.ReplyPageDTO;
import com.jafa.book_report.domain.ReplyVO;
import com.jafa.book_report.repository.ReplyRepository;
import com.jafa.book_report.repository.ReportRepository;
import com.jafa.common.Criteria;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private ReportRepository reportRepository;
	
	@Transactional
	@Override
	public int register(ReplyVO vo) {
		reportRepository.updateReplyCnt(vo.getBno(), 1);//해당 게시물의 댓글수 1증가
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

	@Transactional
	@Override
	public int remove(Long rno) {
		ReplyVO vo = replyRepository.read(rno);
		reportRepository.updateReplyCnt(vo.getBno(), -1);//해당 게시물의 댓글수 1감소
		return replyRepository.delete(rno);
	}

	@Override
	public ReplyPageDTO getList(Criteria criteria, Long bno) {
		return new ReplyPageDTO(
				replyRepository.getReplyCount(bno),
				replyRepository.getList(bno, criteria));
	}
}