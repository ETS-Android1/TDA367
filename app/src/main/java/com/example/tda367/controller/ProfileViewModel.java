package com.example.tda367.controller;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tda367.model.FirebaseHandler;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    public FirebaseHandler firebaseHandler = new FirebaseHandler();

    public ProfileViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is profile fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void addAd(String carTitle, String carBrand, String carModel, String carYear, int carPrice, String carLocation, Uri selectedImage){
        firebaseHandler.addAd(carTitle, carBrand, carModel, carYear, carPrice, carLocation, selectedImage);
    }
}