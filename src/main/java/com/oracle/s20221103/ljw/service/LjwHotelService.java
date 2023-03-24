package com.oracle.s20221103.ljw.service;

import java.util.List;

import com.oracle.s20221103.dto.ReservationDTO;
import com.oracle.s20221103.dto.RoomDTO;


public interface LjwHotelService {
	List<RoomDTO> findRoomList();

	List<ReservationDTO> findResList();

	List<RoomDTO> findResRoomList(String resStartdate, String resEnddate);
}
