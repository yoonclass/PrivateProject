package com.jafa.book_list.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jafa.book_list.domain.BookVO;
import com.jafa.common.Criteria;

public interface BookRepository {
	//도서 목록 조회
	List<BookVO> getList(Criteria criteria);
	
	//최신 도서 조회
	List<BookVO> getLatestBooks(
			@Param("minRow") int minRow, @Param("maxRow") int maxRow);
	
	//도서 추가
	void insert(BookVO book);
	
	//도서 추가 후 PK값 반환받기
	Integer insertSelectKey(BookVO book);
	
	//특정 도서조회
	BookVO read(Long bno);

	//도서 수정	
	int update(BookVO book);
	
	//도서 삭제
	int delete(Long bno);
	
	//전체 도서 수
	int getTotalCount();
}
