package com.jafa.book_report.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jafa.book_report.domain.ReplyVO;
import com.jafa.common.Criteria;

public interface ReplyRepository {
	
	int insert(ReplyVO vo);	//등록
	
	ReplyVO read(Long rno);	//댓글조회+rno
	
	int delete(Long rno);	//삭제
	
	int update(ReplyVO vo);	//수정
	
	List<ReplyVO> getList(	//게시물별 댓글 조회
			@Param("bno") Long bno, 
			@Param("criteria") Criteria criteria);
	
	int getReplyCount(Long bno); // 하나의 게시물에 달린 댓글 수
}
