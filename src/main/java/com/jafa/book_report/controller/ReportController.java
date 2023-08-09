package com.jafa.book_report.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.book_report.domain.ReportVO;
import com.jafa.book_report.service.ReportService;
import com.jafa.common.Criteria;
import com.jafa.common.Pagination;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/book_report")
@RequiredArgsConstructor
public class ReportController {

	private final ReportService service;
	
	@GetMapping("/list")
	public void reportList(Model model, Criteria criteria) {
		log.info("reportList");
		model.addAttribute("list", service.getList(criteria));
		model.addAttribute("page", new Pagination(criteria, service.totalCount()));
	}
	
	@GetMapping("/get")
	public void get(Long bno, Model model, Criteria criteria) {
		log.info("/컨트롤러 조회");
		log.info(bno);
		log.info(criteria);
		model.addAttribute("report",service.get(bno));
	}
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(ReportVO report, RedirectAttributes rttr) {
		log.info("register: " + report);
		service.register(report);
		rttr.addFlashAttribute("result", report.getBno());
		rttr.addFlashAttribute("operation", "register");
		return "redirect:/book_report/list";
	}
	
	@GetMapping("/modify")
	public void modify(@RequestParam("bno") Long bno, Model model, Criteria criteria) {
		log.info("컨트롤러 수정 Get");
		log.info(bno);
		log.info(criteria);
		model.addAttribute("report",service.get(bno));
	}
	
	@PostMapping("/modify")
	public String modify(ReportVO report, RedirectAttributes rttr, Criteria criteria) {
		log.info("/컨트롤러 수정 완료 : "+report);
		if(service.modify(report)) {
			rttr.addFlashAttribute("result", report.getBno());
			rttr.addFlashAttribute("operation", "modify");
		}
		rttr.addAttribute("pageNum",criteria.getPageNum());
		rttr.addAttribute("amount",criteria.getAmount());
		return "redirect:/book_report/list";
	}
	
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes rttr, Criteria criteria) {
		log.info("(controller)remove : " + bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", bno);
			rttr.addFlashAttribute("operation", "remove");
		}
		rttr.addAttribute("pageNum",criteria.getPageNum());
		rttr.addAttribute("amount",criteria.getAmount());
		return "redirect:/book_report/list"; 
	}
}