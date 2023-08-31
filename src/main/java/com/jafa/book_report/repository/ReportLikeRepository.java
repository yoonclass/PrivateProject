package com.jafa.book_report.repository;

import com.jafa.book_report.domain.LikeDTO;

public interface ReportLikeRepository {
	
	void insert(LikeDTO likeDTO);

	void delete(LikeDTO likeDTO);
	
	LikeDTO get(LikeDTO likeDTO);
}
