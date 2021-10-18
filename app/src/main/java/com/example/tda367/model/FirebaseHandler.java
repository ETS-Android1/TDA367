package com.example.tda367.model;


import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class FirebaseHandler {
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReference(); //root reference
    private StorageReference imagesRef = storageRef.child("images"); //image reference -> /images
    private FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    public void addAd(String carTitle, String carBrand, String carModel, String carYear, int carPrice, String carLocation, Uri selectedImage) {
        DocumentReference newCarRef = fireStore.collection("cars").document();
        String carEmail = this.getCurrentUser().getEmail();
        //creates empty array for temp use in firebase
        ArrayList<Long> emptyDateList = new ArrayList<>();
        CarAdModel carAd = new CarAdModel(carTitle, newCarRef.getId(), carBrand, carModel, carYear, carPrice, carLocation, carEmail, emptyDateList);
        Map<String, Object> data = carAd.generateCarHashMap();
        newCarRef.set(data);
        uploadImage(selectedImage, newCarRef.getId());
    }

    public FirebaseFirestore getFireStore() {
        return fireStore;
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

    public void registerUserWithEmailAndPassword(String email, String password, String firstName,
                                                 String surName, String address, String city, String phoneNumber){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((task -> {
                    if(task.isSuccessful()) {
                        System.out.println("login success");
                        String uid = firebaseAuth.getUid();
                        fireStore.collection("users").document(uid).set(generateHashMap(uid, email, firstName, surName, address, city, phoneNumber));

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

    public CollectionReference getCollectionReference(String string){
        return fireStore.collection(string);
    }

    public FirebaseUser getCurrentUser(){
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public void signOut(){
        firebaseAuth.getInstance().signOut();
    }

    public void uploadImage(Uri uri, String uid) {
        StorageReference imageRef = imagesRef.child(uid + "/car");
        UploadTask uploadTask = imageRef.putFile(uri);


        //Observer for done/fail status.
        uploadTask.addOnFailureListener(exception -> {
            System.out.println("Failed to upload");
        }).addOnSuccessListener(taskSnapshot -> {
            System.out.println("Upload succeeded");
        });
    }
    public Map<String, Object> generateHashMap(String userID, String email, String firstName, String surname, String address, String city, String phoneNumber) {
        Map<String, Object> UsersID = new HashMap<String, Object>();

        //KEYS gives String to field inside document
        UsersID.put("UserID", userID);
        UsersID.put("UserEmail", email);
        UsersID.put("UserFirstName", firstName);
        UsersID.put("UserSurname", surname);
        UsersID.put("UserAddress", address);
        UsersID.put("UserCity", city);
        UsersID.put("UserPhoneNumber", phoneNumber);

        return UsersID;
    }



}
