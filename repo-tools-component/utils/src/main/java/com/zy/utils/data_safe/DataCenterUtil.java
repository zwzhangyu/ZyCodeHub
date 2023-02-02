package com.zy.utils.data_safe;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.util.Calendar.*;

/***
 *
 *  数据中心相关操作工具整合
 * @author ZhangYu
 * @date 2021/8/21
 */
@Slf4j
public class DataCenterUtil {
    /**
     * 补位字符串
     */
    private static  final String  REPLACE_CODE="0000";

    private static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgE" +
            "AAoGBAKOxdiTIqPg2BPcADTRD0f8q7BG26vMnx5veOsj5ZZF8ly1OPTRE8L" +
            "h3AjHa1y7tPuNDH0vMwIvckE7nnZTWoEOkfMsgcSAkbxxbAhL7A6AjiJ8/H" +
            "kJPwexDHsv3ZXScHxD6WoZ6Vh47oNcOEImZTdW9WgfhT7XLIAIdifCDahIhA" +
            "gMBAAECgYEAiCIi1e6HD32lXWGOmaO5dhNVJlpQRQ4VJByKGO/IN1k9RLFnlx" +
            "uqck6O5bVQ9bbacRg2Y8yRaP9yiBx5YfmoZ5/9sgj2s96ofI8e0jOpP9qpJZ8SR" +
            "5zhpZCwk7gwCMnVbKzXMPd2W8qsFDuSF8iBD+QDSpZr/h6dpvvCOx/8S40CQQDwOIZrWb" +
            "SVbYmwmVpZV6pRr5HaSY2Mh7cIaTZ/7LdJiNCjT33FxBiY5EMLGkvCzmYyuIuSUmzy/kxAow0D0Vb7A" +
            "kEArnISxBJITSN6vbULuBCZpq/tyWwMnUUQtWrgVnn4Y0sJOp9LnZdY4o4jtUzin86JLWY/wGoM2MWD3Ixy3" +
            "RVgkwJAEMiyQ5/h6SrdGvSsBmAEq25r72eCN1ZoKHNJdoiwibfAdBiivgWkflI8iPMOZg8LvvF79v5BVH0GEOITj7e5" +
            "rwJATZwYtzy/B/qh7+atOQ3BnkQlEXjNv+ZrQZs0BaUsGdjXWUpnlMwx0lGUl2OGa7yykQ0tjchgwMxXFvcxXyTUmwJBAL30czvHq1eAWyo+M71Iq4uLelZEiSbGDLdqFlM1WkQp3HSaZ7MclksljV0pggWAtbEGDfyC72CvlVFtshIT8hs=";


    private static final String   PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCjsXYkyKj4NgT3AA00Q9H/KuwRturzJ8eb3jrI+WWRfJctTj00RPC4dwIx2tcu7T7jQx9LzMCL3JBO552U1qBDpHzLIHEgJG8cWwIS+wOgI4ifPx5CT8HsQx7L92V0nB8Q+lqGelYeO6DXDhCJmU3VvVoH4U+1yyACHYnwg2oSIQIDAQAB";




    /***
     *   RSA加密
     * @param input   明文字符串
     * @return java.lang.String  密文
     * @author ZhangYu
     * @date 2021/8/29
     */
    public  static  String rsaEncrypt(String input){
        if (StringUtils.isEmpty(input)){
            return null;
        }
        String result = null;
        try{
            RSA rsa = new RSA(null, PUBLIC_KEY);
            byte[] encrypt = rsa.encrypt(input, KeyType.PublicKey);
            result= Base64.encode(encrypt);
        }catch (Exception e){
            log.error("RSA加密失败,{}",input);
        }
        return result;
    }



    /***
     *   RSA解密
     * @param input 密文
     * @return java.lang.String  原文
     * @author ZhangYu
     * @date 2021/8/29
     */
    public  static  String rsaDecrypt(String input){
        String result=null;
       try{
           RSA rsa = new RSA(PRIVATE_KEY, null);
           byte[] aByte = Base64.decode(input);
           byte[] decrypt = rsa.decrypt(aByte, KeyType.PrivateKey);
           result=  StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8);
       }catch (Exception e){
           log.error("RSA解密失败,{}",input);
       }
       return result;
    }


    public static void main(String[] args) {
        System.out.println(rsaEncrypt("我是一段测试aaaa"));
        System.out.println(rsaDecrypt(rsaEncrypt("我是一段测试aaaa")));

    }





    /**
     * 计算年龄差
     * @param first
     * @param last
     * @return
     */
    public static Integer getDiffYears(Date first, Date last) {
        Integer diff=0;
        try {

           Calendar a = getCalendar(first);
           Calendar b = getCalendar(last);
           diff = b.get(YEAR) - a.get(YEAR);
           if (a.get(MONTH) > b.get(MONTH) ||
                   (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
               diff--;
           }
           return diff;
       }catch (Exception e){
            log.error("计算年龄差错误",e);
        }
        return  diff;
    }


    public static Calendar getCalendar(Date date) throws Exception {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }





    /***
     *  根据出生日期计算年龄
     * @param birthDay 出生日期
     * @return int 年龄
     * @author ZhangYu
     * @date 2021/8/23
     */
    public static  int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();
        //出生日期晚于当前时间，无法计算
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        //当前年份
        int yearNow = cal.get(YEAR);
        //当前月份
        int monthNow = cal.get(MONTH);
        //当前日期
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);
        int yearBirth = cal.get(YEAR);
        int monthBirth = cal.get(MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        //计算整岁数
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                //当前日期在生日之前，年龄减一
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            }else{
                //当前月份在生日之前，年龄减一
                age--;
            } } return age; }



    /**
     * 用SimpleDateFormat计算时间差
     */
    public static int  calculateTimeDiff(Date birthday)  {
        int days=0;
        try {
            /*天数差*/
            long from1 = birthday.getTime();
            long to1 = System.currentTimeMillis();
            //向下取整
            days= (int) Math.floor((to1 - from1) /  (double)(1000 * 60 * 60 * 24));
        }catch (Exception e){
            log.error("数据中心-计算时间差错误",e);
        }
        return days;
    }


    /**
     * 身份证脱敏
     * @param cardNo  身份证号码
     * @param index  开头保留几位数
     * @param end  结尾保留几位数
     * @author ZhangYu
     * @return  脱敏后的身份证
     *
     */
    public static String hideCerCardNum(String cardNo,int index,int end) {
        String  desensitizationIdCard = "";
        try{
            if (StringUtils.isBlank(cardNo)) {
                return desensitizationIdCard;
            }
            desensitizationIdCard= StringUtils.left(cardNo, index).concat(StringUtils.removeStart(
                    StringUtils.leftPad(StringUtils.right(cardNo, end),
                            StringUtils.length(cardNo), "*"), "***"));
        }catch (Exception e){
            log.error("数据中心-身份证脱敏错误",e);
        }
        return  desensitizationIdCard;
    }

}
