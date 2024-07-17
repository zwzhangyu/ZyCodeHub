package com.zy.rule.demo.canteen;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.RuleListener;

/**
 * @author chaird
 * @create 2022-11-26 1:54
 */
public class YmlRulesListener implements RuleListener {

    private String entityType ;




    @Override
    public boolean beforeEvaluate(Rule rule, Facts facts) {
        return true;
    }

    @Override
    public void afterEvaluate(Rule rule, Facts facts, boolean evaluationResult) {

    }

    @Override
    public void beforeExecute(Rule rule, Facts facts) {

    }

    @Override
    public void onSuccess(Rule rule, Facts facts) {

        //获取需要验证的对象，比如 【餐厅、套餐、菜品 implement RuleCommonInterface】
        RuleCommonInterface ruleCommon = facts.get(entityType);
        //把规则信息进行一个赋值
        ruleCommon.setDescription(rule.getDescription());


    }

    @Override
    public void onFailure(Rule rule, Facts facts, Exception exception) {

    }

    public YmlRulesListener(){

    }

    public YmlRulesListener(String entityType) {
        this.entityType = entityType;
    }
}

