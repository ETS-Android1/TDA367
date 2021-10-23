package edu.stulb.rentalcar.controller;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tda367.R;
import com.google.firebase.auth.FirebaseUser;

import edu.stulb.rentalcar.model.FirebaseHandler;
import edu.stulb.rentalcar.view.AddCarAdFragment;
import edu.stulb.rentalcar.view.DashboardFragment;
import edu.stulb.rentalcar.view.SignInFragment;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private FirebaseHandler firebaseHandler = new FirebaseHandler();

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Homepage for featured images.");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public FirebaseUser getCurrentUser() {
        return firebaseHandler.getCurrentUser();
    }
}