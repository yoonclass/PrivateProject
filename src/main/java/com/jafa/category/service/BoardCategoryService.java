package com.jafa.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jafa.category.domain.BoardCategory;
import com.jafa.category.repository.BoardCategoryRepository;

public interface BoardCategoryService {
	List<BoardCategory> getCategoryById(int categoryId);
}
