package com.jafa.book_report.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jafa.member.domain.MemberAttachVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReplyVO {
	private Long bno; //게시물번호
	private String id;
	private Long rno; //댓글번호
	private String reply; //댓글내용
	private String replyer; //댓글 작성자
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime replyDate; //댓글등록일
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime updateDate;//댓글수정일
}
