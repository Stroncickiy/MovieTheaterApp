package com.epam.movies.dao;


import com.epam.movies.model.EventStatistics;

public interface EventStatisticsDAO extends  CommonDAO<EventStatistics> {
    boolean isEventStatisticsExist(String name);

    EventStatistics getByName(String eventName);

}
