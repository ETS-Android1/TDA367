package com.example.tda367;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpFragment extends Fragment {

    private Button buttonContinuePayment;
    private Button buttonCancelRegistation;
    private EditText editTextTextEmailAddress;
    private EditText editTextTextPassword2;
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
        editTextTextEmailAddress = (EditText) view.findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword2 = (EditText) view.findViewById(R.id.editTextTextPassword2);


        buttonContinuePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        return view;
    }

    private void registerUser(){
        final String email = editTextTextEmailAddress.getText().toString();
        String password = editTextTextPassword2.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((task -> {
            if(task.isSuccessful()){
                userID = firebaseAuth.getUid();
                System.out.println(userID);
                FirebaseFirestore.getInstance().collection("users").document(userID).set(generateHashMap(userID,email));
            } else {
                FirebaseAuthException e = (FirebaseAuthException)task.getException();
                assert e != null;
                System.out.println( "felmeddelande: " + e.getMessage());
            }
        }));
    }

    public Map<String, Object> generateHashMap(String userID, String email) {
        Map<String, Object> UsersID = new HashMap<String, Object>();

        //KEYS gives String to field inside document
        UsersID.put("UserID", userID);
        UsersID.put("User Email", email);
        return UsersID;
    }


}