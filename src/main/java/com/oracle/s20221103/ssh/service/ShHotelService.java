package com.oracle.s20221103.ssh.service;

import java.util.List;

import com.oracle.s20221103.dto.HotelDTO;
import com.oracle.s20221103.dto.ResPayDTO;
import com.oracle.s20221103.dto.ResVO;
import com.oracle.s20221103.dto.ReservationDTO;

public interface ShHotelService {

	HotelDTO roomchoice(int roomNum);

	List<ReservationDTO> resList(ReservationDTO reservationDTO);
	List<ReservationDTO> resList(Long id, ReservationDTO reservationDTO); // 오버로딩
	List<ReservationDTO> resList(Long id, String period, ReservationDTO reservationDTO); // 오버로딩
	List<ReservationDTO> resList(ReservationDTO reservationDTO, String name); // 오버로딩

	List<ResPayDTO> resPayList(ResPayDTO resPayDTO);

	void resInsert(ResVO resVO);

	long resNoSelect();

	void resUpdate(Long resNo);

	int totalRes();
	int totalRes(Long id);
	int totalRes(String name);

	int totalResPay();




}
