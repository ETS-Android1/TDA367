package edu.stulb.rentalcar.controller;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tda367.R;
import edu.stulb.rentalcar.model.FirebaseHandler;
import edu.stulb.rentalcar.view.AddCarAdFragment;
import edu.stulb.rentalcar.view.ProfileFragment;
import edu.stulb.rentalcar.view.SignInFragment;
import edu.stulb.rentalcar.view.SignUpConfirmationFragment;
import edu.stulb.rentalcar.view.SignUpFragment;
import edu.stulb.rentalcar.view.SignUpPaymentFragment;

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

    public void loadProfileFragment(FragmentManager fragmentManager) {
        Fragment profileFragment = new ProfileFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, profileFragment).commit();
    }

    public void loadSignInFragment(FragmentManager fragmentManager) {
        Fragment signInFragment = new SignInFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signInFragment).commit();
    }

    public void loadSignUpPaymentFragment(FragmentManager fragmentManager) {
        Fragment signUpPaymentFragment = new SignUpPaymentFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signUpPaymentFragment).commit();
    }

    public void addCarAdFragment(FragmentManager fragmentManager) {
        Fragment addCarAdFragment = new AddCarAdFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, addCarAdFragment).commit();
    }

    public void loadSignUpFragment(FragmentManager fragmentManager) {
        Fragment signUpFragment = new SignUpFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signUpFragment).commit();
    }

    public void loadSignUpConfirmationFragment(FragmentManager fragmentManager) {
        Fragment signUpConfirmationFragment = new SignUpConfirmationFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signUpConfirmationFragment).commit();
    }

    public void registerCard(String cardNumber, String cardHolderName, String date, String cvv){
        firebaseHandler.registerCard(cardNumber, cardHolderName, date, cvv);
    }
}