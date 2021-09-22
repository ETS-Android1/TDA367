package com.example.tda367;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.firestore.FirebaseFirestore;

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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.new_user_sign_up, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
            //ändra fragment till profil
        }

    //TODO lägga till mer inputs till
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

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((task -> {
            if(isAllFilled())
            {
                userID = firebaseAuth.getUid();
                System.out.println(userID);
                FirebaseFirestore.getInstance().collection("users").document(userID).set(generateHashMap(userID,email, firstName, surname,address,city,phoneNumber));
            } else {
                FirebaseAuthException e = (FirebaseAuthException)task.getException();
                assert e != null;
                System.out.println( "felmeddelande: " + e.getMessage());
            }
        }));
    }

    private boolean isAllFilled() {
        return String.valueOf(emailInput.getText()).isEmpty() ||
                String.valueOf(passwordInput.getText()).isEmpty() ||
                String.valueOf(reEnterPasswordInput.getText()).isEmpty() ||
                String.valueOf(firstNameInput.getText()).isEmpty() ||
                String.valueOf(surnameInput.getText()).isEmpty() ||
                String.valueOf(addressInput.getText()).isEmpty() ||
                String.valueOf(cityInput.getText()).isEmpty() ||
                String.valueOf(phoneNumberInput.getText()).isEmpty();
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