package com.oracle.s20221103.domain;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.oracle.s20221103.dto.CusNoticeDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;




@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "CUSNOTICE_SEQ_GEN",               // 객체  SEQ
sequenceName = "CUS_NOTICE_SEQ", // DB SEQ
initialValue = 10,
allocationSize =1 )
public class CusNotice {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "CUSNOTICE_SEQ_GEN")
	private Long cusNo;
	
//	//OneToOne
	//@Column(columnDefinition = "string default '10'")
	private Long id;
	
	
	@Column(length = 500, nullable = false)
	private String title;
	
	@Column(length = 500, nullable = false)
	private String content;
	
	//JPA Timestamp 사용하기 위해서 자료형 Date으로 변경
//	@Temporal(TemporalType.TIMESTAMP)
//	@DateTimeFormat(pattern = "yy/MM/dd")
//	@Column(name = "regdate")

	private Date regdate;
	

	private String attachPath;
	
	
	
	
	public Long getCusNo() {
		return cusNo;
	}
	public void setCusNo(Long cusNo) {
		this.cusNo = cusNo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	

	
	
	public CusNotice(CusNoticeDTO cusNoticeDTO) {
		this.cusNo = cusNoticeDTO.getCusNo();
		this.id = cusNoticeDTO.getId();
		this.title = cusNoticeDTO.getTitle();
		this.content = cusNoticeDTO.getContent();
		this.regdate = cusNoticeDTO.getRegdate();
		
	}
	public String getAttachPath() {
		return attachPath;
	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}



	
	
}
