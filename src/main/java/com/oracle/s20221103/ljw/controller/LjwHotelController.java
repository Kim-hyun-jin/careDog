package com.oracle.s20221103.ljw.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.oracle.s20221103.dto.ReservationDTO;
import com.oracle.s20221103.dto.RoomDTO;
import com.oracle.s20221103.ljw.service.LjwHotelService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LjwHotelController {
	private final LjwHotelService hs;
	
	@GetMapping("/hotel/reservation/main")
	public String hotelRreservationMain2(Model model) {
		log.info("hotelRreservationMain2() start...");
		List<RoomDTO> roomList = hs.findRoomList();
		List<ReservationDTO> resList = hs.findResList();
		model.addAttribute("resList", resList);
		model.addAttribute("roomList", roomList);
		return "hotel/reservation/main";
	}
	@ResponseBody
	@RequestMapping("/hotel/reservation/main/ajaxRoomList")
	public List<RoomDTO> ajaxResRoomList(String resStartdate, String resEnddate) {
		System.out.println(resEnddate+resStartdate);
		List<RoomDTO> result = hs.findResRoomList(resStartdate, resEnddate);
		return result;
	}
}
