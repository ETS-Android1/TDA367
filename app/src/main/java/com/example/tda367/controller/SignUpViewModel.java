package com.example.tda367.controller;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tda367.R;
import com.example.tda367.model.FirebaseHandler;
import com.example.tda367.view.SignUpPaymentFragment;

public class SignUpViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    private FirebaseHandler firebaseHandler = new FirebaseHandler();

    public SignUpViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is signup fragment");
    }

    public boolean isUserLoggedIn() {
        return firebaseHandler.isUserLoggedIn();
    }

    public void registerUserWithEmailAndPassword(String email, String password, String
            firstName, String surName, String address, String city, String phoneNumber) {
        firebaseHandler.registerUserWithEmailAndPassword(email, password, firstName, surName, address, city, phoneNumber);
    }
    
    public void loadProfileFragment(FragmentManager fragmentManager){
        Fragment profileFragment = new SignUpPaymentFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, profileFragment).commit();
    }
    public void loadSignInFragment(FragmentManager fragmentManager){
        Fragment signInFragment = new SignUpPaymentFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signInFragment).commit();
    }
}
