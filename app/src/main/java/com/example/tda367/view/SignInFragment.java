package com.example.tda367.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tda367.R;
import com.example.tda367.controller.ProfileViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import android.widget.Toast;

public class SignInFragment extends Fragment {

    private ProfileViewModel profileViewModel;

    private Button buttonLogIn;
    private Button buttonSignup;
    private EditText editEmailText;
    private EditText editPasswordText;
    private FirebaseAuth firebaseAuth;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Kollar om användare är inloggad innan den skickar en view
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null){
            loadProfileFragment();
        }
        int currentFragment = R.layout.fragment_notifications;
        View view = LayoutInflater.from(getContext()).inflate(currentFragment, container, false);


        editEmailText = (EditText) view.findViewById(R.id.editEmailText);
        editPasswordText = (EditText) view.findViewById(R.id.editPasswordText);
        buttonLogIn = (Button) view.findViewById(R.id.buttonLogIn);

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (isFieldsEmpty()) {
                   makeToast("You need to fill in all the fields!");
               } else {
                   signIn();
               }
           }
       });

        buttonSignup = (Button) view.findViewById(R.id.buttonSignup);
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("klick");
                Fragment signUpFragment = new SignUpFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signUpFragment).commit();
            }
        });

        return view;
    }

    private void signIn(){

        //string kan inte vara null eller inget för att köra
        if (isFieldsEmpty()){
            System.out.println("inget skrevs in");
        }else{
            String email = String.valueOf(editEmailText.getText());
            String password = String.valueOf(editPasswordText.getText());
            firebaseAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener((task -> {
                        if(task.isSuccessful()) {
                            System.out.println("du klarade det bre");
                            loadProfileFragment();
                            makeToast("You are now logged in!");
                        }else{
                            FirebaseAuthException e = (FirebaseAuthException)task.getException();
                            assert e != null;
                            makeToast(e.getMessage());
                        }
                    }));
        }
        //Test epost: hannes@gmail.com pass: Stulb123
    }

    private boolean isFieldsEmpty() {
        return String.valueOf(editEmailText.getText()).isEmpty() || String.valueOf(editPasswordText.getText()).isEmpty();
    }

    private void loadProfileFragment(){
        Fragment profileFragment = new ProfileFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, profileFragment).commit();
    }

    private void makeToast(CharSequence message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getContext(), message, duration);
        View view = toast.getView();
        toast.show();
    }


}