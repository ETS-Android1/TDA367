package com.example.tda367;

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

import com.example.tda367.ui.notifications.NotificationsFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;

public class SignUpFragment extends Fragment {

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
    private FirebaseAuth firebaseAuth;
    private String userID;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Kollar om användare är inloggad innan den skickar en view
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null){
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
        final String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String firstName = firstNameInput.getText().toString();
        String surname = surnameInput.getText().toString();
        String address = addressInput.getText().toString();
        String city = cityInput.getText().toString();
        String phoneNumber = phoneNumberInput.getText().toString();

        if (!isFieldsEmpty()) {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((task -> {
                if (task.isSuccessful()) {
                    userID = firebaseAuth.getUid();
                    System.out.println(userID);
                    FirebaseFirestore.getInstance().collection("users").document(userID)
                            .set(generateHashMap(userID, email, firstName, surname, address, city, phoneNumber));
                    loadSignUpPaymentFragment();
                } else {
                    FirebaseAuthException e = (FirebaseAuthException) task.getException();
                    assert e != null;
                    System.out.println("felmeddelande: " + e.getMessage());
                }
            }));
        }
        else {
            System.out.println("Alla fields är inte fyllda");
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

    private void loadSignUpPaymentFragment(){
        Fragment signUpPaymentFragment = new SignUpPaymentFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signUpPaymentFragment).commit();
    }
    private void loadProfileFragment(){
        Fragment profileFragment = new ProfileFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, profileFragment).commit();
    }
    private void loadSignInFragment(){
        Fragment signInFragment = new NotificationsFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signInFragment).commit();
    }

    public Map<String, Object> generateHashMap(String userID, String email, String firstName, String surname, String address, String city, String phoneNumber) {
        Map<String, Object> UsersID = new HashMap<String, Object>();

        //KEYS gives String to field inside document
        UsersID.put("UserID", userID);
        UsersID.put("UserEmail", email);
        UsersID.put("UserFirstName", firstName);
        UsersID.put("UserSurname", surname);
        UsersID.put("UserAddress", address);
        UsersID.put("UserCity", city);
        UsersID.put("UserPhoneNumber", phoneNumber);

        return UsersID;
    }


}