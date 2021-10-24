package edu.stulb.rentalcar.controller;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.tda367.R;
import edu.stulb.rentalcar.model.user.User;
import edu.stulb.rentalcar.model.user.UserHandler;
import edu.stulb.rentalcar.view.AddListingFragment;
import edu.stulb.rentalcar.view.DashboardFragment;
import edu.stulb.rentalcar.view.SignInFragment;

public class HomeViewModel extends ViewModel {

    private final UserHandler userHandler = UserHandler.getInstance();
    private final MutableLiveData<String> mText;

    int[] images = {
            R.drawable.car_featured_1,
            R.drawable.car_featured_2,
            R.drawable.car_featured_3,
            R.drawable.car_featured_4,
            R.drawable.car_featured_5,
            R.drawable.car_featured_6,
            R.drawable.car_featured_7,
            R.drawable.car_featured_8
    };

    int currentImage = 0;
    int numImages = images.length;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Homepage for featured images.");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void loadAddCarFragment(FragmentManager fragmentManager){
        Fragment addCarFragment = new AddListingFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, addCarFragment).commit();
    }

    public void loadDashboardFragment(FragmentManager fragmentManager){
        Fragment dashboardFragment = new DashboardFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, dashboardFragment).commit();
    }

    public void loadSignInFragment(FragmentManager fragmentManager){
        Fragment signInFragment = new SignInFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signInFragment).commit();
    }

    public User getCurrentUser() {
        return userHandler.getCurrentUser();
    }

    public void setFirstState(ImageView imageView, TextView textView) {
        imageView.setImageResource(images[currentImage]);
        textView.setText("Showing image " + (currentImage + 1) + " of " + images.length);
    }

    public void nextImage(ImageView imageView, TextView textView) {
        if (currentImage == images.length) {
            currentImage = 0;
        } else {
            //Increment Counter to move to next Image
            currentImage++;
            currentImage = currentImage % numImages;
            //Set the image depending on the counter.
            currentImage = currentImage % images.length;
        }
        imageView.setImageResource(images[currentImage]);
        textView.setText("Showing image " + (currentImage+1) + " of " + images.length);
    }

    public void previousImage(ImageView imageView, TextView textView) {
        if (currentImage == 0) {
            currentImage = images.length - 1;
        } else {
            //Increment Counter to move to next Image
            currentImage--;
            currentImage = currentImage % numImages;
            //Set the image depending on the counter.
            currentImage = currentImage % images.length;
        }
        imageView.setImageResource(images[currentImage]);
        textView.setText("Showing image " + (currentImage+1) + " of " + images.length);
    }
}