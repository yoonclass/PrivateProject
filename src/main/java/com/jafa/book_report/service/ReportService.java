package com.jafa.book_report.service;

import java.util.List;

import com.jafa.book_report.domain.ReportVO;
import com.jafa.common.Criteria;

//비즈니스 로직을 구현하는 인터페이스
//로직은 서비스 단에서 처리하여, 컨트롤러나 다른 레이어에 적합한 결과를 제공
//데이터베이스와 직접적인 관련 없이 도메인과 관련된 작업을 수행
//예를 들어, 독후감 목록 조회 시 데이터베이스 쿼리를 실행하고, 조회 결과를 처리하여 원하는 형태로 가공하는 작업
public interface ReportService {
	
	//독후감 목록 조회
	List<ReportVO> getList(Criteria criteria);
	
	//독후감 추가
	void register(ReportVO report);
	
	//독후감 특정 게시물 조회
	ReportVO get(Long bno);

	//독후감 특정 게시물 수정	
	boolean modify(ReportVO report);
	
	//독후감 삭제
	boolean remove(Long bno);
	
	int totalCount();
}
