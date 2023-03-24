package com.oracle.s20221103.asecurity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	public CustomAccessDeniedHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		String uri = request.getHeader("Referer");
		if(uri !=null && !uri.contains("/login") && !uri.contains("/signup")) {
			request.getSession().setAttribute("prevPage", uri);
		} else {
			request.getSession().setAttribute("prevPage", "/");
		}
		request.setAttribute("errMsg",accessDeniedException.getMessage());
		request.getRequestDispatcher("/main/denied").forward(request, response);

	}

}
