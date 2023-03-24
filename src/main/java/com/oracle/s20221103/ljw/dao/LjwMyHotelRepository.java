package com.oracle.s20221103.ljw.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.s20221103.dto.ReservationDTO;
import com.oracle.s20221103.dto.RoomDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class LjwMyHotelRepository implements LjwHotelRepository {
	private final SqlSession session;

	@Override
	public List<RoomDTO> findRoomList() {
		List<RoomDTO> result = null;
		try {
			result = session.selectList("roomList");
		} catch (Exception e) {
			log.info("findRoomList() e.getMessage...==>{}",e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("null")
	@Override
	public List<ReservationDTO> findResList() {
		List<ReservationDTO> result = new ArrayList<ReservationDTO>();
		try {
			List<ReservationDTO> resList = session.selectList("resList");
			for(ReservationDTO rs : resList) {
				rs.setResStartdate(rs.getResStartdate().substring(0,10));
				rs.setResEnddate(rs.getResEnddate().substring(0,10));
				result.add(rs);
			}
		} catch (Exception e) {
			log.info("findResList() e.getMessage...==>{}",e.getMessage());
		}
		return result;
	}

	@Override
	public List<RoomDTO> findResRoomList(String resStartdate, String resEnddate) {
			List<RoomDTO> result = null;
			try {
				Map<String, String> param = new HashMap<String, String>();
				param.put("resStartdate", resStartdate);
				param.put("resEnddate", resEnddate);
				result = session.selectList("resRoomList",param);
				System.out.println("result.size() ==> "+result.size());
			} catch (Exception e) {
				log.info("findResRoomList() e.getMessage...==>{}",e.getMessage());
			}
			return result;
	}

}
