package com.example.tda367;

import java.io.Serializable;


public class CarAdModel implements Serializable {
    private String carBrand;
    private String carModel;
    private String carTitle;
    private String carYear;
    private String carLocation;
    private int carPrice;
    private String carID;// Hash created by firebase.
    private String carImageUrl;

    public CarAdModel(String carBrand, String carModel, String carTitle, String carYear, String carLocation, int carPrice, String carID, String carImageUrl) {
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carTitle = carTitle;
        this.carYear = carYear;
        this.carLocation = carLocation;
        this.carPrice = carPrice;
        this.carID = carID;
        this.carImageUrl = carImageUrl;
    }

    public CarAdModel() {
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarTitle() {
        return carTitle;
    }

    public void setCarTitle(String carTitle) {
        this.carTitle = carTitle;
    }

    public String getCarYear() {
        return carYear;
    }

    public void setCarYear(String carYear) {
        this.carYear = carYear;
    }

    public int getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(int carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getCarLocation() {
        return carLocation;
    }

    public void setCarLocation(String carLocation) {
        this.carLocation = carLocation;
    }

    public String getCarImageUrl() {
        return carImageUrl;
    }

    public void setCarImageUrl(String imageUrl) {
        this.carImageUrl = imageUrl;
    }


}
