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

import edu.stulb.rentalcar.controller.HomeViewModel;
import com.example.tda367.R;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel = new HomeViewModel();

    private ImageButton nextButton;
    private ImageButton backButton;
    private Button findCarButton;
    private Button addCarButton;
    private ImageView featuredImageView;
    private TextView numberOfTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        super.onCreate(savedInstanceState);

        featuredImageView = (ImageView) view.findViewById(R.id.myImageView);
        nextButton = (ImageButton) view.findViewById(R.id.nextButton);
        backButton = (ImageButton) view.findViewById(R.id.backButton);
        findCarButton = (Button) view.findViewById(R.id.findCarButton);
        addCarButton = (Button) view.findViewById(R.id.addCarButton);
        numberOfTextView = (TextView) view.findViewById(R.id.numberOfTextView);

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

        homeViewModel.setFirstState(featuredImageView, numberOfTextView);

        return view;
    }

    private void makeToast(CharSequence message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getContext(), message, duration);
        toast.show();
    }


    View.OnClickListener changeNextImage = new OnClickListener() {
        public void onClick(View v) {
            homeViewModel.nextImage(featuredImageView, numberOfTextView);
        }
    };

    View.OnClickListener changePreviousImage = new OnClickListener() {
        public void onClick(View v) {
            homeViewModel.previousImage(featuredImageView, numberOfTextView);
        }
    };

}