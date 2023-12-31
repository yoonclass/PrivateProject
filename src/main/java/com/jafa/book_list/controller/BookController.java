package com.jafa.book_list.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.book_list.domain.BookAttachVO;
import com.jafa.book_list.domain.BookVO;
import com.jafa.book_list.service.BookService;
import com.jafa.common.Criteria;
import com.jafa.common.Pagination;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/book_list")
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;
	
	@GetMapping("/list")
	public void bookList(Model model, Criteria criteria) {
		log.info("bookList");
		model.addAttribute("list", bookService.getList(criteria));
		model.addAttribute("page", new Pagination(criteria, bookService.totalCount(criteria)));
	}
	
	@GetMapping("/get")
	public void get(Long bno, Model model, Criteria criteria) {
		log.info("/컨트롤러 조회");
		log.info(bno);
		model.addAttribute("book",bookService.get(bno));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/register")
	public void register() {}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/register")
	public String register(BookVO book, RedirectAttributes rttr) {
		
		log.info(book);
		log.info(book.getAttachList());
		bookService.register(book);
		rttr.addFlashAttribute("result", book.getBno());
		rttr.addFlashAttribute("operation", "register");
		return "redirect:/book_list/list";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/modify")
	public void modify(@RequestParam("bno") Long bno, Model model, Criteria criteria) {
		log.info("컨트롤러 수정 Get");
		log.info(bno);
		model.addAttribute("book",bookService.get(bno));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/modify")
	public String modify(BookVO book, RedirectAttributes rttr, Criteria criteria) {
		List<BookAttachVO> attachList = book.getAttachList();
		log.info(book);
		if(attachList!=null) {
			List<BookAttachVO> insertList = attachList.stream()
					.filter(attach -> attach.getBno()==null).collect(Collectors.toList());
				log.info("새로 추가 : " + insertList);
				List<BookAttachVO> delList = attachList.stream()
						.filter(attach -> attach.getBno()!=null).collect(Collectors.toList());
				log.info("삭제 목록 : " + delList);
		}	
			
			if(bookService.modify(book)) {
				rttr.addFlashAttribute("result", book.getBno());
				rttr.addFlashAttribute("operation", "modify");
			}
			return "redirect:/book_list/list"+criteria.getListLink();
		}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes rttr, Criteria criteria) {
		log.info("(controller)remove : " + bno);
		if(bookService.remove(bno)) {
			rttr.addFlashAttribute("result", bno);
			rttr.addFlashAttribute("operation", "remove");
		}
		rttr.addAttribute("pageNum",criteria.getPageNum());
		rttr.addAttribute("amount",criteria.getAmount());
		return "redirect:/book_list/list"; 
	}
	
	@GetMapping("/getAttachList")
	@ResponseBody
	public ResponseEntity<List<BookAttachVO>> getAttachList(Long bno){
		return new ResponseEntity<List<BookAttachVO>>(bookService.getAttachList(bno), HttpStatus.OK);
	}
	
	@GetMapping("/getAttachFileInfo")
	@ResponseBody
	public ResponseEntity<BookAttachVO> getAttach(String uuid){
		return new ResponseEntity<>(bookService.getAttach(uuid),HttpStatus.OK); 
	}
	
	/*
	@GetMapping("/latestBooks")
	public String latestBooks(Model model){
		List<BookVO> latestBooks = bookService.getLatestBooks();
		model.addAttribute("latestBooks",latestBooks);
		return "redirect:/";
	}
	*/
	
	
}