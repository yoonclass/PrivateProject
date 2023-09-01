package com.jafa.book_report.controller;

import java.nio.file.AccessDeniedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.book_report.domain.LikeDTO;
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

	private final ReportService reportService;
	
	@GetMapping("/list")
	public void reportList(Model model, Criteria criteria) {
		log.info("reportList");
		model.addAttribute("list", reportService.getList(criteria));
		model.addAttribute("page", new Pagination(criteria, reportService.totalCount(criteria)));
	}
	
	@GetMapping("/get")
	public void get(Long bno, Model model, Criteria criteria) {
		log.info("/컨트롤러 조회");
		log.info(bno);
		log.info(criteria);

		model.addAttribute("report",reportService.get(bno));
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/register")
	public void register() {}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/register")
	public String register(ReportVO report, RedirectAttributes rttr) {
		log.info("register: " + report);
		reportService.register(report);
		rttr.addFlashAttribute("result", report.getBno());
		rttr.addFlashAttribute("operation", "register");
		return "redirect:/book_report/list";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify")
	public String modify(@RequestParam("bno") Long bno, Model model, Criteria criteria, Authentication auth) throws AccessDeniedException {
		log.info("컨트롤러 수정 Get");
		log.info("bno : "+bno);
		log.info("criteria : "+criteria);
		
		ReportVO vo = reportService.get(bno);
		String username = auth.getName();
		if(!vo.getWriter().equals(username) &&
		   !auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
				throw new AccessDeniedException("수정 권한 없음");
		}
		model.addAttribute("report",reportService.get(bno));
		return "book_report/modify";
	}
	
	@PreAuthorize("isAuthenticated() and principal.username== #report.writer")
	@PostMapping("/modify")
	public String modify(ReportVO report, RedirectAttributes rttr, Criteria criteria) {
		log.info("/컨트롤러 수정 완료 : "+report);
		if(reportService.modify(report)) {
			rttr.addFlashAttribute("result", report.getBno());
			rttr.addFlashAttribute("operation", "modify");
		}
		return "redirect:/book_report/list";
	}
	
	@PreAuthorize("isAuthenticated() and principal.username== #report.writer or hasRole('ROLE_ADMIN')")
	@PostMapping("/remove")
	public String remove(ReportVO report, Long bno, RedirectAttributes rttr, Criteria criteria) {
		log.info("(controller)remove : " + bno);
		if(reportService.remove(bno)) {
			rttr.addFlashAttribute("result", bno);
			rttr.addFlashAttribute("operation", "remove");
		}
		rttr.addAttribute("pageNum",criteria.getPageNum());
		rttr.addAttribute("amount",criteria.getAmount());
		return "redirect:/book_report/list"; 
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value="/like",produces="plain/text; charset=utf-8")
	public ResponseEntity<String> hitLike(LikeDTO likeDTO){
		log.info(likeDTO.getId());
		log.info(likeDTO.getBno());
		String message = likeDTO.getBno()+"번 ";
		if(reportService.hitLike(likeDTO)) {
			message += "게시글을 추천하였습니다.";
		} else {
			message += "게시글을 추천 취소하였습니다.";
		}
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	@PostMapping(value="/islike")
	public ResponseEntity<Boolean> isLike(LikeDTO likeDTO){
		return new ResponseEntity<Boolean>(reportService.isLike(likeDTO),HttpStatus.OK);
	}
}