package com.oracle.s20221103.ljw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.s20221103.dto.MemberDTO;
import com.oracle.s20221103.ljw.service.LjwLoginService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LjwLoginController {
	private final LjwLoginService ls;
	private final BCryptPasswordEncoder passwordEncoder;
	private final JavaMailSender mailSender;
	@ResponseBody
	@PostMapping("/ajaxJoinMember")
	public String ajaxJoinMember(MemberDTO member) {
		log.info("ajaxJoinMember() start...");
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		int result       = ls.insertMember(member);
		String resultStr = Integer.toString(result) ;
		return resultStr;
	}
	@ResponseBody
	@RequestMapping("/ajaxJoinChkMemberName")
	public String ajaxJoinChkMemberName(String username) {
		int result = ls.dupChkUsername(username);
		String resultStr = Integer.toString(result) ;
		return resultStr;
	}
	@GetMapping("/main/findJoinId")
	public String findJoinId() {
		log.info("findJoinId() start...");
		log.info("findJoinId() finish...");
		return "main/findJoinId";
	}
	@GetMapping("/main/findJoinPass")
	public String findJoinPass() {
		log.info("findJoinPass() start...");
		log.info("findJoinPass() finish...");
		return "main/findJoinPass";
	}
	@ResponseBody
	@PostMapping("/main/ajaxEnableChk")
	public List<MemberDTO> ajaxEnableChk(MemberDTO member) {
		log.info("ajaxEnableChk() start...");
		List<MemberDTO> result = ls.findJoinData(member);
		System.out.println(result.size());
		log.info("ajaxEnableChk() finish...");
		return result;
	}
	@ResponseBody
	@PostMapping("/main/ajaxPassChkSendCode")
	public Map<String, Object> ajaxPassChkSendCode(MemberDTO member) {
		log.info("ajaxPassChkSendCode() start...");
		MemberDTO result = ls.emailChk(member);
		String vReturn = "-1";
		Map<String, Object> results = new HashMap<String, Object>();
		if(result!=null) {
			log.info("mailSending...");
			String tomail = member.getMemberEmail();
			String title = "[CareDog] 비밀번호변경 인증코드 이메일";
			System.out.println(tomail);
			String setfrom = "tesla0111@naver.com";
			try {
				vReturn="0";
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				messageHelper.setFrom(setfrom);		//보내는사람 : 생략시 정삭작동 X
				messageHelper.setTo(tomail);		//받는사람 : 이메일
				messageHelper.setSubject(title);	//제목 : 생략가능.
				String tempPassword = (int) (Math.random()*999999)+1 +"";
				while (tempPassword.length()<=6) {
					tempPassword = "0"+tempPassword;
					if(tempPassword.length()>5) break;
				}
				messageHelper.setText("사용자 인증 코드입니다. 코드입력창에 해당 코드["+tempPassword+"]를 넣어주세요." ); //메일내용
				System.out.println("인증코드 :" + tempPassword);
				mailSender.send(message);
				vReturn="1";
				results.put("CODE", tempPassword);
			} catch (Exception e) {
				log.info("mailSending e.getmessage : "+e.getMessage());
			}
		}
		results.put("data", vReturn);
		log.info("ajaxPassChkSendCode() finish...");
		return results;
	}
	@ResponseBody
	@PostMapping("/main/ajaxChkCode")
	public String ajaxChkCode(MemberDTO member) {
		log.info("ajaxChkCode() start...");
		String result = "0";
		MemberDTO codeChk = ls.mailCodeChk(member);
		if(codeChk!=null) result = "1";
		log.info("ajaxChkCode() finish..."+result);
		return result;
	}
	@PostMapping("/main/resetPassForm")
	public String resetPassForm(HttpServletRequest request,MemberDTO member) {
		log.info("resetPassForm() start...");
		request.getSession().setAttribute("resetPassUser", member);
		log.info("resetPassForm() finish...");
		return "main/resetPassForm";
	}
	@ResponseBody
	@PostMapping("/main/resetPass")
	public String resetPass(HttpServletRequest request,String password) {
		log.info("resetPass() start...");
		String result ="0";
		MemberDTO resetPass = (MemberDTO) request.getSession().getAttribute("resetPassUser");
		resetPass.setPassword(passwordEncoder.encode(password));
		int modCnt = ls.passReset(resetPass);
		result = Integer.toString(modCnt);
		log.info("resetPass() finish...");
		return result;
	}
	@ResponseBody
	@GetMapping("/signupForm/ajaxMailSend")
	public String ajaxMailSend(String email) {
		log.info("ajaxMailSend() start...");
		String result ="";
		log.info("mailSending...");
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom("tesla0111@naver.com");															//보내는사람 : 생략시 정삭작동 X
			messageHelper.setTo(email);																				//받는사람 : 이메일
			messageHelper.setSubject("[CareDog] 회원가입 이메일 인증코드");											//제목 : 생략가능.
			String tempPassword = (int) (Math.random()*999999)+1 +"";
			while (tempPassword.length()<=6) {
				tempPassword = "0"+tempPassword;
				if(tempPassword.length()>5) break;
			}
			messageHelper.setText("사용자 인증 코드입니다. 코드입력창에 해당 코드["+tempPassword+"]를 넣어주세요." ); //메일내용
			System.out.println("인증코드 :" + tempPassword);
			mailSender.send(message);
			result=tempPassword;
		} catch (Exception e) {
			log.info("mailSending e.getmessage : "+e.getMessage());
		}
		log.info("ajaxMailSend() finish...");
		return result;
	}
}
