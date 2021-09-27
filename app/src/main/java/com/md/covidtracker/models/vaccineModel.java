package com.md.covidtracker.models;

public class vaccineModel {

    String name;
    String center_id;
    String address;
    String state_name;
    String available_capacity_dose1;
    String available_capacity_dose2;
    String vaccine;
    String from, to , min_age_limit;
    String district_name;

    public vaccineModel(String name, String center_id, String address,
                          String district_name, String state_name, String available_capacity_dose1,
                            String available_capacity_dose2, String vaccine, String from, String to, String min_age_limit) {
        this.name = name;
        this.center_id = center_id;
        this.address = address;
        this.district_name = district_name;
        this.state_name = state_name;
        this.available_capacity_dose1 = available_capacity_dose1;
        this.available_capacity_dose2 = available_capacity_dose2;
        this.vaccine = vaccine;
        this.from = from;
        this.to = to;
        this.min_age_limit = min_age_limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCenter_id() {
        return center_id;
    }

    public void setCenter_id(String center_id) {
        this.center_id = center_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getAvailable_capacity_dose1() {
        return available_capacity_dose1;
    }

    public void setAvailable_capacity_dose1(String available_capacity_dose1) {
        this.available_capacity_dose1 = available_capacity_dose1;
    }

    public String getAvailable_capacity_dose2() {
        return available_capacity_dose2;
    }

    public void setAvailable_capacity_dose2(String available_capacity_dose2) {
        this.available_capacity_dose2 = available_capacity_dose2;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMin_age_limit() {
        return min_age_limit;
    }

    public void setMin_age_limit(String min_age_limit) {
        this.min_age_limit = min_age_limit;
    }

}
