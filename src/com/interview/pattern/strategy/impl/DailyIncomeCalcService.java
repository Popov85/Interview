package com.interview.pattern.strategy.impl;

import com.interview.pattern.strategy.AbstractNamedService;

import java.math.BigDecimal;

public interface DailyIncomeCalcService extends AbstractNamedService<String> {
    BigDecimal calDailyIncome();

    @Override
    String type();
}
