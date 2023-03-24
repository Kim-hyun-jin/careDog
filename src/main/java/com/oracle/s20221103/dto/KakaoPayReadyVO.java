package com.oracle.s20221103.dto;

import java.util.Date;

import lombok.Data;

@Data
public class KakaoPayReadyVO {
	private String tid, next_redirect_pc_url;
    private Date created_at;
    
    // 필요 추가
    private String partner_order_id, partner_user_id;
    private AmountVO amount;
    private String item_name;
}
