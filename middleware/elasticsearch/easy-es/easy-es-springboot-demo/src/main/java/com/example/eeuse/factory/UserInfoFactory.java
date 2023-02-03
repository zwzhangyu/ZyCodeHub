package com.example.eeuse.factory;

import com.apifan.common.random.source.*;
import com.example.eeuse.model.UserInfo;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserInfoFactory {

    static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public static UserInfo generateUserInfo() {
        UserInfo userInfo = UserInfo.builder()
                .content(OtherSource.getInstance().randomChineseSentence())
                .userName(PersonInfoSource.getInstance().randomChineseName())
                .salary(BigDecimal.valueOf(NumberSource.getInstance().randomInt(1, 10000)))
                .address(AreaSource.getInstance().randomAddress())
                .creator(PersonInfoSource.getInstance().randomChineseName())
                .phone(PersonInfoSource.getInstance().randomChineseMobile())
                .birthday(DateTimeSource.getInstance().randomDate(2000, "yyyy-MM-dd"))
                .gmtCreate(DateTimeSource.getInstance().randomPastTime(LocalDateTime.now(), 100000000).format(df))
                .build();
        log.info("UserInfo工厂生成数据：{}", userInfo);
        return userInfo;
    }

    public static List<UserInfo> generateUserInfoList() {
        int randomSize = NumberSource.getInstance().randomInt(1, 10);
        List<UserInfo> userInfos = new ArrayList<>(randomSize);
        for (int i = 0; i < randomSize; i++) {
            userInfos.add(generateUserInfo());
        }
        return userInfos;
    }

    public static List<UserInfo> generateUserInfoList(int size) {
        List<UserInfo> userInfos = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            userInfos.add(generateUserInfo());
        }
        return userInfos;
    }


}
