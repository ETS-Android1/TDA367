package com.example.tda367.ui.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tda367.R;
import com.example.tda367.ui.controller.ProfileViewModel;
import com.example.tda367.ui.model.FirebaseHandler;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddCarAdFragment extends Fragment {

    private static final int RESULT_LOAD_IMAGE = 1;
    private Button saveAdButton;
    private Button uploadImageButton;
    private EditText titleEditText;
    private EditText brandEditText;
    private EditText modelEditText;
    private EditText yearEditText;
    private EditText priceEditText;
    private EditText locationEditText;
    private ImageView carPreview;
    private Uri selectedImage;
    private ProfileViewModel profileViewModel = new ProfileViewModel();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_ad, container, false);

        titleEditText = view.findViewById(R.id.titleEditText);
        brandEditText = view.findViewById(R.id.brandEditText);
        modelEditText = view.findViewById(R.id.modelEditText);
        yearEditText = view.findViewById(R.id.yearEditText);
        priceEditText = view.findViewById(R.id.priceEditText);
        locationEditText = view.findViewById(R.id.locationEditText);
        saveAdButton = view.findViewById(R.id.saveAdButton);
        uploadImageButton = view.findViewById(R.id.uploadImageButton);

        saveAdButton.setOnClickListener(v -> addAdToFirebase());

        uploadImageButton.setOnClickListener(v -> loadGallery());

       carPreview = view.findViewById(R.id.carPreview);
       carPreview.setVisibility(View.GONE);//Makes it invisible and not take up space before image is selected.


        return view;
    }

    public void loadGallery(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && data != null){
            selectedImage = data.getData();
            carPreview.setImageURI(selectedImage);
            carPreview.setVisibility(View.VISIBLE);//Makes preview visible
        }
    }

    /*private void addAdToFirebase2() {
        if (!checkFields()) {
            String carTitle = String.valueOf(titleEditText.getText());
            String carBrand = String.valueOf(brandEditText.getText());
            String carModel = String.valueOf(modelEditText.getText());
            String carYear = String.valueOf(yearEditText.getText());
            Long carPrice = Long.valueOf(String.valueOf(priceEditText.getText()));
            String carLocation = String.valueOf(locationEditText.getText());

            String carEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference newCarRef = db.collection("cars").document();
            Map<String, Object> data = generateCarHashMap(carTitle, newCarRef.getId(), carBrand, carModel, carYear, carPrice, carLocation, carEmail);
            newCarRef.set(data);

            if (selectedImage != null){
                firebaseHandler.uploadPicture(selectedImage, newCarRef.getId());
            }
        }
    }*/

    private void addAdToFirebase() {
        if (!areFieldsEmpty() && selectedImage != null) {
            String carTitle = String.valueOf(titleEditText.getText());
            String carBrand = String.valueOf(brandEditText.getText());
            String carModel = String.valueOf(modelEditText.getText());
            String carYear = String.valueOf(yearEditText.getText());
            Integer carPrice = Integer.valueOf(String.valueOf(priceEditText.getText()));
            String carLocation = String.valueOf(locationEditText.getText());
            profileViewModel.firebaseHandler.addAd(carTitle, carBrand, carModel, carYear, carPrice, carLocation, selectedImage); // addAd has to upload image
        }
    }
    //Checks if inputFields are empty -> returns true if any field is empty.
    private boolean areFieldsEmpty() {
        return String.valueOf(titleEditText.getText()).isEmpty() ||
                String.valueOf(brandEditText.getText()).isEmpty() ||
                String.valueOf(modelEditText.getText()).isEmpty() ||
                String.valueOf(yearEditText.getText()).isEmpty() ||
                String.valueOf(priceEditText.getText()).isEmpty() ||
                String.valueOf(locationEditText.getText()).isEmpty();
    }
    //Creates Map of Ad
    public Map<String, Object> generateCarHashMap(String carTitle, String carID, String carBrand, String carModel, String carYear, Long carPrice, String carLocation, String carEmail) {
        Map<String, Object> CarId = new HashMap<String, Object>();

        //KEYS gives String to field inside document
        CarId.put("CarTitle", carTitle);
        CarId.put("CarId", carID);
        CarId.put("CarBrand", carBrand);
        CarId.put("CarModel", carModel);
        CarId.put("CarYear", carYear);
        CarId.put("CarPrice", carPrice);
        CarId.put("CarLocation", carLocation);
        CarId.put("CarEmail", carEmail);
        return CarId;
    }
}