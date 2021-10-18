package com.example.tda367.model;


import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Map;

public class FirebaseHandler {
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReference(); //root reference
    private StorageReference imagesRef = storageRef.child("images"); //image reference -> /images
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    public void addAd(String carTitle, String carBrand, String carModel, String carYear, int carPrice, String carLocation, Uri selectedImage) {
        DocumentReference newCarRef = db.collection("cars").document();
        String carEmail = this.getCurrentUser().getEmail();
        //creates empty array for temp use in firebase
        ArrayList<Long> emptyDateList = new ArrayList<>();
        CarAdModel carAd = new CarAdModel(carTitle, newCarRef.getId(), carBrand, carModel, carYear, carPrice, carLocation, carEmail, emptyDateList);
        Map<String, Object> data = carAd.generateCarHashMap();
        newCarRef.set(data);
        uploadImage(selectedImage, newCarRef.getId());
    }

    public void signInWithEmailAndPassword(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((task -> {
                    if(task.isSuccessful()) {
                        System.out.println("login success");//in the future add listeners
                    }else{
                        System.out.println("login failed");
                        FirebaseAuthException e = (FirebaseAuthException)task.getException();
                        assert e != null;
                    }
                }));
    }

    public boolean isUserLoggedIn(){
        if (firebaseAuth.getInstance().getCurrentUser() != null){
            return true;
        }
        return false;
    }

    public FirebaseUser getCurrentUser(){
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public void uploadImage(Uri uri, String uid) {
        StorageReference imageRef = imagesRef.child(uid + "/car");//Gets last part of the path (file name)
        UploadTask uploadTask = imageRef.putFile(uri);


        //Observer for done/fail status.
        uploadTask.addOnFailureListener(exception -> {
            System.out.println("Failed to upload");
        }).addOnSuccessListener(taskSnapshot -> {
            System.out.println("Upload succeeded");
        });
    }



}
