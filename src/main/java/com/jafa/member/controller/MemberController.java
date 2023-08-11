package com.jafa.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("/member")
@Log4j
public class MemberController {

	@Autowired
	MemberService memberService;
	
	//회원가입================
	@GetMapping("/join")
	public String join() {
		return "member/join";
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
}
