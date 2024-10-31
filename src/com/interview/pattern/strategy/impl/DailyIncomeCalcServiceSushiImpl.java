package com.interview.pattern.strategy.impl;

import java.math.BigDecimal;

public class DailyIncomeCalcServiceSushiImpl implements DailyIncomeCalcService {

    @Override
    public BigDecimal calDailyIncome() {
        return BigDecimal.TEN;
    }

    @Override
    public String type() {
        return "SUSHI";
    }
}
