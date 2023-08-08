package com.jafa.book_report.domain;

import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Alias("report")
public class ReportVO {
	private Long bno;
	private String title;
	private String content;
	private String writer;
	
	private int replyCnt;
	private int likeReport;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime regDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime updateDate;
}
