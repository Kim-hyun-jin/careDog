package com.oracle.s20221103.dto;

import lombok.Data;

@Data
public class MapperVO {
	private Long id;
	private String period;
	private String name;
	private ReservationDTO reservationDTO;
}
