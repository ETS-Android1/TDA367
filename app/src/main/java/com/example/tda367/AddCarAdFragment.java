package com.example.tda367;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

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
    private final ImageHandler imageHandler = new ImageHandler();
    private ImageView carPreview;
    private Uri selectedImage;
    ProgressDialog progressDialog;

    FirebaseUser mUser;
    private FirebaseAuth mAuth;
    StorageReference mStorage;
    FirebaseFirestore db;


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

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mStorage = FirebaseStorage.getInstance().getReference().child("CarImages");

        progressDialog = new ProgressDialog(getContext());

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
        if (!checkFields()) {
            String carTitle = String.valueOf(titleEditText.getText());
            String carBrand = String.valueOf(brandEditText.getText());
            String carModel = String.valueOf(modelEditText.getText());
            String carYear = String.valueOf(yearEditText.getText());
            Long carPrice = Long.valueOf(String.valueOf(priceEditText.getText()));
            String carLocation = String.valueOf(locationEditText.getText());
            //TODO lägga till den i användarens egna collection, inte bara den offentliga

            progressDialog.setMessage("Adding Car in Database");
            progressDialog.show();

            long timeMillis = System.currentTimeMillis();
            //first storeImage in database
            mStorage.child(mUser.getUid()).child(timeMillis + "").putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        mStorage.child(mUser.getUid()).child(timeMillis + "").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                DocumentReference newCarRef = db.collection("cars").document();
                                Map<String, Object> data = generateCarHashMap(carTitle, newCarRef.getId(), carBrand, carModel, carYear, carPrice, carLocation, uri);
                                newCarRef.set(data);

                                db.collection("cars").add(data).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                        if (task.isSuccessful()) {
                                            progressDialog.dismiss();
                                            Toast.makeText(getContext(), "Car Saved Successfully", Toast.LENGTH_SHORT).show();
                                        } else {
                                            progressDialog.dismiss();
                                        }
                                    }
                                });
                            }
                        });

                    }
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
    public Map<String, Object> generateCarHashMap(String carTitle, String carID, String carBrand, String carModel, String carYear, Long carPrice, String carLocation, Uri uri) {
        Map<String, Object> CarId = new HashMap<String, Object>();

        //KEYS gives String to field inside document
        CarId.put("CarTitle", carTitle);
        CarId.put("CarId", carID);
        CarId.put("CarBrand", carBrand);
        CarId.put("CarModel", carModel);
        CarId.put("CarYear", carYear);
        CarId.put("CarPrice", carPrice);
        CarId.put("CarLocation", carLocation);
        CarId.put("CarImageUrl", uri.toString());

        return CarId;
    }
}