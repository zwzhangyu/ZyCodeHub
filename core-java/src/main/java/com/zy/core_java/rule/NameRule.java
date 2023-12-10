package com.zy.core_java.rule;

import static com.zy.core_java.rule.RuleConstant.MATCH_ADDRESS_START;
import static com.zy.core_java.rule.RuleConstant.MATCH_NATIONALITY_START;

public class NameRule extends AbstractRule {

    @Override
    protected <T> T convert(RuleDto dto) {
        NationalityRuleDto nationalityRuleDto = new NationalityRuleDto();
        if (dto.getAddress().startsWith(MATCH_ADDRESS_START)) {
            nationalityRuleDto.setNationality(MATCH_NATIONALITY_START);
        }
        return (T) nationalityRuleDto;
    }


    @Override
    protected <T> boolean executeRule(T t) {
        System.out.println("NationalityRule invoke!");
        NationalityRuleDto nationalityRuleDto = (NationalityRuleDto) t;
        if (nationalityRuleDto.getNationality().startsWith(MATCH_NATIONALITY_START)) {
            return true;
        }
        return false;
    }
}