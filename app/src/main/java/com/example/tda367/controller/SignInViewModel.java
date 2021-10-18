package com.example.tda367.controller;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tda367.R;
import com.example.tda367.model.FirebaseHandler;
import com.example.tda367.view.ProfileFragment;
import com.example.tda367.view.SignUpFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class SignInViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    private FirebaseHandler firebaseHandler = new FirebaseHandler();

    public SignInViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is profile fragment");
    }

    public boolean isUserLoggedIn(){
        return firebaseHandler.isUserLoggedIn();
    }

    public void loadSignupFragment(FragmentManager fragmentManager){
        Fragment signUpFragment = new SignUpFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signUpFragment).commit();
    }

    public void loadProfileFragment(FragmentManager fragmentManager){
        Fragment profileFragment = new ProfileFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, profileFragment).commit();
    }

    public void signIn(String email, String password){
        firebaseHandler.signInWithEmailAndPassword(email, password);
    }

    public LiveData<String> getText() {
        return mText;
    }
}
