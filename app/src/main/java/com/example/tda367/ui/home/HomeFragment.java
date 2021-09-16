package com.example.tda367.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.tda367.R;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;

    ImageView myImageView;
    ImageButton nextButton;
    ImageButton backButton;

    int[] images = {
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_background,
            R.drawable.ic_home_black_24dp,
            R.drawable.ic_dashboard_black_24dp
    };

    int currentImage = 0;
    int numImages = images.length;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        super.onCreate(savedInstanceState);

        myImageView = (ImageView) view.findViewById(R.id.myImageView);
        nextButton = (ImageButton) view.findViewById(R.id.nextButton);
        backButton = (ImageButton) view.findViewById(R.id.backButton);

        nextButton.setOnClickListener(changeNextImage);
        backButton.setOnClickListener(changePreviousImage);

        myImageView.setImageResource(images[currentImage]);

        return view;
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
        }
    };

}