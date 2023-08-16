package com.jafa.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.jafa.member.domain.MemberVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomUser extends User{
	private static final long serialVersionUID = 5933271706009259803L;
	
	private MemberVO memberVO;
	
	// 스프링 시큐리티의 User 클래스 생성자를 호출하는 생성자로, 사용자명(username), 패스워드(password), 그리고 권한(authorities) 정보를 받아서 초기화
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	//MemberVO 객체를 받아서 사용자명, 패스워드, 권한 정보를 추출하여 User 클래스 생성자를 호출하고, CustomUser 객체의 필드에 MemberVO를 저장
	public CustomUser(MemberVO memberVO) {
		super(memberVO.getMemberId(), memberVO.getMemberPwd(), //객체에서 사용자ID,PWD 가져옴
				memberVO.getAuthList().stream()				   //객체에서 사용자 권한목록 가져옴
				//스트림으로 변환하여 getAuth 메서드 이용해 권한 이름 추출
				.map(auth -> new SimpleGrantedAuthority(auth.getAuth())) //각 권한을 SimpleGrantedAuthority 객체로 변환하고
				.collect(Collectors.toList()));				//리스트로 수집함
		this.memberVO = memberVO; 
	}
}
