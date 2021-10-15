package com.example.tda367.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tda367.R;

public class SignUpConfirmationFragment extends Fragment {

    private Button continueProfileButton;
    private Button continueAddButton;

    public static SignUpConfirmationFragment newInstance() {
        return new SignUpConfirmationFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.new_user_confirmation, container, false);

        continueProfileButton = (Button) view.findViewById(R.id.continueProfileButton);
        continueAddButton = (Button) view.findViewById(R.id.continueAddButton);

        continueProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadProfileFragment();
            }
        });

        continueAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAddCarAdFragment();
            }
        });

        return view;
    }

    private void loadProfileFragment(){
        Fragment profileFragment = new ProfileFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, profileFragment).commit();
    }
    private void loadAddCarAdFragment(){
        Fragment addCarAdFragment = new AddCarAdFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, addCarAdFragment).commit();
    }
}