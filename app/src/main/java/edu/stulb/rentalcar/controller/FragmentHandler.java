package edu.stulb.rentalcar.controller;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;

import com.example.tda367.R;

import edu.stulb.rentalcar.view.AddCarAdFragment;
import edu.stulb.rentalcar.view.DashboardFragment;
import edu.stulb.rentalcar.view.ProfileFragment;
import edu.stulb.rentalcar.view.SignInFragment;
import edu.stulb.rentalcar.view.SignUpFragment;

public class FragmentHandler extends ViewModel {
    private static final FragmentHandler instance = new FragmentHandler();

    private FragmentHandler() {
    }

    public static FragmentHandler getInstance(){
        return instance;
    }

    public void loadAddCarFragment(FragmentManager fragmentManager){
        Fragment addCarFragment = new AddCarAdFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, addCarFragment).commit();
    }

    public void loadProfileFragment(FragmentManager fragmentManager){
        Fragment profileFragment = new ProfileFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, profileFragment).commit();
    }

    public void loadSignupFragment(FragmentManager fragmentManager){
        Fragment signUpFragment = new SignUpFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signUpFragment).commit();
    }

    public void loadDashboardFragment(FragmentManager fragmentManager){
        Fragment dashboardFragment = new DashboardFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, dashboardFragment).commit();
    }

    public void loadSignInFragment(FragmentManager fragmentManager){
        Fragment signInFragment = new SignInFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signInFragment).commit();
    }


}
