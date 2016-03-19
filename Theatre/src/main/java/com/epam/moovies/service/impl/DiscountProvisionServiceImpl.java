package com.epam.moovies.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.moovies.dao.DiscountProvisionDAO;
import com.epam.moovies.model.DiscountProvision;
import com.epam.moovies.service.DiscountProvisionService;

@Service
public class DiscountProvisionServiceImpl implements DiscountProvisionService {

    @Autowired
    private DiscountProvisionDAO dao;

    public DiscountProvision registerDiscountProvision(DiscountProvision discountProvision){
        return  dao.add(discountProvision);
    }



}
