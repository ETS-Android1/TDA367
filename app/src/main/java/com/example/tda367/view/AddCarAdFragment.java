package com.example.tda367.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tda367.R;
import com.example.tda367.controller.ProfileViewModel;
import com.example.tda367.model.CarAdModel;

public class AddCarAdFragment extends Fragment {

    private ProfileViewModel profileViewModel = new ProfileViewModel();

    private static final int RESULT_LOAD_IMAGE = 1;
    private Button saveAdButton;
    private Button uploadImageButton;
    private EditText titleEditText;
    private EditText brandEditText;
    private EditText modelEditText;
    private EditText yearEditText;
    private EditText priceEditText;
    private ImageView carPreview;
    private Spinner spinnerLocation;
    private Uri selectedImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_ad, container, false);

        String[] locations = {"Göteborg", "Stockholm", "Malmö"};

        titleEditText = view.findViewById(R.id.titleEditText);
        brandEditText = view.findViewById(R.id.brandEditText);
        modelEditText = view.findViewById(R.id.modelEditText);
        yearEditText = view.findViewById(R.id.yearEditText);
        priceEditText = view.findViewById(R.id.priceEditText);
        saveAdButton = view.findViewById(R.id.saveAdButton);
        uploadImageButton = view.findViewById(R.id.uploadImageButton);
        spinnerLocation = view.findViewById(R.id.spinnerLocation);
        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int i, long l) {}

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}});

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, locations);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerLocation.setAdapter(adapter);

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

    private void addAdToFirebase() {
        if (!areFieldsEmpty() && selectedImage != null) {
            String carTitle = String.valueOf(titleEditText.getText());
            String carBrand = String.valueOf(brandEditText.getText());
            String carModel = String.valueOf(modelEditText.getText());
            String carYear = String.valueOf(yearEditText.getText());
            Integer carPrice = Integer.valueOf(String.valueOf(priceEditText.getText()));
            String carLocation = String.valueOf(spinnerLocation.getSelectedItem());
            profileViewModel.addAd(carTitle, carBrand, carModel, carYear, carPrice, carLocation, selectedImage);
        }
    }

    //Checks if inputFields are empty -> returns true if any field is empty.
    private boolean areFieldsEmpty() {
        return String.valueOf(titleEditText.getText()).isEmpty() ||
                String.valueOf(brandEditText.getText()).isEmpty() ||
                String.valueOf(modelEditText.getText()).isEmpty() ||
                String.valueOf(yearEditText.getText()).isEmpty() ||
                String.valueOf(priceEditText.getText()).isEmpty();
    }
}