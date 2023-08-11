package com.jafa.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
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
}
