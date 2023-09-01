package com.jafa.common;

import org.springframework.web.util.UriComponentsBuilder;

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
	
	private String type;//타입
	private String keyword;//키워드
	
	//생성자로 1번은 무조건 실행된다
	public Criteria() {
		this(1,10);	//1페이지, 한 페이지당 게시물 수는 10개
	}
	
	//매개변수로 들어오는 값을 이용하여 조정할 수 있다.
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

	public String getListLink() {	// "pageNum"과 "amount"가 포함된 URL 문자열을 생성
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum",this.pageNum)
				.queryParam("amount",this.amount);
		return builder.toUriString();
	}
}
