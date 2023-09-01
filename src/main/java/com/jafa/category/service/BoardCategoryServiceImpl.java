package com.jafa.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jafa.category.domain.BoardCategory;
import com.jafa.category.repository.BoardCategoryRepository;

@Service
public class BoardCategoryServiceImpl implements BoardCategoryService {

	@Autowired
	private BoardCategoryRepository categoryRepository;
	
	@Override
	public List<BoardCategory> getCategoryById(int categoryId) {
		return categoryRepository.readCategory(categoryId);
	}
}
