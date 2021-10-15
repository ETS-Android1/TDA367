package com.example.tda367.ui.model;


import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class FirebaseHandler {
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference(); //root reference
    StorageReference imagesRef = storageRef.child("images"); //image reference -> /images

    private String getCurrentUid(){
        return FirebaseAuth.getInstance().getUid();
    }


    private void generateFolder(){


    }

    public void uploadPicture(Uri uri, String uid) {
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
