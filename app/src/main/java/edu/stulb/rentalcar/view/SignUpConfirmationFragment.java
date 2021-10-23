package edu.stulb.rentalcar.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tda367.R;

import edu.stulb.rentalcar.controller.FragmentHandler;
import edu.stulb.rentalcar.controller.SignUpViewModel;

public class SignUpConfirmationFragment extends Fragment {

    private final FragmentHandler fragmentHandler = FragmentHandler.getInstance();
    private final SignUpViewModel signUpViewModel = new SignUpViewModel();

    private Button continueProfileButton;
    private Button continueAddButton;

    public static SignUpConfirmationFragment newInstance() {
        return new SignUpConfirmationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.new_user_confirmation, container, false);

        continueProfileButton = view.findViewById(R.id.continueProfileButton);
        continueAddButton = view.findViewById(R.id.continueAddButton);

        continueProfileButton.setOnClickListener(v -> fragmentHandler.loadProfileFragment(getParentFragmentManager()));

        continueAddButton.setOnClickListener(v -> fragmentHandler.loadAddCarFragment(getParentFragmentManager()));

        return view;
    }

}