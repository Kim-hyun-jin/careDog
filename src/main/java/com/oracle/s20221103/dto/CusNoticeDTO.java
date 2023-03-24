package com.oracle.s20221103.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.lang.Nullable;

import com.oracle.s20221103.domain.CusNotice;
import com.oracle.s20221103.khj.dto.NoticeAttachVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CusNoticeDTO {

	private Long cusNo;
	private Long id;
	private String title;
	private String content;
	@Nullable
	private Date regdate; // 저장할 때 포맷터로 date
	//@Nullable
	//private String keyword; //검색 키워드
	private List<NoticeAttachVO> attachList;
	

	public CusNoticeDTO(CusNotice cusNotice) {
		this.cusNo = cusNotice.getCusNo();
		this.id = cusNotice.getId();
		this.title = cusNotice.getTitle();
		this.content = cusNotice.getContent();
		this.regdate = cusNotice.getRegdate();
		
	
	}

	public CusNoticeDTO(Long cusNo2, Long id2, String title2, String content2, Date regdate2) {
		
	}
	
}
