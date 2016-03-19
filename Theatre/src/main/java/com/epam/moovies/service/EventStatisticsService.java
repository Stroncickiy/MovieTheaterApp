package com.epam.moovies.service;

import com.epam.moovies.model.EventStatistics;

import java.util.List;

public interface EventStatisticsService {

    List<com.epam.moovies.model.EventStatistics> getAllEventQueries();
    EventStatistics add(EventStatistics statisticsToAdd);
    boolean isStatisticsForEventExists(String name);
    boolean updateEventStatistics(EventStatistics statistics);
    EventStatistics getStaticsticsForEventByEventName(String eventName);

}
