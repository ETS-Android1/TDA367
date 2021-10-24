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
    private EditText nameInput;
    private EditText cardNumberInput;
    private EditText cardNameInput;
    private EditText cardDateInput;
    private EditText cardCVVInput;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (signUpViewModel.isUserSignedIn()){
            loadProfileFragment();
        }
        View view = LayoutInflater.from(getContext()).inflate(R.layout.new_user_sign_up, container, false);
        buttonContinuePayment = view.findViewById(R.id.buttonSignUp);
        buttonCancelRegistation = view.findViewById(R.id.buttonCancelRegistation);
        emailInput = view.findViewById(R.id.emailInput);
        passwordInput = view.findViewById(R.id.passwordInput);
        reEnterPasswordInput = view.findViewById(R.id.reEnterPasswordInput);
        nameInput = view.findViewById(R.id.nameInput);
        cardNumberInput = view.findViewById(R.id.cardnumberInput);
        cardNameInput = view.findViewById(R.id.cardnameInput);
        cardDateInput = view.findViewById(R.id.dateInput);
        cardCVVInput = view.findViewById(R.id.cvvInput);

        buttonContinuePayment.setOnClickListener(v -> {
            checkContinuePayment();
        });
        buttonCancelRegistation.setOnClickListener(v -> loadSignInFragment());
        return view;

    }

    private void checkContinuePayment() {
        if (!isFieldsEmpty()) {
            if (!signUpViewModel.checkEmailInput(emailInput.getText().toString())) {
                makeToast("Email must contain @");
                return;
            }
            else if (!signUpViewModel.checkPasswordLength(passwordInput.getText().toString(),
                    reEnterPasswordInput.getText().toString())) {
                makeToast("Password must be at least 8 characters!");
                return;
            }
            else if (!signUpViewModel.checkPasswordsEqual(passwordInput.getText().toString(),
                    reEnterPasswordInput.getText().toString())) {
                makeToast("Passwords must be equal!");
                return;
            }
            else if (!signUpViewModel.checkCardLength(cardNumberInput.getText().toString())) {
                makeToast("Cardnumber must be 16 digits!");
                return;
            }
            else if (!signUpViewModel.checkDateInput(cardDateInput.getText().toString())) {
                makeToast("Incorrect date input, must be: MM/YY");
                return;
            }
            else if (!signUpViewModel.checkCvvLength(cardCVVInput.getText().toString())) {
                makeToast("CVV must be 3 digits!");
                return;
            }
            else {
                registerUser();
                makeToast("Sign up successful!");
                loadSignInFragment();
                return;
            }
        }
        makeToast("You need to fill in all the fields");
        System.out.println("You need to fill in all the fields");
    }

    private void registerUser(){
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String name = nameInput.getText().toString();
        String cardNumber = cardNumberInput.getText().toString();
        String cardName = cardNameInput.getText().toString();
        String cardDate = cardDateInput.getText().toString();
        String cardCVV = cardCVVInput.getText().toString();
        signUpViewModel.createUser(email, password, name, cardNumber, cardName, cardDate, cardCVV);
    }

    private boolean isFieldsEmpty() {
        return String.valueOf(emailInput.getText()).isEmpty() ||
                String.valueOf(passwordInput.getText()).isEmpty() ||
                String.valueOf(reEnterPasswordInput.getText()).isEmpty() ||
                String.valueOf(nameInput.getText()).isEmpty() ||
                String.valueOf(cardNameInput.getText()).isEmpty() ||
                String.valueOf(cardNumberInput.getText()).isEmpty() ||
                String.valueOf(cardDateInput.getText()).isEmpty() ||
                String.valueOf(cardCVVInput.getText()).isEmpty();
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