package com.oracle.s20221103.asecurity;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.oracle.s20221103.dto.MemberDTO;

import lombok.Data;
//****** Authentication 객체에 저장할 수 있는 유일한 타입 *******

//login이라는 주소가 호출이 되면, Security가 낚아 채서 login 진행
//login 진행 완료후 Security Session 생성 (Security ContextHolder에 정보 저장)
//Security Session -> Authentication -> UserDetails[PrincipalDetails]

@SuppressWarnings("serial")
@Data
public class PrincipalDetails implements UserDetails {
	private MemberDTO user;
	
	public PrincipalDetails(MemberDTO user) {
		this.user=user;
		System.out.println("principal details 생성자 getUsername ==>"+user.getUsername());
		System.out.println("principal details 생성자 getUsername ==>"+user.getId());
	}
	
	@Override
	//해당 user의 권한을 리턴하는곳.
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<GrantedAuthority>();
		collect.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				System.out.println("getAuthorities getAuthority user get.Role() ==>"+user.getRole());
				return user.getRole();
			}
		});
		return collect;
	}
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
	public boolean getPremiumChk() {
		return user.getPremiumChk()==1;
	}

	@Override
	public boolean isAccountNonExpired() { //추가적으로 조건을 걸어 계정접근제한을 걸 수 있는 기본 메소드들.
		if(user.getMemberEnabled()==0) return false;
		else return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
		
	}
	
	
	public Long getId() {
		return user.getId();
	}


}
