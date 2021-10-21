package edu.stulb.rentalcar.controller;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tda367.R;
import com.google.firebase.auth.FirebaseUser;

import edu.stulb.rentalcar.model.Database;
import edu.stulb.rentalcar.model.FirebaseHandler;
import edu.stulb.rentalcar.model.listing.Car;
import edu.stulb.rentalcar.model.listing.CarManufacturer;
import edu.stulb.rentalcar.model.listing.Listing;
import edu.stulb.rentalcar.model.listing.Location;
import edu.stulb.rentalcar.model.listing.Reservation;
import edu.stulb.rentalcar.model.user.Card;
import edu.stulb.rentalcar.model.user.User;
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

    public void loadAddCarFragment(FragmentManager fragmentManager){
        Fragment addCarFragment = new AddCarAdFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, addCarFragment).commit();
    }

    public void loadDashboardFragment(FragmentManager fragmentManager){
        Fragment dashboardFragment = new DashboardFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, dashboardFragment).commit();
        testPushMap();
    }
    public void testPushMap(){
        CarManufacturer carManufacturer = new CarManufacturer("Volvo");
        Car car = new Car("v90", carManufacturer, "2005");
        Location location = new Location("Göteborg");
        Reservation reservation = new Reservation();
        Card card = new Card("Hannes Thörn", "5355830012341234", "11/25", "111");
        User user = new User("Hannes", "Hannes@gmail.com","Stulb123", card);
        Listing listing = new Listing(car, 200, location, user,reservation, "PathentillBilden");
        Database.getInstance().publishListing(listing);
    }

    public void loadSignInFragment(FragmentManager fragmentManager){
        Fragment signInFragment = new SignInFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signInFragment).commit();
    }
}