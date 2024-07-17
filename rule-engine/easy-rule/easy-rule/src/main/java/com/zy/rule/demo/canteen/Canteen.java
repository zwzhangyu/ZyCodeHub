package com.zy.rule.demo.canteen;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;


@Data
@Accessors(chain = true)
public class Canteen  extends RuleCommonInterface {

    private Integer cantennId;

    private String name;

    private Integer productId;

    private Integer type;

    private Map<String, String> prpoerties = new HashMap();

    @Override
    public String toString() {
        return "Canteen{" +
                "cantennId=" + cantennId +
                ", name='" + name + '\'' +
                ", productId=" + productId +
                ", type=" + type +
                ", ruleDesc=" + super.getDescription() +
                ", prpoerties=" + prpoerties +
                '}';
    }
}