package com.zy.test.log_parse;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/*******************************************************
 * Created by ZhangYu on 2024/10/19
 * Description :
 * History   :
 *******************************************************/
@Data
@Builder
public class CardBeanDto {

    private String uuid;

    private String idCard;

    private String mobileNo;

    private  String realName;

    private String tqcImgUrl;


    private String cartBadgeNo;

    private String cartColor;

    private String cartFrontImg;

    private String cartBackImg;

    private String cardType;


    private String idFrontImg;

    private String idBackImg;


    private String timestamp;
    private Date timestampObj;
    private String tid;




}
