package edu.stulb.rentalcar.view;

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
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.example.tda367.R;

import edu.stulb.rentalcar.controller.ProfileViewModel;

public class AddCarAdFragment extends Fragment {

    private ProfileViewModel profileViewModel = new ProfileViewModel();

    private static final int RESULT_LOAD_IMAGE = 1;
    private Button saveAdButton;
    private Button uploadImageButton;
    private Button cancelAdButton;
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

        brandEditText = view.findViewById(R.id.brandEditText);
        modelEditText = view.findViewById(R.id.modelEditText);
        yearEditText = view.findViewById(R.id.yearEditText);
        priceEditText = view.findViewById(R.id.priceEditText);
        saveAdButton = view.findViewById(R.id.saveAdButton);
        uploadImageButton = view.findViewById(R.id.uploadImageButton);
        cancelAdButton = view.findViewById(R.id.cancelAdButton);
        spinnerLocation = view.findViewById(R.id.spinnerLocation);
        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int i, long l) {}

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}});

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, locations);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerLocation.setAdapter(adapter);

        saveAdButton.setOnClickListener(v -> createListing());
        uploadImageButton.setOnClickListener(v -> loadGallery());
        cancelAdButton.setOnClickListener(v -> loadProfileFragment(getParentFragmentManager()));

        carPreview = view.findViewById(R.id.carPreview);
        carPreview.setVisibility(View.GONE);//Makes it invisible and not take up space before image is selected.

        return view;
    }

    public void loadGallery(){
        Intent galleryIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
    }

    private void loadProfileFragment(FragmentManager fragmentManager){
        Fragment profileFragment = new ProfileFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, profileFragment).commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && data != null){
            selectedImage = data.getData();
            Glide.with(getContext()).load(selectedImage).into(carPreview);
            carPreview.setVisibility(View.VISIBLE);//Makes preview visible
            /*Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), selectedImage);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/
        }
    }

    private void createListing() {
        if (!areFieldsEmpty() && selectedImage != null) {
            String carBrand = String.valueOf(brandEditText.getText());
            String carModel = String.valueOf(modelEditText.getText());
            String carYear = String.valueOf(yearEditText.getText());
            int carPrice = Integer.parseInt(String.valueOf(priceEditText.getText()));
            String carLocation = String.valueOf(spinnerLocation.getSelectedItem());
            profileViewModel.createListing(carBrand, carModel, carYear, carPrice, carLocation, selectedImage);
            loadProfileFragment(getParentFragmentManager());
        }
    }

    //Checks if inputFields are empty -> returns true if any field is empty.
    private boolean areFieldsEmpty() {
        return String.valueOf(brandEditText.getText()).isEmpty() ||
                String.valueOf(modelEditText.getText()).isEmpty() ||
                String.valueOf(yearEditText.getText()).isEmpty() ||
                String.valueOf(priceEditText.getText()).isEmpty();
    }
}