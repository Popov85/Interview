package com.interview.pattern.strategy;

public interface AbstractStrategyService <T1 extends AbstractNamedService, T2>{

    T1 getInstance(T2 name);
}
