package com.example.tda367;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tda367.ui.notifications.NotificationsFragment;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpPaymentFragment extends Fragment {
    private EditText cardnumberInput;
    private EditText cardholderNameInput;
    private EditText dateInput;
    private EditText cvvInput;
    private String CardholderID;
    private Button buttonContinueConfirmation;
    private Button buttonCancelNewUserSignup;
    private FirebaseAuth firebaseAuth;
    Intent intent;


    public static SignUpPaymentFragment newInstance() {
        return new SignUpPaymentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.new_user_payment, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            //ändra fragment till profil
        }


        cardnumberInput = (EditText) view.findViewById(R.id.cardnumberInput);
        cardholderNameInput = (EditText) view.findViewById(R.id.cardholderNameInput);
        dateInput = (EditText) view.findViewById(R.id.dateInput);
        cvvInput = (EditText) view.findViewById(R.id.cvvInput);
        buttonContinueConfirmation = (Button) view.findViewById(R.id.buttonContinueConfirmation);
        buttonCancelNewUserSignup = (Button) view.findViewById(R.id.buttonCancelNewUserSignup);

        buttonContinueConfirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerCardholder();
            }
        });

        intent = new Intent(getActivity(), NotificationsFragment.class);
        buttonCancelNewUserSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                startActivity(intent);
            }
        });

        return view;
    }

    private void registerCardholder() {
        final String cardnumber = cardnumberInput.getText().toString();
        String cardholderName = cardholderNameInput.getText().toString();
        String date = dateInput.getText().toString();
        String cvv = cvvInput.getText().toString();
    }


}

//TODO Strukturera hashmap

/* Oklart om hashmap osv
        public Map<String, Object> generateHashMap(String userID, String email, String firstName, String surname, String address, String city, String phoneNumber) {
        Map<String, Object> CardholderID = new HashMap<String, Object>();

        //KEYS gives String to field inside document
        UsersID.put("CardholderNumber", cardnumber);
        UsersID.put("CardholderName", name);
        UsersID.put("CardholderDate", date);
        UsersID.put("CardholderCVVNumber", CVV);

        return CardholderID;
    }


 */