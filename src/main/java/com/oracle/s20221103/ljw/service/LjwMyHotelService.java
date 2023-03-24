package com.oracle.s20221103.ljw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.s20221103.dto.ReservationDTO;
import com.oracle.s20221103.dto.RoomDTO;
import com.oracle.s20221103.ljw.dao.LjwHotelRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
@Transactional
public class LjwMyHotelService implements LjwHotelService {
	private final LjwHotelRepository hr;
	@Override
	public List<RoomDTO> findRoomList() {
		return hr.findRoomList();
	}
	@Override
	public List<ReservationDTO> findResList() {
		return hr.findResList();
	}
	@Override
	public List<RoomDTO> findResRoomList(String resStartdate, String resEnddate) {
		return hr.findResRoomList(resStartdate,resEnddate);
	}
}
