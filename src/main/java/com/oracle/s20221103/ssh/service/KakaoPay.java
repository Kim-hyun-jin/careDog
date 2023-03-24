package com.oracle.s20221103.ssh.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.oracle.s20221103.asecurity.PrincipalDetails;
import com.oracle.s20221103.dto.AmountVO;
import com.oracle.s20221103.dto.HotelDTO;
import com.oracle.s20221103.dto.KakaoPayApprovalVO;
import com.oracle.s20221103.dto.KakaoPayReadyVO;
import com.oracle.s20221103.dto.ResVO;

import lombok.extern.java.Log;

@Service
@Log
public class KakaoPay {

    private static final String HOST = "https://kapi.kakao.com";
    
    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;
    
    public String kakaoPayReady(HotelDTO hotel, Long diffDays,
    							String userId) {
    	// RestAPI 호출 이후 응답을 받을 때까지 기다리는 동기 방식
        RestTemplate restTemplate = new RestTemplate();
 
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        // 권한						admin key
        headers.add("Authorization", "KakaoAK 394da79b0b926dcb63faf95b6e687ecd");
        
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        
        int difD = diffDays.intValue();
        int rp = hotel.getRoomPrice()*difD;
        System.out.println(rp);
        String price = String.valueOf(rp);
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", "1000");
        params.add("partner_user_id", userId);
        params.add("item_name", hotel.getRoomType());
        params.add("quantity", String.valueOf(difD));
        params.add("total_amount", price);
        params.add("tax_free_amount", "100");
        params.add("approval_url", "http://localhost:9091/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:9091/kakaoPayCancel");
        params.add("fail_url", "http://localhost:9091/kakaoPaySuccessFail");
 
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
 
        try {
        	// postForObject POST 요청을 보내고 객체로 결과를 반환 받는다.
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);
            
            kakaoPayReadyVO.setPartner_order_id("1000");
            kakaoPayReadyVO.setPartner_user_id(userId);
            AmountVO amount = new AmountVO();
            amount.setTotal(rp);
            kakaoPayReadyVO.setAmount(amount);
            
            log.info("" + kakaoPayReadyVO);
            
            return kakaoPayReadyVO.getNext_redirect_pc_url();
 
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return "/pay";
        
    }
    
    public KakaoPayApprovalVO kakaoPayInfo(String pg_token) {
    	 
        log.info("KakaoPayInfoVO............................................");
        log.info("-----------------------------");
        
        RestTemplate restTemplate = new RestTemplate();
 
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK 394da79b0b926dcb63faf95b6e687ecd");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
 
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyVO.getTid());
        params.add("partner_order_id", kakaoPayReadyVO.getPartner_order_id());
        params.add("partner_user_id", kakaoPayReadyVO.getPartner_user_id());
        params.add("pg_token", pg_token);
        params.add("total_amount", String.valueOf(kakaoPayReadyVO.getAmount().getTotal()));
        
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        
        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
            // 날짜 변경
            Calendar cal = Calendar.getInstance();
            cal.setTime(kakaoPayApprovalVO.getApproved_at());
            cal.add(Calendar.HOUR, -9);
            kakaoPayApprovalVO.setApproved_at(cal.getTime());
            
            System.out.println("kakaopayA -> " + kakaoPayApprovalVO.getApproved_at());
            
            log.info("" + kakaoPayApprovalVO);
          
            return kakaoPayApprovalVO;
        
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }
	
}
