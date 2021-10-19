package com.example.tda367.model;


import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
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

    private String url;


    public String getUrl(){
        return url;
    }

    public void addAd(String carTitle, String carBrand, String carModel, String carYear, int carPrice, String carLocation, Uri selectedImage) {
        DocumentReference newCarRef = fireStore.collection("cars").document();
        String carEmail = this.getCurrentUser().getEmail();
        //creates empty array for temp use in firebase
        ArrayList<Long> emptyDateList = new ArrayList<>();
        CarAdModel carAd = new CarAdModel(carBrand, carModel, carTitle, carYear, carLocation, carPrice, newCarRef.getId(), carEmail, emptyDateList);
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
                        System.out.println("signup success");
                        String uid = firebaseAuth.getUid();
                        fireStore.collection("users").document(uid).set(generateHashMap(uid, email, firstName, surName, address, city, phoneNumber));

                    }else{
                        System.out.println("signup failed");
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

    public void getAds(String userEmail){
        ArrayList<CarAdModel> adList = new ArrayList<CarAdModel>();

        fireStore.collection("cars").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                    if (documentSnapshot.getString("CarEmail").equals(userEmail)){
                        adList.add(new CarAdModel(documentSnapshot.getString("CarBrand"),
                                documentSnapshot.getString("CarModel"),
                                documentSnapshot.getString("CarTitle"),
                                documentSnapshot.getString("CarYear"),
                                documentSnapshot.getString("CarLocation"),
                                documentSnapshot.getLong("CarPrice").intValue(),
                                documentSnapshot.getString("CarId"),
                                documentSnapshot.getString("CarEmail"),
                                (ArrayList<Long>) documentSnapshot.get("CarBookedDates")));
                    }

                }
            }
        });
    }

    public void registerCard(String cardNumber, String cardHolderName, String date, String cvv){
        String uid = firebaseAuth.getUid();
        fireStore.collection("users").document(uid).collection("CardInfo").
                document("Card").set(generateCardHashMap(cardNumber, cardHolderName, date, cvv)).
                addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        System.out.println("Card info added");
                    }

        });
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

    public void GenerateCarImageUrl(String carID){
        imagesRef.child(carID+"/car").getDownloadUrl().addOnSuccessListener(uri -> {
            url = uri.toString();
            System.out.println(url + " THIS IS GENERATE");
        }).addOnFailureListener(e -> System.out.println("Failed to get download url for image"));
    }

    public String GetCarImageUrl(String carID){
        GenerateCarImageUrl(carID);
        System.out.println("This is get");
        return getUrl();
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

    public Map<String, Object> generateCardHashMap(String cardNumber, String cardHolderName, String date, String cvv) {
        Map<String, Object> CardInfo = new HashMap<String, Object>();

        //KEYS gives String to field inside document
        CardInfo.put("CardholderNumber", cardNumber);
        CardInfo.put("CardholderName", cardHolderName);
        CardInfo.put("CardholderDate", date);
        CardInfo.put("CardholderCVVNumber", cvv);

        return CardInfo;
    }



}
