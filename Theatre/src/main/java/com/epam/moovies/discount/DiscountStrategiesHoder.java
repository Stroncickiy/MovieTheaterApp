package com.epam.moovies.discount;

import com.epam.moovies.discount.calculator.DiscountCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Component
public class DiscountStrategiesHoder {

    @Autowired
    private List<DiscountCalculator> calculators;

    @PostConstruct
    public void init() {
        for (DiscountCalculator calculator : calculators) {
            addStrategy(calculator.getClass(), calculator);
        }
    }

    private static HashMap<Class<?>, DiscountCalculator> strategies = new HashMap<>();

    public static void addStrategy(Class<?> calculatorClass, DiscountCalculator calculatorImplementation) {
        strategies.put(calculatorClass, calculatorImplementation);
    }

    public static DiscountCalculator getStrategyImplementationByClass(Class<?> strategyClass) {
        return strategies.get(strategyClass);
    }

}
