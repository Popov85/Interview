package com.interview.pattern.strategy.impl;

import java.util.Arrays;

public class Application {

    public static void main(String[] args) {

        //Cashiers
        DailyIncomeCalcServiceCashierImpl dailyIncomeCalcServiceCashier = new DailyIncomeCalcServiceCashierImpl();
        //Couriers
        DailyIncomeCalcServiceCourierImpl dailyIncomeCalcServiceCourier = new DailyIncomeCalcServiceCourierImpl();
        //Sushi
        DailyIncomeCalcServiceSushiImpl dailyIncomeCalcServiceSushi = new DailyIncomeCalcServiceSushiImpl();

        DailyIncomeCalcServiceStrategy dailyIncomeCalcServiceStrategy =
                new DailyIncomeCalcServiceStrategy(Arrays.asList(dailyIncomeCalcServiceCashier, dailyIncomeCalcServiceCourier, dailyIncomeCalcServiceSushi));

        DailyIncomeCalcService instance = dailyIncomeCalcServiceStrategy.getInstance("SUSHI");

        System.out.println("For employee = "+instance.type()+ ", calc income = "+instance.calDailyIncome());

    }
}
