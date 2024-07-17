package com.zy.rule.demo.canteen;

import lombok.Data;


import java.io.FileReader;

/*******************************************************
 * Created by ZhangYu on 2024/6/29
 * Description :
 * History   :
 *******************************************************/

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.reader.YamlRuleDefinitionReader;


/**
 * @author chaird
 * @create 2022-11-26 13:02
 */
public class RulesEngineFactory {

    /**
     * 构建食堂规则。特殊
     *
     * @return
     */
    public static BizRuleEngine buildRuleEngine4Canteen() {
        String entityType = "canteen";
        String rulePath = "/Users/zhangyu/code/zy/github/ZyCodeHub/rule-engine/easy-rule/easy-rule/src/main/resources/canteenRule.yml";
        return buildRuleEngine(entityType, rulePath);
    }

    public static void main(String[] args) {
        buildRuleEngine4Canteen();
    }

    // 可以有N个
    public static BizRuleEngine buildRuleEngine4MealGroup() {

        String entityType = "mealGroup";
        String reulePath = "xxxxx";
        // return buildRuleEngine(entityType, reulePath);
        return null;
    }

    private static BizRuleEngine buildRuleEngine(String entityType, String rulePath) {

        BizRuleEngine bizRuleEngine = new BizRuleEngine(entityType, rulePath);

        return bizRuleEngine;
    }

    @Data
    public static class BizRuleEngine {

        private String entityType;

        private MVELRuleFactory ruleFactory;

        private DefaultRulesEngine rulesEngine;

        private Rules rules;

        public BizRuleEngine(String entityType, String rulePath) {

            try {
                this.entityType = entityType;

                ruleFactory = new MVELRuleFactory(new YamlRuleDefinitionReader());
                rules = ruleFactory.createRules(new FileReader(rulePath));

                rulesEngine = new DefaultRulesEngine();
                rulesEngine.registerRuleListener(new YmlRulesListener(entityType));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void fire(RuleCommonInterface input) {

            Facts facts = new Facts();
            facts.put(entityType, input);
            rulesEngine.fire(rules, facts);
        }
    }
}


