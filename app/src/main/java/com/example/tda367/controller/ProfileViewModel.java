package com.example.tda367.controller;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tda367.R;
import com.example.tda367.model.CarAdModel;
import com.example.tda367.model.FirebaseHandler;
import com.example.tda367.view.AddCarAdFragment;
import com.example.tda367.view.SignInFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private FirebaseHandler firebaseHandler = new FirebaseHandler();

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

    public String getUserEmail(){
        return firebaseHandler.getCurrentUser().getEmail().toString();
    }

    public void loadSignInFragment(FragmentManager fragmentManager){
        Fragment signInFragment = new SignInFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signInFragment).commit();
    }

    public void loadAddCarAdFragment(FragmentManager fragmentManager){
        Fragment addCarAdFragment = new AddCarAdFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, addCarAdFragment).commit();
    }

    public void signOut(){
        firebaseHandler.signOut();
    }

}