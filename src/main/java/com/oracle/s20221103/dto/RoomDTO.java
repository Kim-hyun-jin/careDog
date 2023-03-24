package com.oracle.s20221103.dto;

import lombok.Data;

@Data
public class RoomDTO {
	private String roomType;
	private int roomPrice;
	private String roomFunction;
	private int limitSize;
}
