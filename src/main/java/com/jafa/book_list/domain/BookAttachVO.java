package com.jafa.book_list.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BookAttachVO {
	private String uuid; 
	private String uploadPath; 
	private String fileName; 
	private boolean fileType;
	private Long bno; 
}
