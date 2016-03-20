package com.epam.moovies.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.moovies.model.EventStatistics;
import com.epam.moovies.model.Ticket;
import com.epam.moovies.service.EventStatisticsService;


@Component
public class StatisticsCounterAspect {


    @Autowired
    private EventStatisticsService eventStatisticsService;


    @Pointcut("execution(* com.epam.moovies.service.EventService.getByName(..))")
    public void callEventPointCut(){

    }


    @Pointcut("execution(* com.epam.moovies.service.BookingService.getTicketPrice(..))")
    public void queryPricePointCut(){

    }


    @Pointcut("execution(* com.epam.moovies.service.BookingService.bookTicket(..))")
    public void bookTicketPointCut(){

    }


    @AfterReturning("callEventPointCut()")
    public void countAfterCalling(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String eventName = (String) args[0];
        if(!eventStatisticsService.isStatisticsForEventExists(eventName)){
            EventStatistics eventStatistics  = new EventStatistics(eventName);
            eventStatistics.incrementNameQueried();
             eventStatisticsService.add(eventStatistics);
        }else {
            EventStatistics staticsticsForEventByEventName = eventStatisticsService.getStaticsticsForEventByEventName(eventName);
            staticsticsForEventByEventName.incrementNameQueried();
            eventStatisticsService.updateEventStatistics(staticsticsForEventByEventName);

        }
    }
    @AfterReturning("queryPricePointCut()")
    public void countAfterQueringOfPrice(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Ticket ticket = (Ticket) args[0];
        String eventName = ticket.getEvent().getName();
        if(!eventStatisticsService.isStatisticsForEventExists(eventName)){
            EventStatistics eventStatistics  = new EventStatistics(eventName);
            eventStatistics.incrementPriceQueried();
            eventStatisticsService.add(eventStatistics);
        }else {
            EventStatistics staticsticsForEventByEventName = eventStatisticsService.getStaticsticsForEventByEventName(eventName);
            staticsticsForEventByEventName.incrementPriceQueried();
            eventStatisticsService.updateEventStatistics(staticsticsForEventByEventName);

        }
    }


    @AfterReturning("bookTicketPointCut()")
    public void countAfterBookingOfTicket(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Ticket ticket = (Ticket) args[0];
        String eventName = ticket.getEvent().getName();
        if(!eventStatisticsService.isStatisticsForEventExists(eventName)){
            EventStatistics eventStatistics  = new EventStatistics(eventName);
            eventStatistics.incrementTicketsBoocked();
            eventStatisticsService.add(eventStatistics);
        }else {
            EventStatistics staticsticsForEventByEventName = eventStatisticsService.getStaticsticsForEventByEventName(eventName);
            staticsticsForEventByEventName.incrementTicketsBoocked();
            eventStatisticsService.updateEventStatistics(staticsticsForEventByEventName);

        }
    }

}
