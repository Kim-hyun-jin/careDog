package com.oracle.s20221103.asecurity;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oracle.s20221103.dto.MemberDTO;
import com.oracle.s20221103.ljw.dao.LjwLoginRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
//DB에 담긴 사용자 인증정보와 비교하기 위해 UserDetailsService에 사용자 정보를 넘겨준다.

//*****		Security 설정에서 loginProcessingUrl("/login")  (securityConfiguration)		*****
//login 요청이 오면 자동으로 UserDetailsService Type으로 
//IOC되어 있는  loadUserByUsername Method가 실행(약속)
//Security session( 내부-> Authentication(내부->userDetails) )
public class PrincipalDetailsService implements UserDetailsService {
	private final LjwLoginRepository ljwLoginRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername() start...");
		MemberDTO user = ljwLoginRepository.findUser(username);
		if(user==null) return null;
		else {
			user.setPremiumChk(ljwLoginRepository.checkPremium(user.getId()));
			return new PrincipalDetails(user);
		}
	}

}
