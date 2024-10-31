package com.interview.pattern.strategy.impl;

import java.math.BigDecimal;

public class DailyIncomeCalcServiceCourierImpl implements DailyIncomeCalcService {

    @Override
    public BigDecimal calDailyIncome() {
        return BigDecimal.TWO;
    }

    @Override
    public String type() {
        return "COURIER";
    }
}
