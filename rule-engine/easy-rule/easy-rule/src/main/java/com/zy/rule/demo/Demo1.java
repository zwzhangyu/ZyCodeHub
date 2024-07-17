package com.zy.rule.demo;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;

/*******************************************************
 * Created by ZhangYu on 2024/6/29
 * Description :
 * History   :
 *******************************************************/
public class Demo1 {

    public void rule(String mess) {
        Rules rules = new Rules();
        rules.register(new RuleBuilder()
                .when((facts -> facts.get("mess").equals("A")))
                .then(facts -> {
                    System.out.println("更新B");
                })
                .build());
        RulesEngine rulesEngine = new DefaultRulesEngine();
        Facts facts = new Facts();
        facts.put("mess", mess);
        rulesEngine.fire(rules, facts);
    }

    public static void main(String[] args) {
        new Demo1().rule("A");
        new Demo1().rule("B");
    }
}
