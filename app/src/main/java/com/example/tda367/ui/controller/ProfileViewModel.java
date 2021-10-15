package com.example.tda367.ui.controller;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tda367.ui.model.FirebaseHandler;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    public FirebaseHandler firebaseHandler = new FirebaseHandler();

    public ProfileViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public FirebaseHandler getFirebaseHandler() {
        return firebaseHandler;
    }
}