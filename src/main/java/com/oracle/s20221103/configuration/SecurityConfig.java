package com.oracle.s20221103.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.oracle.s20221103.asecurity.CustomLoginSuccessHandler;
import com.oracle.s20221103.asecurity.CustomAccessDeniedHandler;

import lombok.RequiredArgsConstructor;


@Configuration
//스프링MVC와 스프링 시큐리티를 결합하는 용도의 어노테이션
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {
	private final CustomLoginSuccessHandler customLoginSuccessHandler;//임재우 12/02 로그인시 이전페이지 이동기능 추가
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public UserDetailsService userDetailsService;
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) 
			throws Exception {
		
		http.csrf().disable()
			.authorizeHttpRequests()
//			antMatchers : 해당 경로에 권한체크 ->실패시 로그인 페이지로 (개발 중 주석처리)
//			.antMatchers("/**/member/**").authenticated()
			.antMatchers("/**/reservation/**").authenticated()
			.antMatchers("/**/**/myQuestion").authenticated()
//			.antMatchers("/**/teacher/**").hasAnyRole("ADMIN","TEACHER")
			.antMatchers("/**/admin/**").hasAnyRole("ADMIN")
			.antMatchers("/**/BHCorrection/**").hasAnyRole("MEMBER", "TEACHER")
			.and()
			.exceptionHandling().accessDeniedHandler(accessDeniedHandler())
			.and()
//			로그인페이지 설정 및 로그인시 설정(handler)추가.
			.formLogin()
			.loginPage("/main/login")
			.successHandler(customLoginSuccessHandler) //임재우 12/02 로그인시 이전페이지 이동기능 추가
			//.failureHandler()
			.permitAll();
		

		//로그아웃
		http.logout()
		//.logoutUrl("/customLogout")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID", "remember-me");
	
		return http.build();
	}
	
	//정적자원에는 필터적용하지 않게 처리
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		
		System.out.println("---------web configure----------");
		
		return (web) -> web.ignoring().requestMatchers(
											PathRequest.toStaticResources().atCommonLocations());
	}
	
	//로그인 성공 후 처리 핸들러
	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	
	//접근제한 에러 핸들러
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	//@Bean 인메모리 테스트
	public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.withUsername("user1")
                .password(encodePwd().encode("user1"))
                .roles("USER")
                .build();
         UserDetails user2 = User.withUsername("user2")
                .password(encodePwd().encode("user2"))
                .roles("USER")
                .build();
         UserDetails admin = User.withUsername("admin")
                .password(encodePwd().encode("admin"))
                .roles("ADMIN")
                .build();
            return new InMemoryUserDetailsManager(user1, user2, admin);
	}
	

	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(encodePwd());
	    return authProvider;
	}
}
