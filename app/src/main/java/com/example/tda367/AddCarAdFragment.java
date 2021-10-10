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
import android.widget.TextView;
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

    //private static final int RESULT_LOAD_IMAGE = 1;
    private Button saveAdButton;
    private EditText titleEditText;
    private EditText brandEditText;
    private EditText modelEditText;
    private EditText yearEditText;
    private EditText priceEditText;
    private EditText locationEditText;
    private ImageView selectImage;
    private FirebaseAuth mAuth;

    TextView selectImageTextView;
    FirebaseUser mUser;
    FirebaseFirestore db;
    private int carID;
    public static final int PICK_IMAGE = 1101;
    Uri imageUri;
    StorageReference mStorage;
    ProgressDialog progressDialog;

    /*
    private final ImageHandler imageHandler = new ImageHandler();
    private ImageView carPreview;
    private Uri selectedImage;
    */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_ad, container, false);

        titleEditText = (EditText) view.findViewById(R.id.titleEditText);
        brandEditText = (EditText) view.findViewById(R.id.brandEditText);
        modelEditText = (EditText) view.findViewById(R.id.modelEditText);
        yearEditText = (EditText) view.findViewById(R.id.yearEditText);
        priceEditText = (EditText) view.findViewById(R.id.priceEditText);
        locationEditText = (EditText) view.findViewById(R.id.locationEditText);
        selectImage = (ImageView) view.findViewById(R.id.imageview);
        selectImageTextView = (TextView) view.findViewById(R.id.selectImageTextView);


        saveAdButton = view.findViewById(R.id.saveAdButton);

        progressDialog = new ProgressDialog(getContext());
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mStorage = FirebaseStorage.getInstance().getReference().child("CarImages");
        saveAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addAdToFirebase();
            }
        });

        //select image click listner
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });

        selectImageTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });

        /*
        uploadImageButton = view.findViewById(R.id.uploadImageButton);

        saveAdButton.setOnClickListener(v -> addAdToFirebase());

        uploadImageButton.setOnClickListener(v -> loadGallery());

       carPreview = view.findViewById(R.id.carPreview);
       carPreview.setVisibility(View.GONE);//Makes it invisible and not take up space before image is selected.
       */


        return view;
    }

    private void SelectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    /*
    public void loadGallery(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
    }
    */

    private void addAdToFirebase() {

        String carTitle = titleEditText.getText().toString();
        String carBrand = brandEditText.getText().toString();
        String carModel = modelEditText.getText().toString();
        String carYear = yearEditText.getText().toString();
        String carPrice = priceEditText.getText().toString();
        String carLocation = locationEditText.getText().toString();

        //TODO lägga till den i användarens egna collection, inte bara den offentliga

        //check if any field is empty and user didnot added anything in edittext

        if (carTitle.isEmpty()) {
            titleEditText.setError("Type Title of Car");
            titleEditText.requestFocus();
        } else if (carBrand.isEmpty()) {
            brandEditText.setError("Type Brand of Car");
            brandEditText.requestFocus();
        } else if (carModel.isEmpty()) {
            modelEditText.setError("Type Model of Car");
            modelEditText.requestFocus();
        } else if (carYear.isEmpty()) {
            yearEditText.setError("Type Year of Car");
            yearEditText.requestFocus();
        } else if (carPrice.isEmpty()) {
            priceEditText.setError("Type Price of Car");
            priceEditText.requestFocus();
        } else if (carLocation.isEmpty()) {
            locationEditText.setError("Type Location of Car");
            locationEditText.requestFocus();
        } else if (imageUri == null) {
            Toast.makeText(getContext(), "Select Image For Car", Toast.LENGTH_SHORT).show();
        } else {

            progressDialog.setMessage("Adding Car in Database");
            progressDialog.show();

            long timeMillis = System.currentTimeMillis();
            //first storeImage in database
            mStorage.child(mUser.getUid()).child(timeMillis + "").putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        mStorage.child(mUser.getUid()).child(timeMillis + "").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                //save data in Firestore after uploading image

                                HashMap hashMap = new HashMap();
                                hashMap.put("title", carTitle);
                                hashMap.put("brand", carBrand);
                                hashMap.put("model", carModel);
                                hashMap.put("year", carYear);
                                hashMap.put("price", carPrice);
                                hashMap.put("location", carLocation);
                                hashMap.put("imageUrl", uri.toString());

                                db.collection("Cars").add(hashMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
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

    /*
    private void addAdToFirebase() {
        if (!checkFields()) {
            String carTitle = String.valueOf(titleEditText.getText());
            String carBrand = String.valueOf(brandEditText.getText());
            String carModel = String.valueOf(modelEditText.getText());
            String carYear = String.valueOf(yearEditText.getText());
            Long carPrice = Long.valueOf(String.valueOf(priceEditText.getText()));
            String carLocation = String.valueOf(locationEditText.getText());
            //TODO lägga till den i användarens egna collection, inte bara den offentliga


            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference newCarRef = db.collection("cars").document();
            Map<String, Object> data = generateCarHashMap(carTitle, newCarRef.getId(), carBrand, carModel, carYear, carPrice, carLocation);
            newCarRef.set(data);

            if (selectedImage != null){
                imageHandler.uploadPicture(selectedImage, newCarRef.getId());
            }
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

    */

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            selectImage.setImageURI(imageUri);
        }
        /*
        if (requestCode == RESULT_LOAD_IMAGE && data != null){
            selectedImage = data.getData();
            carPreview.setImageURI(selectedImage);
            carPreview.setVisibility(View.VISIBLE);//Makes preview visible
        }
        */
    }
}