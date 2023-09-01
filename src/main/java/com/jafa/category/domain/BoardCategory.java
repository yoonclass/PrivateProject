package com.jafa.category.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class BoardCategory {
	private int categoryId;//카테고리 ID
	private String categoryName;//카테고리 이름
}
