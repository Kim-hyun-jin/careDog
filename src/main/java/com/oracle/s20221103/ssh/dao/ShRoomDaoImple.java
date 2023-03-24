package com.oracle.s20221103.ssh.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.s20221103.dto.HotelDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ShRoomDaoImple implements ShRoomDao {
	private final SqlSession session;
	
	@Override
	public HotelDTO roomChoice(int roomNum) {
		HotelDTO hotel = new HotelDTO();
		System.out.println("ShRoomDaoImple Start..." );
		
		try {
			hotel = session.selectOne("shRoomChoice", roomNum);
			System.out.println("ShRoomDaoImple roomChoice room.getRoomType() -> " + hotel.getRoomType());
			System.out.println("ShRoomDaoImple roomChoice room.getRoomFunction() -> " + hotel.getRoomFunction());
			System.out.println("ShRoomDaoImple roomChoice room.getRoomMent() -> " + hotel.getRoomMent());
			System.out.println("ShRoomDaoImple roomChoice room.getRoomPrice() -> " + hotel.getRoomPrice());
		
		} catch (Exception e) {
			System.out.println("ShRoomDaoImple roomChoice Exception->"+e.getMessage());
		}
		return hotel;
	}
	
}
