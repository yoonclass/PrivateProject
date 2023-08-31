package com.jafa.book_list.service;

import java.util.List;

import com.jafa.book_list.domain.BookAttachVO;
import com.jafa.book_list.domain.BookVO;
import com.jafa.common.Criteria;

public interface BookService {
	//도서 목록 조회
	List<BookVO> getList(Criteria criteria);
	
	//도서 추가
	void register(BookVO book);
	
	//도서 특정 게시물 조회
	BookVO get(Long bno);

	//도서 특정 게시물 수정	
	boolean modify(BookVO book);
	
	//도서 삭제
	boolean remove(Long bno);
	
	//게시물 수
	int totalCount();
	
	//게시물 번호에 해당하는 첨부파일 리스트
	List<BookAttachVO> getAttachList(Long bno);
	
	//첨부파일 UUID에 해당하는 첨부파일
	BookAttachVO getAttach(String uuid);
	
	//최근 도서 조회
	List<BookVO> getLatestBooks();
}
