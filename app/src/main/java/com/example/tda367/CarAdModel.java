package com.example.tda367;

import java.io.Serializable;

public class CarAdModel implements Serializable {


    private String carBrand;
    private String carModel;
    private String carTitle;
    private String carYear;
    private String carLocation;
    private String carPrice;
    private String carID;// Hash created by firebase.
    private String imageUrl;


    public CarAdModel(String carID, String carTitle, String carBrand, String carModel, String carYear, String carPrice, String carLocation, String imageUrl) {

        this.carID = carID;
        this.carTitle = carTitle;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carYear = carYear;
        this.carPrice = carPrice;
        this.carLocation = carLocation;
        this.imageUrl = imageUrl;

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

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
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


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
