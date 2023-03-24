package com.oracle.s20221103.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class BHCorrectionDTO {
	private Long appliNo;			//신청번호
	private Long memberId;			//회원번호(로그인이 선생님일 경우 선생님)
	private Long dogNo;				//반려견 번호
	private String title;			//제목
	private String beforeContent;	//상담 전 내용
	private String status;			//진행상태
	private Long teacherId;			//선생님 번호
	private Date startDate;			//시작일
	private Date finishDate;		//종료일
	private String afterContent;	//상담 후 내용

	//member
	private String memberName;		//회원 이름
	private String tname;			//선생님 이름(로그인이 선생님일 경우 회원이름)
	private String memberCall;		//연락처
	
	//dog
	private String dogName;			//반려견 이름
	
	//Paging
	private int start;
	private int end;
	private Long rn;
	
}
