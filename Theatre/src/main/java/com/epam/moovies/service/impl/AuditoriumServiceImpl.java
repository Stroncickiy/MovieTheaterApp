package com.epam.moovies.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.epam.moovies.dao.AuditoriumDAO;
import com.epam.moovies.dao.SeatsDAO;
import com.epam.moovies.model.Auditorium;
import com.epam.moovies.model.Seat;
import com.epam.moovies.service.AuditoriumService;

@Service(value = "auditoriumService")
public class AuditoriumServiceImpl implements AuditoriumService {

	@Autowired
	private AuditoriumDAO dao;

	@Autowired
	private SeatsDAO seatsDao;

	@Value("${auditories.number}")
	private Integer numberOfAuditoriesInProperties;

	@Autowired
	private Environment environment;

	@Value("${auditories.initFromProperties}")
	private boolean initFromProperties;

	@PostConstruct
	public void init() {
		if (initFromProperties) {
			for (int i = 1; i <= numberOfAuditoriesInProperties; i++) {
				Auditorium parsedAuditoriumFromProperties = parseAuditoriumFromProperties(i);
				String name = parsedAuditoriumFromProperties.getName();
				if (dao.getByName(name) == null) {
					addAuditorium(parsedAuditoriumFromProperties);
				}
			}
		} else {
			System.out.println("Initialization of auditories from properties skipped");
		}
	}

	private Auditorium parseAuditoriumFromProperties(int auditoryNumber) {
		String auditoryName = environment.getProperty("auditory" + auditoryNumber + ".name");
		Integer auditoryNumberOfSeats = Integer
				.parseInt(environment.getProperty("auditory" + auditoryNumber + ".numberOfSeats"));
		String auditoryVipSeatsString = environment.getProperty("auditory" + auditoryNumber + ".vipSeats").trim();
		String[] auditoryVipSeatsArray = auditoryVipSeatsString.split(",");
		List<String> auditoryVipSeats = Arrays.asList(auditoryVipSeatsArray);

		Auditorium auditorium = new Auditorium();
		List<Seat> seatList = new ArrayList<>();
		for (int seatNumber = 1; seatNumber <= auditoryNumberOfSeats; seatNumber++) {
			Seat seat = new Seat();
			seat.setNumber(Long.valueOf(seatNumber));
			seat.setVip(auditoryVipSeats.contains(String.valueOf(seatNumber)));
			seatList.add(seat);
		}
		auditorium.setName(auditoryName);
		auditorium.setSeats(seatList);
		return auditorium;
	}

	public void addAuditorium(Auditorium auditorium) {
		dao.add(auditorium);
	}

	public List<Auditorium> getAll() {
		return dao.getAll();
	}

	public int getSeatsNumber(Long id) {
		return dao.getById(id).getSeats().size();
	}

	public List<Seat> getSeats(Long id) {
		return dao.getById(id).getSeats();
	}

	public Auditorium getByName(String name) {
		return dao.getByName(name);
	}

	@Override
	public Auditorium getById(Long auditoryId) {
		return dao.getById(auditoryId);
	}

	@Override
	public List<Seat> getSeatsByNumbersAndAuditorium(Long auditoriumId, Long[] seats) {
		return seatsDao.getSeatsByNumberAndAuditorium(auditoriumId, seats);
	}

	@Override
	public List<Seat> getSeatsForAuditorium(Long auditoriumId) {
		return seatsDao.getSeatsForAuditorium(auditoriumId);
	}

}
