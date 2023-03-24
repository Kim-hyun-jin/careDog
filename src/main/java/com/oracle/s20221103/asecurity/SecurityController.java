 package com.oracle.s20221103.asecurity;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SecurityController {

//	@GetMapping("/error/accessError")
//	public void accessDenied(Authentication auth, Model model) {
//		model.addAttribute("errormsg", "접근권한이 없습니다");
//	}
		
	@RequestMapping("/main/denied")
	public String accDenied(HttpServletRequest request, Model model) {
		log.info("accDenied() start...");
		model.addAttribute("prevPage",request.getSession().getAttribute("prevPage"));
		model.addAttribute("errMsg",request.getAttribute("errMsg"));
		return "main/denied";
	}
	
	@GetMapping("/logout")
	public void logoutGet() {
		System.out.println("custom logout");
		
	}
	

}
