package com.epam.movies.model;

public class EventStatistics {
    private Long id;
    private String name;
    private Integer queriedByName;
    private Integer priceQueried;
    private Integer ticketsBoocked;


    public EventStatistics(){
        queriedByName = 0;
        priceQueried = 0;
        ticketsBoocked  = 0;
    }

    public EventStatistics(String name){
        this.name = name;
        queriedByName = 0;
        priceQueried = 0;
        ticketsBoocked  = 0;
    }


    public String getName() {
        return name;
    }

    public void  incrementNameQueried() {
         queriedByName = queriedByName.intValue()+1;
    }

    public void  incrementPriceQueried() {
        priceQueried = priceQueried.intValue()+1;
    }

    public void  incrementTicketsBoocked() {
        ticketsBoocked = ticketsBoocked.intValue()+1;
    }


    public Integer getQueriedByName() {
        return queriedByName;
    }

    public Integer getPriceQueried() {
        return priceQueried;
    }

    public Integer getTicketsBoocked() {
        return ticketsBoocked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQueriedByName(Integer queriedByName) {
        this.queriedByName = queriedByName;
    }

    public void setPriceQueried(Integer priceQueried) {
        this.priceQueried = priceQueried;
    }

    public void setTicketsBoocked(Integer ticketsBoocked) {
        this.ticketsBoocked = ticketsBoocked;
    }
}
