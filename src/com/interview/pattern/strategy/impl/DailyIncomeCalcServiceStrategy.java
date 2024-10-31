package com.interview.pattern.strategy.impl;

import com.interview.pattern.strategy.UniversalStrategyService;

import java.util.List;

public class DailyIncomeCalcServiceStrategy extends UniversalStrategyService<DailyIncomeCalcService, String> {

    public DailyIncomeCalcServiceStrategy(List<DailyIncomeCalcService> calcServices) {
        super(calcServices);
    }
}
