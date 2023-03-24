package com.oracle.s20221103.jhy.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.s20221103.dto.QADTO;
import com.oracle.s20221103.jhy.service.QAService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class QaController {
	private final QAService qaService;

//	@RequestMapping(value = "/customerService/QA/QA")
//	public String QAMain(Model model) {
//		String status = "답변 미완료";
//		List<QADTO> qaDtoList = qaService.getQaList(status);
//		System.out.println("qaDtoList size->"+ qaDtoList.size());
//		model.addAttribute("qaList", qaDtoList);
//		return "customerService/QA/QA";
//	}
}
