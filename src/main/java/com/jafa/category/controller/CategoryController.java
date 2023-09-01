package com.jafa.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.jafa.category.domain.BoardCategory;
import com.jafa.category.service.BoardCategoryService;

@Controller
public class CategoryController {

	@Autowired
	private BoardCategoryService categoryService;
	
//	public String getCategory(@PathVariable int categoryId, Model model) {
//		List<BoardCategory> categories = categoryService.getCategoryById(categoryId);
//		model.addAttribute("categories",categories);
//		return ""
//	}
}
