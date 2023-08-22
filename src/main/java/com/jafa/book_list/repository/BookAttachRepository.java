package com.jafa.book_list.repository;

import java.util.List;

import com.jafa.book_list.domain.BookAttachVO;

public interface BookAttachRepository {

	void insert(BookAttachVO vo);	//첨부파일 등록
	
	List<BookAttachVO> selectByBno(Long bno); //게시물 선택
	
	BookAttachVO selectByUuid(String uuid); //uuid 첨부파일 선택

	void delete(String uuid);	//uuid 첨부파일 삭제
	
	void deleteAll(Long bno);//bno 게시물 삭제
	
	List<BookAttachVO> pastFiles();
}
