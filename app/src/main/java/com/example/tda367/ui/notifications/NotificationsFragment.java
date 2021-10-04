package com.example.tda367.ui.notifications;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tda367.MainActivity;
import com.example.tda367.ProfileFragment;
import com.example.tda367.R;
import com.example.tda367.SignUpFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import android.widget.Button;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

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
        buttonLogIn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                System.out.println("adde");

                signIn();
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
        if (String.valueOf(editEmailText.getText()).isEmpty() || String.valueOf(editPasswordText.getText()).isEmpty()){
            System.out.println("inget skrevs in");
        }else{
            String email = String.valueOf(editEmailText.getText());
            String password = String.valueOf(editPasswordText.getText());
            firebaseAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener((task -> {
                        if(task.isSuccessful()) {
                            System.out.println("du klarade det bre");
                            loadProfileFragment();
                        }else{
                            FirebaseAuthException e = (FirebaseAuthException)task.getException();
                            assert e != null;
                            System.out.println(e.getMessage());
                        }
                    }));
        }
        //Test epost: hannes@gmail.com pass: Stulb123
    }
    private void loadProfileFragment(){
        Fragment profileFragment = new ProfileFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, profileFragment).commit();
    }

}