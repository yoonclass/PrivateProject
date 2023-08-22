package com.jafa.member.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.error.PasswordMisMatchException;
import com.jafa.member.domain.MemberVO;
import com.jafa.member.service.MailSendService;
import com.jafa.member.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	MailSendService mailSendService;
	
	@RequestMapping("/login")
	public String loginPage(HttpServletRequest request, Model model, String logout,
			Authentication authentication, RedirectAttributes rttr) {
		
		String uri = request.getHeader("Referer"); // 로그인 전 사용자가 보던 페이지

		//uri가 존재하고 login과 접근거부를 포함하고 있지 않다면 이전 페이지로 돌아간다.
		if(uri!=null && !uri.contains("/login") && !uri.contains("/accessDenied")) {
			request.getSession().setAttribute("prevPage", uri);
		} 
		
//		인증정보가 존재한다면~
		if (authentication!=null) {
	        rttr.addFlashAttribute("duplicateLogin", "이미 로그인 중입니다.");
	        return "redirect:/"; // 또는 다른 페이지로 이동
	    }
		
		if(logout!=null) model.addAttribute("logout","로그아웃");
			return "member/login";
		}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping({"/myPage","/myPage/{path}"})
	public String myPage(Model model, Principal principal,
			@PathVariable(required = false) String path) {
		String memberId = principal.getName();
		if(path==null) {
			MemberVO memberVO = memberService.read(memberId);
			model.addAttribute("vo",memberVO);
			return "member/myPage";
		}
		return "member/"+path;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@PostMapping(value = "/member/changePwd", produces = "application/text; charset=utf-8")
	@ResponseBody
	public ResponseEntity<String> changePwd(MemberVO memberVO,
	        @RequestParam String currentPwd, @RequestParam String newPwd) {
	    try {
	        Map<String, String> memberMap = new HashMap<>();
	        memberMap.put("memberId", memberVO.getMemberId());
	        memberMap.put("newPwd", newPwd);
	        memberMap.put("currentPwd", currentPwd);
	        
	        memberService.changePwd(memberMap);
	    } catch (PasswordMisMatchException e) {
	        return new ResponseEntity<String>("비밀번호가 일치하지 않음",HttpStatus.UNAUTHORIZED);
	    }
	    return new ResponseEntity<String>("success",HttpStatus.OK);
	}
	
	//회원가입================
	//메일 체크
	@GetMapping("/mailCheck")
	@ResponseBody
	public String mailCheck(String email) {
		return mailSendService.joinEmail(email);
	}
	
	// 약관동의 
	@GetMapping("/join/step1")
	public String step1() {
		return "member/step1";
	}
	
	//이메일 인증 및 가입 작성
	@PostMapping("/join/step2")
	public String step2(MemberVO memberVO) {
		return "member/join";
	}
	
	//회원가입 처리
	@PostMapping("/member/join")
	public String join(MemberVO vo, RedirectAttributes rttr) {
		memberService.join(vo);
		return "redirect:/";	//메인 페이지로 돌아감
	}

	@GetMapping("/join/step2")
	public String joinForm() {
		return "member/step1";
	}
	
	// 아이디 중복체크
	@PostMapping("/member/idCheck")
	@ResponseBody
	public ResponseEntity<Boolean> idDuplicateCheck( String memberId){
		System.out.println(memberId);
		MemberVO vo = memberService.read(memberId);
		return vo == null ?
			new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK)
			: new ResponseEntity<>(Boolean.FALSE,HttpStatus.OK);
	}
	

	//Id, Pwd 찾기
	@GetMapping("/findMemberInfo")
	public String findMemberInfo() {
		return "member/findMemberInfo";
	}

	@PostMapping("/findMemberInfo")
	@ResponseBody
	public String findMemberInfo(String email) {
		String message = null;
		return null;
	}
	@GetMapping("/accessDenied")
	public String accessDenided() {
		return "accessError";
	}
}
