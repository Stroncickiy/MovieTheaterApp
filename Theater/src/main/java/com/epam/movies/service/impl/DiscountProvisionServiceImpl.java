package com.epam.movies.service.impl;

import com.epam.movies.dao.DiscountProvisionDAO;
import com.epam.movies.model.DiscountProvision;
import com.epam.movies.service.DiscountProvisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountProvisionServiceImpl implements DiscountProvisionService {

    @Autowired
    private DiscountProvisionDAO dao;

    public DiscountProvision registerDiscountProvision(DiscountProvision discountProvision){
        return  dao.add(discountProvision);
    }



}
