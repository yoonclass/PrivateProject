package com.jafa.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.error.PasswordMisMatchException;
import com.jafa.member.domain.MemberVO;
import com.jafa.member.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@RequestMapping("/login")
	public String loginPage(HttpServletRequest request, Model model, String logout,
			Authentication authentication, RedirectAttributes rttr) {
		
		String uri = request.getHeader("Referer"); // 로그인 전 사용자가 보던 페이지
		//uri가 존재하고 login과 접근거부를 포함하고 있지 않다면 이전 페이지로 돌아간다.
		if(uri!=null && !uri.contains("/login") && !uri.contains("/accessDenied")) {
			//세션의 prevPage에 uri정보 저장
			request.getSession().setAttribute("prevPage", uri);
		} 
		
		if(logout!=null) {model.addAttribute("logout","로그아웃");}
		return "member/login";
	}
	
	//회원가입================
	@GetMapping("/member/join")
	public String join() {
		return "/member/join";
	}

	@PostMapping("/join")
	public String join(MemberVO vo, RedirectAttributes rttr) {
		memberService.join(vo);
		return "redirect:/";	//메인 페이지로 돌아감
	}
	
	//비밀번호 변경==================
	@GetMapping("/changePwd")
	public String changePwd() {
		return "member/changePwd";
	}

	@PostMapping("/changePwd")
	@ResponseBody
	public ResponseEntity<String> changePwd(
			@RequestParam("memberId") String memberId,
	        @RequestParam("newPwd") String newPwd) {
		try {
	        memberService.changePwd(memberId, newPwd);
	    } catch (PasswordMisMatchException e) {
	        return new ResponseEntity<String>("비밀번호가 일치하지 않음", HttpStatus.UNAUTHORIZED);
	    }
	    return new ResponseEntity<String>("success", HttpStatus.OK);
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
