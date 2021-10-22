package edu.stulb.rentalcar.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tda367.R;
import edu.stulb.rentalcar.controller.SignUpViewModel;

public class SignUpFragment extends Fragment {

    private final SignUpViewModel signUpViewModel = new SignUpViewModel();

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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (signUpViewModel.isUserLoggedIn()){
            loadProfileFragment();
        }
        View view = LayoutInflater.from(getContext()).inflate(R.layout.new_user_sign_up, container, false);
        buttonContinuePayment = (Button) view.findViewById(R.id.buttonContinueConfirmation);
        buttonCancelRegistation = (Button) view.findViewById(R.id.buttonCancelRegistation);
        emailInput = (EditText) view.findViewById(R.id.emailInput);
        passwordInput = (EditText) view.findViewById(R.id.passwordInput);
        reEnterPasswordInput = (EditText) view.findViewById(R.id.reEnterPasswordInput);
        firstNameInput = (EditText) view.findViewById(R.id.editFirstNameInput);
        surnameInput = (EditText) view.findViewById(R.id.editSurnameInput);
        addressInput = (EditText) view.findViewById(R.id.editAddressInput);
        cityInput = (EditText) view.findViewById(R.id.editCityInput);
        phoneNumberInput = (EditText) view.findViewById(R.id.editPhoneNumberInput);

        buttonContinuePayment.setOnClickListener(v -> {
            if (!isFieldsEmpty()) {
                registerUser();
                loadSignUpPaymentFragment();
                return;
            }
            makeToast("You need to fill in all the fields");
            System.out.println("You need to fill in all the fields");
        });
        buttonCancelRegistation.setOnClickListener(v -> loadSignInFragment());
        return view;

    }

    private void checkInputLimitations() {
        if (!checkEmailInput()) {
            makeToast("Email must contain a @");
            return;
        }
        else if (!checkPasswordLength()) {
            makeToast("Password must be at least 8 characters!");
            return;
        }
        else if (!checkPasswordsEqual()) {
            makeToast("Passwords must be the same!");
            return;
        }
        registerUser();
        loadSignUpPaymentFragment();
    }

    private boolean checkPasswordsEqual() {
        return passwordInput.getText().toString().equals(reEnterPasswordInput.getText().toString());
    }

    private boolean checkPasswordLength() {
        return (emailInput.getText().toString().length() > 7) ||
                (reEnterPasswordInput.getText().toString().length() > 7);
    }

    private boolean checkEmailInput() {
        return emailInput.getText().toString().contains("@");
    }

    private void registerUser(){
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String firstName = firstNameInput.getText().toString();
        String surName = surnameInput.getText().toString();
        String address = addressInput.getText().toString();
        String city = cityInput.getText().toString();
        String phoneNumber = phoneNumberInput.getText().toString();

        signUpViewModel.registerUserWithEmailAndPassword(email, password, firstName,surName, address, city, phoneNumber);
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

    private void loadSignUpPaymentFragment(){
        signUpViewModel.loadSignUpPaymentFragment(getParentFragmentManager());
    }

    private void loadProfileFragment(){
        signUpViewModel.loadProfileFragment(getParentFragmentManager());
    }
    private void loadSignInFragment(){
        signUpViewModel.loadSignInFragment(getParentFragmentManager());
    }


}