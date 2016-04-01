package com.epam.movies.service.impl;

import com.epam.movies.dao.EventStatisticsDAO;
import com.epam.movies.model.EventStatistics;
import com.epam.movies.service.EventStatisticsService;
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
		return !eventStatisticsDAO.isEventStatisticsExist(name);
	}

	@Override
	public void updateEventStatistics(EventStatistics statistics) {
		 eventStatisticsDAO.update(statistics);
	}

	@Override
	public EventStatistics getStaticsticsForEventByEventName(String eventName) {
		return eventStatisticsDAO.getByName(eventName);
	}

}
