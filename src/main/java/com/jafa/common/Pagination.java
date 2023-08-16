package com.jafa.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Pagination {

	private Criteria criteria;
	private int startPage; 
	private int endPage; 
	private int tempEndPage;  
	private int totalCount; // 독후감 게시물 수 306개
	private int displayPageNum = 10;
	private boolean prev; 
	private boolean next;
	
	public Pagination(Criteria criteria, int totalCount) {
		this.criteria = criteria;
		this.totalCount = totalCount;
		endPage = (int) Math.ceil(criteria.getPageNum()/(double)displayPageNum)*displayPageNum;  
		startPage = endPage-displayPageNum+1; 
		tempEndPage = (int) Math.ceil(totalCount/(double)criteria.getAmount());
		
		prev = startPage != 1;
		next = endPage < tempEndPage;
		
		if(endPage>tempEndPage) endPage = tempEndPage;
	} 
}
