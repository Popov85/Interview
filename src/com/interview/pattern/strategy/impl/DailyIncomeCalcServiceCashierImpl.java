package com.interview.pattern.strategy.impl;

import java.math.BigDecimal;

public class DailyIncomeCalcServiceCashierImpl implements DailyIncomeCalcService {

    @Override
    public BigDecimal calDailyIncome() {
        return BigDecimal.ONE;
    }

    @Override
    public String type() {
        return "CASHIER";
    }
}
