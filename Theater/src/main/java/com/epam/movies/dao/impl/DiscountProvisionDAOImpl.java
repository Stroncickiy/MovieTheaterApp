package com.epam.movies.dao.impl;

import com.epam.movies.dao.DiscountProvisionDAO;
import com.epam.movies.model.DiscountProvision;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DiscountProvisionDAOImpl implements DiscountProvisionDAO {


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
