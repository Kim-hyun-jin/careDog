package com.oracle.s20221103.asecurity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {


		log.warn("Login success");
		
		List<String> roleNames = new ArrayList<String>();
		
		authentication.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});
		
		//PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		log.warn("ROLE NAMES: "+ roleNames);


//		임재우 12/02 로그인이전페이지 이동 기능 구현 ====================================================================
		String prevPage = (String) request.getSession().getAttribute("prevPage");
		String uri = "/";
		if(prevPage!=null) {
			request.getSession().removeAttribute("prevPage");
			uri = prevPage;
		}
		response.sendRedirect(uri);
//		임재우 12/02 로그인이전페이지 이동 기능 구현 ====================================================================
	}

}
