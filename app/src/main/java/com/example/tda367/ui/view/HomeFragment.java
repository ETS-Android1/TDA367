package com.example.tda367.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.example.tda367.ui.controller.HomeViewModel;
import com.example.tda367.R;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;

    private ImageView myImageView;
    private ImageButton nextButton;
    private ImageButton backButton;
    private Button findCarButton;
    private Button addCarButton;
    private Button howDoButton;
    private FirebaseAuth firebaseAuth;
    private TextView numberOfTextView;

    int[] images = {
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_background,
            R.drawable.ic_home_black_24dp,
            R.drawable.ic_dashboard_black_24dp,
    };

    int currentImage = 0;
    int numImages = images.length;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();

        myImageView = (ImageView) view.findViewById(R.id.myImageView);
        nextButton = (ImageButton) view.findViewById(R.id.nextButton);
        backButton = (ImageButton) view.findViewById(R.id.backButton);
        findCarButton = (Button) view.findViewById(R.id.findCarButton);
        addCarButton = (Button) view.findViewById(R.id.addCarButton);
        howDoButton = (Button) view.findViewById(R.id.howDoButton);
        firebaseAuth = FirebaseAuth.getInstance();
        numberOfTextView = (TextView) view.findViewById(R.id.numberOfTextView);


        nextButton.setOnClickListener(changeNextImage);
        backButton.setOnClickListener(changePreviousImage);


        findCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDashboardFragment();
            }
        });

        // if logged in, go to add car ad, else go to sign in
        addCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firebaseAuth.getCurrentUser() != null) {
                    loadAddCarFragment();
                } else {
                    loadSignInFragment();
                    makeToast("You need to log in first!");
                }
            }
        });


        myImageView.setImageResource(images[currentImage]);
        numberOfTextView.setText("Showing image " + (currentImage+1) + " of " + images.length);

        return view;
    }

    private void makeToast(CharSequence message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getContext(), message, duration);
        toast.show();
    }

    private void loadAddCarFragment(){
        Fragment addCarFragment = new AddCarAdFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, addCarFragment).commit();
    }

    private void loadDashboardFragment(){
        Fragment dashboardFragment = new DashboardFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, dashboardFragment).commit();
    }

    private void loadSignInFragment(){
        Fragment signInFragment = new SignInFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signInFragment).commit();
    }


    View.OnClickListener changeNextImage = new OnClickListener() {
        public void onClick(View v) {
            if (currentImage == images.length) {
                currentImage = 0;
            } else {
                //Increment Counter to move to next Image
                currentImage++;
                currentImage = currentImage % numImages;
                //Set the image depending on the counter.
                currentImage = currentImage % images.length;
        }
            myImageView.setImageResource(images[currentImage]);
            numberOfTextView.setText("Showing image " + (currentImage+1) + " of " + images.length);
        }
    };

    View.OnClickListener changePreviousImage = new OnClickListener() {
        public void onClick(View v) {
            if (currentImage == 0) {
                currentImage = images.length - 1;
            } else {
                //Increment Counter to move to next Image
                currentImage--;
                currentImage = currentImage % numImages;
                //Set the image depending on the counter.
                currentImage = currentImage % images.length;
            }
            myImageView.setImageResource(images[currentImage]);
            numberOfTextView.setText("Showing image " + (currentImage+1) + " of " + images.length);
        }
    };

}