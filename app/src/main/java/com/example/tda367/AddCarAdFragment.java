package com.example.tda367;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.HashMap;
import java.util.Map;
public class AddCarAdFragment extends Fragment {

    private Button saveAdButton;
    private EditText titleEditText;
    private EditText brandEditText;
    private EditText modelEditText;
    private EditText yearEditText;
    private EditText priceEditText;
    private EditText locationEditText;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;
    private int carID;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_ad, container, false);
        indexOfCar();
        titleEditText = (EditText) view.findViewById(R.id.titleEditText);
        brandEditText = (EditText) view.findViewById(R.id.brandEditText);
        modelEditText = (EditText) view.findViewById(R.id.modelEditText);
        yearEditText = (EditText) view.findViewById(R.id.yearEditText);
        priceEditText = (EditText) view.findViewById(R.id.priceEditText);
        locationEditText = (EditText) view.findViewById(R.id.locationEditText);

        saveAdButton = (Button) view.findViewById(R.id.saveAdButton);

        saveAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAdToFirebase();
            }
        });
        return view;
    }

    private void addAdToFirebase() {
        if (!checkFields()) {
            String carTitle = String.valueOf(titleEditText.getText());
            String carBrand = String.valueOf(brandEditText.getText());
            String carModel = String.valueOf(modelEditText.getText());
            String carYear = String.valueOf(yearEditText.getText());
            Long carPrice = Long.valueOf(String.valueOf(priceEditText.getText()));
            String carLocation = String.valueOf(locationEditText);
            String carIdString = Long.toString(carID);
            //TODO lägga till den i användarens egna collection, inte bara den offentliga
            String userID = firebaseAuth.getUid();


            //Creates a new Collection in Firebase with data from generateCarHashMap
            FirebaseFirestore.getInstance().collection("cars").document(carIdString)
                    .set(generateCarHashMap(carTitle, (long) carID, carBrand, carModel, carYear, carPrice, carLocation))
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            System.out.println("la till annonsen");
                        } else {
                            FirebaseAuthException e = (FirebaseAuthException) task.getException();
                            assert e != null;
                            System.out.println("felmeddelande: " + e.getMessage());
                        }
                    });
        }
    }
    //Checks if inputFields are empty
    private boolean checkFields() {
        return String.valueOf(titleEditText.getText()).isEmpty() ||
                String.valueOf(brandEditText.getText()).isEmpty() ||
                String.valueOf(modelEditText.getText()).isEmpty() ||
                String.valueOf(yearEditText.getText()).isEmpty() ||
                String.valueOf(priceEditText.getText()).isEmpty() ||
                String.valueOf(locationEditText.getText()).isEmpty();
    }
    //Creates Map of Ad
    public Map<String, Object> generateCarHashMap(String carTitle, Long carID, String carBrand, String carModel, String carYear, Long carPrice, String carLocation) {
        Map<String, Object> CarId = new HashMap<String, Object>();

        //KEYS gives String to field inside document
        CarId.put("CarTitle", carTitle);
        CarId.put("CarId", carID);
        CarId.put("CarBrand", carBrand);
        CarId.put("CarModel", carModel);
        CarId.put("CarYear", carYear);
        CarId.put("CarPrice", carPrice);
        CarId.put("CarLocation", carLocation);

        return CarId;
    }
    //Checks CarID of last object in firebase and gives carId that value +1 as the new CarID for new object
    private void indexOfCar() {
        final Long[] numIndex = new Long[1];
        firestore.collection("cars").orderBy("CarId", Query.Direction.DESCENDING).limit(1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            //Hinner inte få numindex från firebase innan return
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        numIndex[0] = documentSnapshot.getLong("CarId");
                    }
                } else {
                    System.out.println("Error: " + task.getException());
                }
                carID = numIndex[0].intValue()+1;
            }
        });
        System.out.println(numIndex[0].intValue()+1);
    }
}