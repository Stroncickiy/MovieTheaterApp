package com.epam.moovies.model;

import com.epam.moovies.discount.DiscountStrategy;

public class DiscountProvision {

    private Long id;
    private Long discountAmount;
    private Ticket applyedTo;
    private DiscountStrategy strategyApplied;

    public DiscountProvision(){

    }

    public DiscountProvision(Ticket  applyedTo){
        this.applyedTo = applyedTo;
        this.discountAmount = applyedTo.getDiscountAmount();
        this.strategyApplied = applyedTo.getDiscountStrategy();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Long discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Ticket getApplyedTo() {
        return applyedTo;
    }

    public void setApplyedTo(Ticket applyedTo) {
        this.applyedTo = applyedTo;
    }

    public DiscountStrategy getStrategyApplied() {
        return strategyApplied;
    }

    public void setStrategyApplied(DiscountStrategy strategyApplied) {
        this.strategyApplied = strategyApplied;
    }
}
