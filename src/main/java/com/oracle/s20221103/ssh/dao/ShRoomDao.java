package com.oracle.s20221103.ssh.dao;

import com.oracle.s20221103.dto.HotelDTO;

public interface ShRoomDao {

	HotelDTO roomChoice(int roomNum);

}
