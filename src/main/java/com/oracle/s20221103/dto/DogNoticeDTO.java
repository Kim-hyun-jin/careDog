package com.oracle.s20221103.dto;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DogNoticeDTO {
	
	private Long 	noticeNo;  /* 알림장글번호 */
	private Long 	id;     /*회원아이디 */
	private String 	title;     /* 제목 */
	private String 	content;  /* 내용 */
	private String 	feel;     /* 기분 */
	private String 	health;   /* 건강 */
	private String 	meal;      /* 식사량 */
	private String 	condition;  /* 배변상태 */
	private Date 	regdate;  /* 알림장작성일 */
	private Long 	dogNo;     /* 반려견번호 */
	private Long 	teacherId;  /* 선생님아이디 */
	private Long 	rn;		/* 목록번호 */



	
	private String 	dogImg;        // 강아지사진
	private String 	dogName;       // 강아지이름
	private String 	tname;         // 선생님이름
	private String 	userName;     //아이디
	private String 	memberName;   // 회원이름
	private String  dogImgName;     //강아지사진이름
	private String  dogImgPath;     //강아지사진위치
	
	// 조회용
	private String search;   	
	private String keyword;
	private String pageNum;  
	private int start; 		 	
	private int end;
	



	
	




}
