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
import com.example.tda367.controller.SignInViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.tomer.fadingtextview.FadingTextView;

import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class SignInFragment extends Fragment {

    private SignInViewModel signInViewModel = new SignInViewModel();

    private FadingTextView fadingTextView;

    private Button buttonLogIn;
    private Button buttonSignup;
    private EditText editEmailText;
    private EditText editPasswordText;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(signInViewModel.isUserLoggedIn()){
            signInViewModel.loadProfileFragment(getParentFragmentManager());
        }
        int currentFragment = R.layout.fragment_signin;
        View view = LayoutInflater.from(getContext()).inflate(currentFragment, container, false);

        editEmailText = (EditText) view.findViewById(R.id.editEmailText);
        editPasswordText = (EditText) view.findViewById(R.id.editPasswordText);
        buttonLogIn = (Button) view.findViewById(R.id.buttonLogIn);
        buttonSignup = (Button) view.findViewById(R.id.buttonSignup);
        fadingTextView = (FadingTextView) view.findViewById(R.id.fading_text_view);

        buttonLogIn.setOnClickListener(v -> {
            if (isFieldsEmpty()) {
                makeToast("You need to fill in all the fields!");
            } else {
                signInViewModel.signIn(String.valueOf(editEmailText.getText()), String.valueOf(editPasswordText.getText()));
                if (signInViewModel.isUserLoggedIn()){
                    signInViewModel.loadProfileFragment(getParentFragmentManager());
                    makeToast("You have signed in");
                }
            }
        });

        buttonSignup.setOnClickListener(v -> {
            signInViewModel.loadSignupFragment(getParentFragmentManager());
        });

        return view;

    }

    public boolean isFieldsEmpty() {
        return String.valueOf(editEmailText.getText()).isEmpty() || String.valueOf(editPasswordText.getText()).isEmpty();
    }

    private void makeToast(CharSequence message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getContext(), message, duration);
        View view = toast.getView();
        toast.show();
    }


}