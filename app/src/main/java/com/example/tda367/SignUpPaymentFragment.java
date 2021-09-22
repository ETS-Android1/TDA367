package com.example.tda367;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpPaymentFragment extends Fragment {
    private EditText cardnumberInput;
    private EditText cardholderNameInput;
    private EditText dateInput;
    private EditText cvvInput;
    private String CardholderID;
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

        buttonCancelNewUserSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadSignUpFragment();
            }
        });
        return view;
    }

    private void registerCardholder() {
        final String cardnumber = cardnumberInput.getText().toString();
        String cardholderName = cardholderNameInput.getText().toString();
        String date = dateInput.getText().toString();
        String cvv = cvvInput.getText().toString();
        
        String userID = firebaseAuth.getUid();
        // skapar ny collection med document card som håller cardInfo inom userInfo
        FirebaseFirestore.getInstance().collection("users").document(userID).collection("CardInfo").document("Card")
                .set(generateCardHashMap(cardnumber,cardholderName,date,cvv))
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        System.out.println("la till cardinfo");
                    }else {
                        FirebaseAuthException e = (FirebaseAuthException)task.getException();
                        assert e != null;
                        System.out.println( "felmeddelande: " + e.getMessage());
                    }
                });
    }

    private void loadSignUpFragment(){
        Fragment signUpFragment = new SignUpFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signUpFragment).commit();
    }
    public Map<String, Object> generateCardHashMap(String cardnumber, String cardholderID, String date, String cvv) {
        Map<String, Object> CardInfo = new HashMap<String, Object>();

        //KEYS gives String to field inside document
        CardInfo.put("CardholderNumber", cardnumber);
        CardInfo.put("CardholderName", cardholderID);
        CardInfo.put("CardholderDate", date);
        CardInfo.put("CardholderCVVNumber", cvv);

        return CardInfo;
    }
}






