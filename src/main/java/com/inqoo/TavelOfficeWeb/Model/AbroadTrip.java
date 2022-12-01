package com.inqoo.TavelOfficeWeb.model;

import com.inqoo.TavelOfficeWeb.Trip;

public class AbroadTrip extends Trip {
    private double insurrance;
    public double getPrice() {
        return super.getPriceEur() + insurrance;
    }

    public void setInsurrance(double insurrance) {
        this.insurrance = insurrance;
    }

    @Override
    public String toString() {
        return "AbroadTrip{" +
                "insurrance=" + insurrance +
                '}';
    }
}