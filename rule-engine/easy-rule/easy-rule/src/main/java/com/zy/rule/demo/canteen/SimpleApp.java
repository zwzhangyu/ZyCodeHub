package com.zy.rule.demo.canteen;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;

/*******************************************************
 * Created by ZhangYu on 2024/6/29
 * Description :
 * History   :
 *******************************************************/
public class SimpleApp {

    public static void main(String[] args) {

        Canteen canteen = new Canteen().
                setProductId(1)
                .setType(1);

        // define rules 定义规则
        Rule canteenRule =
                new RuleBuilder()
                        .name("牛肉火锅自营店") // 规则名称
                        .description("productId = 1 && type =1 。文案：牛肉火锅自营店。请从【餐品】开始进行向上申请") // 规则描述
                        .when(facts -> facts.get("productId").equals(1) && facts.get("type").equals(1)) // 规则条件
                        .then(facts -> System.out.println("牛肉火锅自营店。请从【餐品】开始进行向上申请")) // 命中规则后的操作
                        .build();

        // 定义规则集合
        Rules rules = new Rules();
        rules.register(canteenRule);

        // fire rules on known facts  创建执行引擎
        RulesEngineFactory.BizRuleEngine rulesEngine = RulesEngineFactory.buildRuleEngine4Canteen();

        // define facts 定义需要验证的参数
        Facts facts = new Facts();
        facts.put("productId", canteen.getProductId());
        facts.put("type", canteen.getType());

        // 进行规则校验
//        rulesEngine.fire(rules, facts);
    }
}