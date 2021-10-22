package edu.stulb.rentalcar.controller;

import android.net.Uri;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tda367.R;
import edu.stulb.rentalcar.model.CarAdModel;
import edu.stulb.rentalcar.model.FirebaseHandler;
import edu.stulb.rentalcar.view.AddCarAdFragment;
import edu.stulb.rentalcar.view.SignInFragment;

import java.util.ArrayList;

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

    public ArrayList<CarAdModel> carAds(){
        ArrayList<CarAdModel> cars = new ArrayList<CarAdModel>();



        return cars;
    }

}