package com.jafa.book_report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jafa.book_report.domain.LikeDTO;
import com.jafa.book_report.domain.ReportVO;
import com.jafa.book_report.repository.ReportLikeRepository;
import com.jafa.book_report.repository.ReportRepository;
import com.jafa.common.Criteria;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@RequiredArgsConstructor// 주요 생성자를 자동으로 생성해주는 기능
@Log4j
public class ReportServiceImpl implements ReportService {

	private final ReportRepository repository;
	
	@Autowired
	private ReportLikeRepository reportLikeRepository;
	
	@Override
	public List<ReportVO> getList(Criteria criteria) {
		log.info("(ServiceImpl)목록을 조회하였습니다 : ");
		return repository.getList(criteria);
	}

	@Override
	public void register(ReportVO report) {
		log.info("(ServiceImpl)등록하였습니다 : " + report);
		repository.insertSelectKey(report);
	}

	@Override
	public ReportVO get(Long bno) {
		log.info("(ServiceImpl)"+ bno + "번 게시물을 조회하였습니다 : ");
		return repository.read(bno);
	}

	@Override
	public boolean modify(ReportVO report) {
		log.info("(ServiceImpl)게시물을 수정하였습니다 : " + report);
		return repository.update(report) == 1;
		//성공적으로 수정되면 repository.update(report)의 반환값이 1이 되므로,
		//== 1 비교를 통해 true 또는 false를 반환
	}

	@Override
	public boolean remove(Long bno) {
		log.info("(ServiceImpl)"+ bno + "번 게시물을 삭제하였습니다");
		return repository.delete(bno) == 1;
	}

	@Override
	public int totalCount(Criteria criteria) {
		return repository.getTotalCount(criteria);
	}

	@Override
	public boolean hitLike(LikeDTO likeDTO) {
		LikeDTO result = reportLikeRepository.get(likeDTO);
		if(result==null) { //추천
			reportLikeRepository.insert(likeDTO);
			repository.updateLikeCnt(likeDTO.getBno(), 1);
			return true;
		} else { //추천 취소
			reportLikeRepository.delete(likeDTO);
			repository.updateLikeCnt(likeDTO.getBno(), -1);
			return false;
		}
	}

	@Override
	public boolean isLike(LikeDTO likeDTO) {
		return reportLikeRepository.get(likeDTO)!=null;
	}

	@Override
	public List<ReportVO> getRankList() {
		int maxRow = 5;
		return repository.getRankList(maxRow);
	}
}
