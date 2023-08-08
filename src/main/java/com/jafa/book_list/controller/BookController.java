package com.jafa.book_list.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/book_list")
public class BookController {

	public BookController() {
		System.out.println("BookController 빈 생성");
	}
	
	@GetMapping("/list")
	public String bookList() {
		System.out.println("bookList");
		return "book_list/list";
	}
}