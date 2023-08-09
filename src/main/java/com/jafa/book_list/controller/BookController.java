package com.jafa.book_list.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.book_list.domain.BookVO;
import com.jafa.book_list.service.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/book_list")
@RequiredArgsConstructor
public class BookController {

	private final BookService service;
	
	@GetMapping("/list")
	public void bookList(Model model) {
		log.info("reportList");
		model.addAttribute("list", service.getList());
	}
	
	@GetMapping("/get")
	public void get(Long bno, Model model) {
		log.info("/컨트롤러 조회");
		log.info(bno);
		model.addAttribute("book",service.get(bno));
	}
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(BookVO book, RedirectAttributes rttr) {
		log.info("register: " + book);
		service.register(book);
		rttr.addFlashAttribute("result", book.getBno());
		rttr.addFlashAttribute("operation", "register");
		return "redirect:/book_list/list";
	}
	
	@GetMapping("/modify")
	public void modify(@RequestParam("bno") Long bno, Model model) {
		log.info("컨트롤러 수정 Get");
		log.info(bno);
		model.addAttribute("book",service.get(bno));
	}
	
	@PostMapping("/modify")
	public String modify(BookVO book, RedirectAttributes rttr) {
		log.info("/컨트롤러 수정 완료 : "+book);
		if(service.modify(book)) {
			rttr.addFlashAttribute("result", book.getBno());
			rttr.addFlashAttribute("operation", "modify");
		}
		return "redirect:/book_report/list";
	}
	
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes rttr) {
		log.info("(controller)remove : " + bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", bno);
			rttr.addFlashAttribute("operation", "remove");
		}
		return "redirect:/book_report/list"; 
	}
}