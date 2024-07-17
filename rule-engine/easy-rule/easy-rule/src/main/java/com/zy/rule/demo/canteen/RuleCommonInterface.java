package com.zy.rule.demo.canteen;

import lombok.Data;
import lombok.experimental.Accessors;

/*******************************************************
 * Created by ZhangYu on 2024/6/29
 * Description :
 * History   :
 *******************************************************/
@Data
@Accessors(chain = true)
public class RuleCommonInterface {

    //规则描述
    private String description;
}