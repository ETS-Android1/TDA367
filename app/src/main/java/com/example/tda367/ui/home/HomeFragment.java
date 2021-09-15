package com.example.tda367.ui.home;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.tda367.R;
import com.example.tda367.ui.notifications.NotificationsViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;

    ImageView myImageView;
    ImageButton nextButton;
    ImageButton backButton;

    int currentImage = 0;
    int numImages = 3;

    int[] images = {R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, R.drawable.ic_home_black_24dp};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        super.onCreate(savedInstanceState);

        myImageView = (ImageView) view.findViewById(R.id.myImageView);
        nextButton = (ImageButton) view.findViewById(R.id.nextButton);
        backButton = (ImageButton) view.findViewById(R.id.backButton);

        //Just set one Click listener for the image
        nextButton.setOnClickListener(changeNextImage);
        backButton.setOnClickListener(changePreviousImage);
        return view;
    }

        View.OnClickListener changeNextImage = new OnClickListener() {

            public void onClick(View v) {
                //Increase Counter to move to next Image
                currentImage++;
                currentImage = currentImage % numImages;

                //Set the image depending on the counter.
                currentImage++;
                currentImage = currentImage % images.length;

                myImageView.setImageResource(images[currentImage]);
            }
        };

    View.OnClickListener changePreviousImage = new OnClickListener() {

        public void onClick(View v) {
            //Increase Counter to move to next Image
            currentImage--;
            currentImage = currentImage % numImages;

            //Set the image depending on the counter.
            currentImage--;
            currentImage = currentImage % images.length;

            myImageView.setImageResource(images[currentImage]);
        }

    };
}