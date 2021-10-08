package com.example.tda367;


import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class ImageHandler {
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReference(); //root reference
    private StorageReference imagesRef = storageRef.child("images"); //image reference -> /images

    public String getFirebasePath(String uid){
        StorageReference carRef = imagesRef.child("2"); //add carID as parameter when ready
        // carID image reference -> /images/carID/example.jpg
        return carRef.getPath();
    }

    public void uploadPicture(Uri uri, String uid){
        Uri file = Uri.fromFile(new File(uri.getPath()));
        StorageReference imageRef = imagesRef.child(uid + file.getLastPathSegment());//Gets last part of the path (file name)
        UploadTask uploadTask = imageRef.putFile(file);


        //Observer for done/fail status.
        uploadTask.addOnFailureListener(exception -> {
            
        }).addOnSuccessListener(taskSnapshot -> {

        });



    }



}
