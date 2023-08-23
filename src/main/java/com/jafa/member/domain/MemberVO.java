package com.jafa.member.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberVO {
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String memberEmail;
	private boolean enabled;  //회원 계정 활성화 상태 여부를 판별
	
	private List<AuthVO> authList;
	
	private String profileImagePath; // 프로필 이미지 파일 경로
}
