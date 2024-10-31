package com.interview.pattern.strategy;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class UniversalStrategyService<T1 extends AbstractNamedService<T2>, T2> implements AbstractStrategyService<T1, T2> {

    protected final Map<T2, T1> serviceMap;

    public UniversalStrategyService(List<T1> services) {
        this.serviceMap = services.stream()
                .collect(Collectors.toMap(AbstractNamedService::type, Function.identity()));
    }

    @Override
    public T1 getInstance(T2 name) {
        return Optional.ofNullable(serviceMap.get(name))
                .orElseThrow(() -> new RuntimeException("Unsupported service type: " + name));
    }
}
