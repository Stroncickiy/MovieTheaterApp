package com.epam.movies.aspects;

import com.epam.movies.model.DiscountProvision;
import com.epam.movies.model.Ticket;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DiscountAspect {

    @Pointcut("execution(* com.epam.movies.service.DiscountService.evaluateAndSetTicketPrice(..))")
    public void getDiscountPointcut() {

    }

    @AfterReturning(pointcut = "getDiscountPointcut()", returning = "returnedValue")
    public void countAfterCalling(JoinPoint joinPoint, Object returnedValue) {
        DiscountProvision discountProvision = new DiscountProvision((Ticket) returnedValue);


    }

}
