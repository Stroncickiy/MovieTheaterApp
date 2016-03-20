package com.epam.moovies.aspects;

import com.epam.moovies.model.Ticket;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
@Aspect
public class LuckyWinnerAspect {
	private Random random = new Random();

	@Pointcut("execution(* com.epam.moovies.service.DiscountService.evaluateAndSetTicketPrice(..))")
	public void bookTicketPointCut() {

	}

	@Before("bookTicketPointCut()")
	public void tryToMakeTicketLucky(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		Ticket ticket = (Ticket) args[0];
		if (random.nextInt(10) == 7) {
			ticket.makeLucky();
		}
	}

}
