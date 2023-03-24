package com.oracle.s20221103.ssh.dao;

import java.util.List;

import com.oracle.s20221103.dto.MapperVO;
import com.oracle.s20221103.dto.ResVO;
import com.oracle.s20221103.dto.ReservationDTO;

public interface ShResDao {

	List<ReservationDTO> resList(ReservationDTO reservationDTO);
	List<ReservationDTO> resList(MapperVO mapperVO);

	void resInsert(ResVO resVO);
	long resNoSelect();
	void resUpdate(Long resNo);
	int totalRes();
	int totalRes(Long id);
	int totalRes(String name);
	
	List<ReservationDTO> resListSearch(MapperVO mapperVO);
	List<ReservationDTO> resListSearchAdmin(MapperVO mapperVO);


}
