package com.jafa.book_list.repository;

import java.util.List;

import com.jafa.book_list.domain.BookAttachVO;

public interface BookAttachRepository {

	void insert(BookAttachVO vo);
	
	void delete(String uuid);
	
	List<BookAttachVO> selectByBno(Long bno);
}
