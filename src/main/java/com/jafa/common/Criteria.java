package com.jafa.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class Criteria {
	private int pageNum; // 현재 페이지
	private int amount; // 한 페이지당 게시물 수

	private String type;
	private String keyword;
	
	public Criteria() {
		this(1,10);	//1페이지, 한 페이지당 게시물 수는 10개
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public int getMaxRow() {
		return pageNum*amount;
	}

	public int getMinRow() {
		return (pageNum-1)*amount;
	}

	public String[] getTypes() { // collection="types"
		return type == null ? new String[] {} : type.split("");
	}
}
