package com.epam.movies.service.impl;

import com.epam.movies.dao.AuditoriumDAO;
import com.epam.movies.dao.SeatsDAO;
import com.epam.movies.dao.impl.AuditoriumDAOImpl;
import com.epam.movies.dao.impl.SeatsDAOImpl;
import com.epam.movies.model.Auditorium;
import com.epam.movies.model.Seat;
import com.epam.movies.service.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service(value = "auditoriumService")
public class AuditoriumServiceImpl implements AuditoriumService {

	@Autowired
	private AuditoriumDAO dao;

	@Autowired
	private SeatsDAO seatsDao;

	@Value("${auditoriums.number}")
	private Integer numberOfAuditoriumsInProperties;

	@Autowired
	private Environment environment;

	@Value("${auditoriums.initFromProperties}")
	private boolean initFromProperties;

	@PostConstruct
	public void init() {
		if (initFromProperties) {
			for (int i = 1; i <= numberOfAuditoriumsInProperties; i++) {
				Auditorium parsedAuditoriumFromProperties = parseAuditoriumFromProperties(i);
				String name = parsedAuditoriumFromProperties.getName();
				if (dao.getByName(name) == null) {
					addAuditorium(parsedAuditoriumFromProperties);
				}
			}
		} else {
			System.out.println("Initialization of auditoriums  from properties skipped");
		}
	}

	private Auditorium parseAuditoriumFromProperties(int auditoryNumber) {
		String auditoryName = environment.getProperty("auditorium" + auditoryNumber + ".name");
		Integer auditoryNumberOfSeats = Integer
				.parseInt(environment.getProperty("auditorium" + auditoryNumber + ".numberOfSeats"));
		String auditoryVipSeatsString = environment.getProperty("auditorium" + auditoryNumber + ".vipSeats").trim();
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
