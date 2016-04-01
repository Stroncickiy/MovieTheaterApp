package com.epam.movies.service.impl;

import com.epam.movies.dao.TicketDAO;
import com.epam.movies.dao.UserDAO;
import com.epam.movies.model.Ticket;
import com.epam.movies.model.User;
import com.epam.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private TicketDAO ticketDAO;

	public User register(User user) {
		return userDAO.add(user);
	}

	public void remove(long id) {
		 userDAO.remove(id);
	}

	public User getById(long id) {
		return userDAO.getById(id);
	}

	public User getUserByEmail(String email) {
		List<User> all = userDAO.getAll();
		return all.stream().filter(e -> e.getEmail().equalsIgnoreCase(email)).findFirst().get();
	}

	public User getUserByName(String name) {
		List<User> all = userDAO.getAll();
		return all.stream().filter(e -> e.getFirstName().equals(name)).findFirst().get();
	}

	public List<Ticket> getBookedTickets(long id) {
		List<Ticket> all = ticketDAO.getAll();

		return all.stream().filter(e -> e.getCustomer().equals(userDAO.getById(id)))
				.collect(Collectors.toList());
	}

	@Override
	public List<User> getAll() {
		return userDAO.getAll();
	}

	@Override
	public boolean isUserExist(User user) {
		return userDAO.getAll().stream().filter(u -> u.getEmail().equals(user.getEmail())).collect(Collectors.toList())
				.size() > 0;
	}

	@Override
	public void update(User user) {
		 userDAO.update(user);

	}

}
