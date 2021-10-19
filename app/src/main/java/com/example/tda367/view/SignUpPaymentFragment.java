package com.example.tda367.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tda367.R;
import com.example.tda367.controller.SignUpViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpPaymentFragment extends Fragment {

    private SignUpViewModel signUpViewModel = new SignUpViewModel();

    private EditText cardnumberInput;
    private EditText cardholderNameInput;
    private EditText dateInput;
    private EditText cvvInput;
    private Button buttonContinueConfirmation;
    private Button buttonCancelNewUserSignup;
    private FirebaseAuth firebaseAuth;

    public static SignUpPaymentFragment newInstance() {
        return new SignUpPaymentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.new_user_payment, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        if (signUpViewModel.isUserLoggedIn()) {
            loadProfileFragment(getParentFragmentManager());
        }

        cardnumberInput = (EditText) view.findViewById(R.id.cardnumberInput);
        cardholderNameInput = (EditText) view.findViewById(R.id.cardholderNameInput);
        dateInput = (EditText) view.findViewById(R.id.dateInput);
        cvvInput = (EditText) view.findViewById(R.id.cvvInput);
        buttonContinueConfirmation = (Button) view.findViewById(R.id.buttonContinueConfirmation);
        buttonCancelNewUserSignup = (Button) view.findViewById(R.id.buttonCancelNewUserSignup);

        buttonContinueConfirmation.setOnClickListener(v -> {
            checkInputLimitations();
            registerCardholder();
        });

        buttonCancelNewUserSignup.setOnClickListener(view1 -> loadSignUpFragment(getParentFragmentManager()));
        return view;
    }

    private void checkInputLimitations() {
        if (isFieldsEmpty()) {
            makeToast("You need to fill in all the fields!");
            return;
        }
        else if (!checkCardLength()) {
            makeToast("Cardnumber must be 16 digits!");
            return;
        }
        else if (!checkDateInput()) {
            makeToast("Incorrect date input!");
            return;
        }
        else if (!checkCvvLength()) {
            makeToast("CVV must be 3 digits!");
            return;
        }
    }

    private boolean checkCvvLength() {
        return cvvInput.getText().toString().length() > 2;
    }

    private boolean checkCardLength() {
        return cardnumberInput.getText().toString().length() > 15;
    }

    private boolean isFieldsEmpty() {
        return cardnumberInput.getText().toString().isEmpty() ||
                cardholderNameInput.getText().toString().isEmpty() ||
                dateInput.getText().toString().isEmpty() ||
                cvvInput.getText().toString().isEmpty();
    }

    private boolean checkDateInput() {
        String regex = "[0-9]";
        return dateInput.getText().toString().matches(regex + regex + "/" + regex + regex);
    }

    private void registerCardholder() {
        String cardNumber = cardnumberInput.getText().toString();
        String cardHolderName = cardholderNameInput.getText().toString();
        String date = dateInput.getText().toString();
        String cvv = cvvInput.getText().toString();
        signUpViewModel.registerCard(cardNumber, cardHolderName, date, cvv);
    }

    private void loadSignUpFragment(FragmentManager fragmentManager){
        signUpViewModel.loadSignUpFragment(fragmentManager);
    }
    private void loadConfirmationFragment(FragmentManager fragmentManager){
        signUpViewModel.loadSignUpConfirmationFragment(fragmentManager);
    }

    private void loadProfileFragment(FragmentManager fragmentManager){
        signUpViewModel.loadProfileFragment(fragmentManager);
    }

    private void makeToast(CharSequence message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getContext(), message, duration);
        View view = toast.getView();
        toast.show();
    }
}






