package com.zy.core_java.rule;

// 规则抽象
public interface BaseRule {

    boolean execute(RuleDto dto);
}