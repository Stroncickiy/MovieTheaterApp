package com.epam.movies.service;

import com.epam.movies.model.EventStatistics;

import java.util.List;

public interface EventStatisticsService {

    List<com.epam.movies.model.EventStatistics> getAllEventQueries();
    EventStatistics add(EventStatistics statisticsToAdd);
    boolean isStatisticsForEventExists(String name);
    void updateEventStatistics(EventStatistics statistics);
    EventStatistics getStaticsticsForEventByEventName(String eventName);

}
