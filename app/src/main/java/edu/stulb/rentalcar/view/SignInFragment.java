package edu.stulb.rentalcar.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tda367.R;

import edu.stulb.rentalcar.controller.FragmentHandler;
import edu.stulb.rentalcar.controller.SignInViewModel;

import android.widget.Toast;



public class SignInFragment extends Fragment {

    private final FragmentHandler fragmentHandler = FragmentHandler.getInstance();
    private final SignInViewModel signInViewModel = new SignInViewModel();
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

        editEmailText = view.findViewById(R.id.editEmailText);
        editPasswordText = view.findViewById(R.id.editPasswordText);
        buttonLogIn = view.findViewById(R.id.buttonLogIn);
        buttonSignup = view.findViewById(R.id.buttonSignup);

        buttonLogIn.setOnClickListener(v -> {
            if (isFieldsEmpty()) {
                makeToast("You need to fill in all the fields!");
            }
            if (signInViewModel.signIn(String.valueOf(editEmailText.getText()), String.valueOf(editPasswordText.getText()))) {
                signInViewModel.loadProfileFragment(getParentFragmentManager());
            } else {
                makeToast("Wrong email or password!");
            }
        });

        buttonSignup.setOnClickListener(v -> fragmentHandler.loadSignupFragment(getParentFragmentManager()));
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