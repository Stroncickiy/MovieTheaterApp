package com.epam.moovies.aspects;

import com.epam.moovies.model.DiscountProvision;
import com.epam.moovies.model.Ticket;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DiscountAspect {

    @Pointcut("execution(* com.epam.moovies.service.DiscountService.evaluateAndSetTicketPrice(..))")
    public void getDiscountPointcut() {

    }

    @AfterReturning(pointcut = "getDiscountPointcut()",returning = "returnedValue")
    public void countAfterCalling(JoinPoint joinPoint,Object returnedValue) {
        Ticket ticket = (Ticket) returnedValue;
        new DiscountProvision(ticket);

    }


}
