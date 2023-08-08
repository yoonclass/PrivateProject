package com.jafa.book_list.repository;

import java.util.List;

import com.jafa.book_list.domain.BookVO;

public interface BookRepository {
	//도서 목록 조회
	List<BookVO> getList();
	
	//도서 추가
	void insert(BookVO report);
	
	//도서 추가 후 PK값 반환받기
	Integer insertSelectKey(BookVO report);
	
	//특정 도서조회
	BookVO read(Long bno);

	//도서 수정	
	int update(BookVO report);
	
	//도서 삭제
	int delete(Long bno);
	
	//전체 도서 수
	int getTotalCount();
}
