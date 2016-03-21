package com.epam.movies.dao;

import com.epam.movies.model.DiscountProvision;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class DiscountProvisionDAO extends  AbstractDAO<DiscountProvision> {


    /**
	 * 
	 */
	private static final long serialVersionUID = 8099881243254834266L;

	@Override
    public DiscountProvision add(DiscountProvision item) {
        return null;
    }

    @Override
    public boolean update(DiscountProvision item) {
        return false;
    }

    @Override
    public boolean remove(Long key) {
        return false;
    }

    @Override
    public List<DiscountProvision> getAll() {
        return null;
    }

    @Override
    public DiscountProvision getById(Long key) {
        return null;
    }
}
