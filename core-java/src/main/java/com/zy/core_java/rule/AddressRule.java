package com.zy.core_java.rule;

import static com.zy.core_java.rule.RuleConstant.MATCH_ADDRESS_START;

// 具体规则- 例子1
public class AddressRule extends AbstractRule {

    @Override
    public boolean execute(RuleDto dto) {
        System.out.println("AddressRule invoke!");
        if (dto.getAddress().startsWith(MATCH_ADDRESS_START)) {
            return true;
        }
        return false;
    }
}