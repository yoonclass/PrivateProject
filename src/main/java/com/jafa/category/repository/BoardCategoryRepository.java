package com.jafa.category.repository;

import java.util.List;

import com.jafa.category.domain.BoardCategory;

public interface BoardCategoryRepository {
	List<BoardCategory> readCategory(int categoryId);
}
