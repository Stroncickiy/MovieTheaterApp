package com.epam.moovies.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public abstract class AbstractDAO<O> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6523441422230772584L;
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public abstract O add(O item);

	public abstract boolean update(O item);

	public abstract boolean remove(Long key);

	public abstract List<O> getAll();

	public abstract O getById(Long key);

}
