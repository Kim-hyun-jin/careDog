package com.oracle.s20221103.dto;

import lombok.Data;

@Data
public class CriteriaDTO {

	private int pageNum;
	private int amount;
	
	public CriteriaDTO() {
		this(1,10);
	}

	public CriteriaDTO(int pageNum, int amount) {
	
		this.pageNum = pageNum;
		this.amount = amount;
	}
}
