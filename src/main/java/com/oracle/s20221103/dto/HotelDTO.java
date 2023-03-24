package com.oracle.s20221103.dto;

import lombok.Data;

@Data
public class HotelDTO {
	private int roomNum;
	private String roomType;
	private String roomMent;
	private String roomCondition;
	
//	추가요소(room)
	private int roomPrice;
	private String roomFunction;
	private int limitSize;
}
