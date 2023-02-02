package com.zw.jjwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.testng.annotations.Test;
import sun.misc.BASE64Encoder;

import javax.crypto.SecretKey;

public class JwtTestor {


    /***
     * 测试创建jwt
     * @author ZhangYu
     * @date 2021/5/29
     */
    @Test
    public  void createJwt(){
        //私钥字符串,注意秘钥长度需要超过一定的位数，否则无法生成
        String key = "1234567890qwertyuifddsdsdsds";
        //先对明文密码做BASE64编码
        String base64 = new BASE64Encoder().encode(key.getBytes());
        //根据生成的BASE64编码字符串生成秘钥对象,会根据base64长度自动选择相应的 HMAC 算法
        SecretKey secretKey = Keys.hmacShaKeyFor(base64.getBytes());
        //利用JJWT生成Token
        //载荷数据
        String data = "{\"username\":admin}";
        String jwt = Jwts.builder().setSubject(data).signWith(secretKey).compact();
        System.out.println(jwt);
    }


    /***
     * 校验及提取JWT数据
     * @author ZhangYu
     * @date 2021/5/29
     */
    @Test
    public void checkJwt(){
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJ1c2VybmFtZVwiOmFkbWlufSJ9.J0wq20FvgJEJW-h9HymIb7Ed-GByAJJiYVw-nV9Y1i8";
        //私钥
        String key = "1234567890qwertyuifddsdsdsds";
        //1.对秘钥做BASE64编码
        String base64 = new BASE64Encoder().encode(key.getBytes());
        //2.生成秘钥对象,会根据base64长度自动选择相应的 HMAC 算法
        SecretKey secretKey = Keys.hmacShaKeyFor(base64.getBytes());
        //3.验证Token
        try {
            //生成JWT解析器
            JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey).build();
            //解析JWT
            Jws<Claims> claimsJws = parser.parseClaimsJws(jwt);
            //得到载荷中的用户数据
            String subject = claimsJws.getBody().getSubject();
            System.out.println(subject);
        }catch (JwtException e){
            //所有关于Jwt校验的异常都继承自JwtException
            System.out.println("Jwt校验失败");
            e.printStackTrace();
        }
    }
}

