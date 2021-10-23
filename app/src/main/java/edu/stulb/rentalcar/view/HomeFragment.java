package edu.stulb.rentalcar.view;

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

import edu.stulb.rentalcar.controller.FragmentHandler;
import edu.stulb.rentalcar.controller.HomeViewModel;
import com.example.tda367.R;

public class HomeFragment extends Fragment {
    private final FragmentHandler fragmentHandler = FragmentHandler.getInstance();
    private final HomeViewModel homeViewModel = new HomeViewModel();

    private ImageView myImageView;
    private ImageButton nextButton;
    private ImageButton backButton;
    private Button findCarButton;
    private Button addCarButton;
    private Button howDoButton;
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        super.onCreate(savedInstanceState);

        myImageView = view.findViewById(R.id.myImageView);
        nextButton = view.findViewById(R.id.nextButton);
        backButton = view.findViewById(R.id.backButton);
        findCarButton = view.findViewById(R.id.findCarButton);
        addCarButton = view.findViewById(R.id.addCarButton);
        howDoButton = view.findViewById(R.id.howDoButton);
        numberOfTextView = view.findViewById(R.id.numberOfTextView);

        nextButton.setOnClickListener(changeNextImage);
        backButton.setOnClickListener(changePreviousImage);

        findCarButton.setOnClickListener(v -> homeViewModel.loadDashboardFragment(getParentFragmentManager()));

        // if logged in, go to add car ad, else go to sign in
        addCarButton.setOnClickListener(v -> {
            if (homeViewModel.getCurrentUser() != null) {
                homeViewModel.loadAddCarFragment(getParentFragmentManager());
                return;
            }
            homeViewModel.loadSignInFragment(getParentFragmentManager());
            makeToast("You need to log in first!");
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