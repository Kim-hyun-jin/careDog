package com.oracle.s20221103.ssh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.s20221103.dto.HotelDTO;
import com.oracle.s20221103.dto.MapperVO;
import com.oracle.s20221103.dto.ResPayDTO;
import com.oracle.s20221103.dto.ResVO;
import com.oracle.s20221103.dto.ReservationDTO;
import com.oracle.s20221103.ssh.dao.ShResDao;
import com.oracle.s20221103.ssh.dao.ShResPayDao;
import com.oracle.s20221103.ssh.dao.ShRoomDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShHotelServiceImpl implements ShHotelService {
	private final ShRoomDao shRoomDao;
	private final ShResDao shResDao;
	private final ShResPayDao shResPayDao;
	
	@Override
	public HotelDTO roomchoice(int roomNum) {
		System.out.println("ShHotelServiceImpl roomchoice Start...");
		HotelDTO hotel = shRoomDao.roomChoice(roomNum);
		System.out.println("ShHotelServiceImpl roomchoice room.getRoomType() -> " + hotel.getRoomType());
		
		return hotel;
	}

	@Override
	public List<ReservationDTO> resList(ReservationDTO reservationDTO) {
		System.out.println("ShHotelServiceImpl resList...");
		List<ReservationDTO> listRes = shResDao.resList(reservationDTO);
		
		return listRes;
	}

	@Override
	public List<ResPayDTO> resPayList(ResPayDTO resPayDTO) {
		System.out.println("ShHotelServiceImpl resPayList ...");
		List<ResPayDTO> listResPay = shResPayDao.resPayList(resPayDTO);
		
		return listResPay;
	}

	@Override
	public void resInsert(ResVO resVO) {
		System.out.println("ShHotelServiceImpl resInsert...");
		shResDao.resInsert(resVO);
	}

	@Override
	public List<ReservationDTO> resList(Long id, ReservationDTO reservationDTO) {
		System.out.println("ShHotelServiceImpl resList...");
		MapperVO mapperVO = new MapperVO();
		mapperVO.setId(id);
		mapperVO.setReservationDTO(reservationDTO);
		
		List<ReservationDTO> listRes = shResDao.resList(mapperVO);
		
		return listRes;
	}

	@Override
	public long resNoSelect() {
		long resNum = shResDao.resNoSelect();
		
		return resNum;
	}

	@Override
	public void resUpdate(Long resNo) {
		shResDao.resUpdate(resNo);
	}

	@Override
	public int totalRes() {
		int total = shResDao.totalRes();
		
		return total;
	}

	@Override
	public int totalRes(Long id) {
		int total = shResDao.totalRes(id);
		
		return total;
	}

	@Override
	public int totalResPay() {
		int total = shResPayDao.totalResPay();
		
		return total;
	}

	@Override
	public List<ReservationDTO> resList(Long id, String period, ReservationDTO reservationDTO) {
		MapperVO mapperVO = new MapperVO();
		mapperVO.setId(id);
		mapperVO.setPeriod(period);
		mapperVO.setReservationDTO(reservationDTO);
		
		List<ReservationDTO> listRes = shResDao.resListSearch(mapperVO);
		
		return listRes;
	}

	@Override
	public List<ReservationDTO> resList(ReservationDTO reservationDTO, String name) {
		MapperVO mapperVO = new MapperVO();
		mapperVO.setReservationDTO(reservationDTO);
		mapperVO.setName(name);
		
		List<ReservationDTO> listRes = shResDao.resListSearchAdmin(mapperVO);
		
		return listRes;
	}

	@Override
	public int totalRes(String name) {
		int total = shResDao.totalRes(name);
		
		return total;
	}
	
}
