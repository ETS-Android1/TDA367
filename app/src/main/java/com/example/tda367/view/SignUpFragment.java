package com.example.tda367.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tda367.R;
import com.example.tda367.controller.SignUpViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpFragment extends Fragment {

    private SignUpViewModel signUpViewModel = new SignUpViewModel();

    private Button buttonContinuePayment;
    private Button buttonCancelRegistation;
    private EditText emailInput;
    private EditText passwordInput;
    private EditText reEnterPasswordInput;
    private EditText firstNameInput;
    private EditText surnameInput;
    private EditText addressInput;
    private EditText cityInput;
    private EditText phoneNumberInput;
    private String userID;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (signUpViewModel.isUserLoggedIn()){
            loadProfileFragment();
        }
        View view = LayoutInflater.from(getContext()).inflate(R.layout.new_user_sign_up, container, false);
        buttonContinuePayment = (Button) view.findViewById(R.id.buttonContinuePayment);
        buttonCancelRegistation = (Button) view.findViewById(R.id.buttonCancelRegistation);
        emailInput = (EditText) view.findViewById(R.id.emailInput);
        passwordInput = (EditText) view.findViewById(R.id.passwordInput);
        reEnterPasswordInput = (EditText) view.findViewById(R.id.reEnterPasswordInput);
        firstNameInput = (EditText) view.findViewById(R.id.firstNameInput);
        surnameInput = (EditText) view.findViewById(R.id.surnameInput);
        addressInput = (EditText) view.findViewById(R.id.addressInput);
        cityInput = (EditText) view.findViewById(R.id.cityInput);
        phoneNumberInput = (EditText) view.findViewById(R.id.phoneNumberInput);

        buttonContinuePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        buttonCancelRegistation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSignInFragment();
            }
        });
        return view;

    }

    private void registerUser(){
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String firstName = firstNameInput.getText().toString();
        String surName = surnameInput.getText().toString();
        String address = addressInput.getText().toString();
        String city = cityInput.getText().toString();
        String phoneNumber = phoneNumberInput.getText().toString();

        if (!isFieldsEmpty()) {
            signUpViewModel.registerUserWithEmailAndPassword(email, password, firstName,surName, address, city, phoneNumber);
        }
        else {
            System.out.println("Alla fields är inte fyllda");
            makeToast("You need to fill in all the fields!");
        }
    }

    private boolean isFieldsEmpty() {
        return String.valueOf(emailInput.getText()).isEmpty() ||
                String.valueOf(passwordInput.getText()).isEmpty() ||
                String.valueOf(reEnterPasswordInput.getText()).isEmpty() ||
                String.valueOf(firstNameInput.getText()).isEmpty() ||
                String.valueOf(surnameInput.getText()).isEmpty() ||
                String.valueOf(addressInput.getText()).isEmpty() ||
                String.valueOf(cityInput.getText()).isEmpty() ||
                String.valueOf(phoneNumberInput.getText()).isEmpty();
    }

    private void makeToast(CharSequence message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getContext(), message, duration);
        toast.show();
    }

    private void loadProfileFragment(){
        signUpViewModel.loadProfileFragment(getParentFragmentManager());
    }
    private void loadSignInFragment(){
        signUpViewModel.loadSignInFragment(getParentFragmentManager());
    }


}