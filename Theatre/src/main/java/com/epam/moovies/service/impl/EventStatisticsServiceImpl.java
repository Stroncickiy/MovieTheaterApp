package com.epam.moovies.service.impl;

import com.epam.moovies.dao.EventStatisticsDAO;
import com.epam.moovies.model.EventStatistics;
import com.epam.moovies.service.EventStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventStatisticsServiceImpl implements EventStatisticsService {

	@Autowired
	private EventStatisticsDAO eventStatisticsDAO;

	@Override
	public List<EventStatistics> getAllEventQueries() {
		return eventStatisticsDAO.getAll();
	}

	@Override
	public EventStatistics add(EventStatistics statisticsToAdd) {
		return eventStatisticsDAO.add(statisticsToAdd);
	}

	@Override
	public boolean isStatisticsForEventExists(String name) {
		return eventStatisticsDAO.isEventStatisticsExist(name);
	}

	@Override
	public boolean updateEventStatistics(EventStatistics statistics) {
		return eventStatisticsDAO.update(statistics);
	}

	@Override
	public EventStatistics getStaticsticsForEventByEventName(String eventName) {
		return eventStatisticsDAO.getByName(eventName);
	}

}
