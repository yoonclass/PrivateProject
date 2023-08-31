package com.jafa.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jafa.book_list.domain.BookVO;
import com.jafa.book_list.service.BookService;
import com.jafa.book_report.domain.ReportVO;
import com.jafa.book_report.service.ReportService;

@Controller
public class HomeController {

	@Autowired
	BookService bookService; 
	
	@Autowired
	ReportService reportService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<BookVO> latestBooks = bookService.getLatestBooks();
		List<ReportVO> rankReports = reportService.getRankList();
		
		model.addAttribute("latestBooks",latestBooks);
		model.addAttribute("rankReports",rankReports);
		return "home";
	}
}
