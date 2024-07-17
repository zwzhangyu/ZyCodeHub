package com.zy.aop.field_syn;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/*******************************************************
 * Created by ZhangYu on 2024/5/8
 * Description : 会员认证证件类型
 * History   :
 *******************************************************/
@Getter
public enum CardTypeEnumDesc {

    ID("0", "身份证"), DL("1", "驾驶证"), TQC("2", "从业资格证"), CAR("3", "车辆信息"),

    CART("4", "车头行驶证"), TL("5", "挂车行驶证"), RTC("6", "道路运输证"), HEAD("7", "车头照"), FACE("8", "人脸");

    private String type;

    private String desc;

    CardTypeEnumDesc(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    /**
     * 获取配置描述列表
     */
    public static List<String> listDesc() {
        List<String> configList = new ArrayList<>();
        for (CardTypeEnumDesc type : values()) {
            configList.add(type.getDesc());
        }
        return configList;
    }


    /**
     * 检查证件是否存在
     *
     * @return
     */
    public static boolean checkCardExist(String desc) {
        if (StringUtils.isBlank(desc)) {
            return false;
        }
        for (CardTypeEnumDesc type : values()) {
            if (StringUtils.equals(type.getDesc(), desc)) {
                return true;
            }
        }
        return false;
    }
}
