package com.jafa.book_report.repository;

import java.util.List;

import com.jafa.book_report.domain.Criteria;
import com.jafa.book_report.domain.ReportVO;

//데이터베이스와 직접적으로 상호작용하는 인터페이스
//실제로 데이터베이스에 접근하여 데이터를 조작하고, SQL 쿼리를 실행하여 데이터를 추가, 조회, 수정, 삭제
//데이터베이스와의 결합도를 낮추고, 데이터베이스 관련 코드를 추상화하여 비즈니스 로직과 데이터 액세스 로직을 분리합니다.
public interface ReportRepository {
	//독후감 목록 조회
	List<ReportVO> getList(Criteria criteria);
	
	//독후감 추가
	void insert(ReportVO report);
	
	//독후감 추가 후 PK값 반환받기
	Integer insertSelectKey(ReportVO report);
	
	//독후감 특정 게시물 조회
	ReportVO read(Long bno);

	//독후감 특정 게시물 수정	
	int update(ReportVO report);
	
	//독후감 삭제
	int delete(Long bno);
	
	//전체 게시물 수
	int getTotalCount();
}
