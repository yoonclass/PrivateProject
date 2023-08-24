package com.jafa.member.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberAttachVO {
	private String uuid; 
	private String uploadPath; 
	private String fileName; 
	private Long mno;
}
