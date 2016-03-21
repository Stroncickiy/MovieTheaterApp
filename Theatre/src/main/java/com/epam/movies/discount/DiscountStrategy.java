package com.epam.movies.discount;

import com.epam.movies.discount.calculator.DiscountCalculator;
import com.epam.movies.discount.calculator.impl.BirthdayStrategyDiscountCalulator;
import com.epam.movies.discount.calculator.impl.Every10TicketDiscountCalculator;
import com.epam.movies.discount.calculator.impl.LuckyWinnerDiscountCalculator;

public enum DiscountStrategy {
    BIRTHDAY_STRATEGY(BirthdayStrategyDiscountCalulator.class), EVERY_10_TICKET(
			Every10TicketDiscountCalculator.class), LUCKY_WINNER(
					LuckyWinnerDiscountCalculator.class), NO_DISCOUNT(null);

	DiscountStrategy(Class<?> discountCalculatorClass) {
		this.discountCalculatorClass = discountCalculatorClass;
	}

	private Class<?> discountCalculatorClass;

	public DiscountCalculator getDiscountCalculator() {
		return DiscountStrategiesHoder.getStrategyImplementationByClass(discountCalculatorClass);
	}

}
