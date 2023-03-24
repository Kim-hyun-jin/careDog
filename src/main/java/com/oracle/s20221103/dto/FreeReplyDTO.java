package com.oracle.s20221103.dto;

import lombok.Data;

@Data
public class FreeReplyDTO {

	private Long freeboardNo;
	private Long replyNo;
	private Long id;
	private String replyContent;
	private String replyRegdate;
	
	//읽기전용
	private String username;
	private String role;
	private String memberName;
}
