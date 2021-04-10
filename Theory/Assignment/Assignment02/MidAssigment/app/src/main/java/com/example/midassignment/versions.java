package com.example.midassignment;

public class versions {

    private String presentAddress , country, district, postOffice, PoliceStation, postalCode, house, road;
    private boolean expandable;

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public versions(String presentAddress ,String country, String district, String postOffice, String policeStation, String postalCode, String house, String road) {
        this.presentAddress = presentAddress;
        this.country = country;
        this.district = district;
        this.postOffice = postOffice;
        PoliceStation = policeStation;
        this.postalCode = postalCode;
        this.house = house;
        this.road = road;
        this.expandable = false;
    }

    public String getPresentAddress() {return presentAddress; }

    public void setPresentAddress(String presentAddress) { this.presentAddress = presentAddress; }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(String postOffice) {
        this.postOffice = postOffice;
    }

    public String getPoliceStation() {
        return PoliceStation;
    }

    public void setPoliceStation(String policeStation) {
        PoliceStation = policeStation;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }


    @Override
    public String toString() {
        return "versions{" +
                "presentAddress='" + presentAddress + '\'' +
                "country='" + country + '\'' +
                ", district='" + district + '\'' +
                ", postOffice='" + postOffice + '\'' +
                ", PoliceStation='" + PoliceStation + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", house='" + house + '\'' +
                ", road='" + road + '\'' +
                '}';
    }

}