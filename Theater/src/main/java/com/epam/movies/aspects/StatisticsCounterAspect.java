package com.epam.movies.aspects;

import com.epam.movies.model.EventStatistics;
import com.epam.movies.model.Ticket;
import com.epam.movies.service.EventStatisticsService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatisticsCounterAspect {

	@Autowired
	private EventStatisticsService eventStatisticsService;

	@Pointcut("execution(* com.epam.movies.service.EventService.getByName(..))")
	public void callEventPointCut() {

	}

	@Pointcut("execution(* com.epam.movies.service.BookingService.getTicketPrice(..))")
	public void queryPricePointCut() {

	}

	@Pointcut("execution(* com.epam.movies.service.BookingService.bookTicket(..))")
	public void bookTicketPointCut() {

	}

	@AfterReturning("callEventPointCut()")
	public void countAfterCalling(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		String eventName = (String) args[0];
		if (eventStatisticsService.isStatisticsForEventExists(eventName)) {
			EventStatistics eventStatistics = new EventStatistics(eventName);
			eventStatistics.incrementNameQueried();
			eventStatisticsService.add(eventStatistics);
		} else {
			EventStatistics staticsticsForEventByEventName = eventStatisticsService
					.getStaticsticsForEventByEventName(eventName);
			staticsticsForEventByEventName.incrementNameQueried();
			eventStatisticsService.updateEventStatistics(staticsticsForEventByEventName);

		}
	}

	@AfterReturning("queryPricePointCut()")
	public void countAfterQueringOfPrice(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		Ticket ticket = (Ticket) args[0];
		String eventName = ticket.getEvent().getName();
		if (eventStatisticsService.isStatisticsForEventExists(eventName)) {
			EventStatistics eventStatistics = new EventStatistics(eventName);
			eventStatistics.incrementPriceQueried();
			eventStatisticsService.add(eventStatistics);
		} else {
			EventStatistics staticsticsForEventByEventName = eventStatisticsService
					.getStaticsticsForEventByEventName(eventName);
			staticsticsForEventByEventName.incrementPriceQueried();
			eventStatisticsService.updateEventStatistics(staticsticsForEventByEventName);

		}
	}

	@AfterReturning("bookTicketPointCut()")
	public void countAfterBookingOfTicket(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		Ticket ticket = (Ticket) args[0];
		String eventName = ticket.getEvent().getName();
		if (eventStatisticsService.isStatisticsForEventExists(eventName)) {
			EventStatistics eventStatistics = new EventStatistics(eventName);
			eventStatistics.incrementTicketsBoocked();
			eventStatisticsService.add(eventStatistics);
		} else {
			EventStatistics statisticsForEventByEventName = eventStatisticsService
					.getStaticsticsForEventByEventName(eventName);
			statisticsForEventByEventName.incrementTicketsBoocked();
			eventStatisticsService.updateEventStatistics(statisticsForEventByEventName);

		}
	}

}
