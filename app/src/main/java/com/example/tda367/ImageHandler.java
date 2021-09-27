package com.example.tda367;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ImageHandler {
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference(); //root reference
    StorageReference imagesRef = storageRef.child("images"); //image reference -> /images
    //StorageReference carRef = imagesRef.child(); //carID reference -> /images/carID





    private String getCurrentUid(){
        return FirebaseAuth.getInstance().getUid();
    }


    private void generateFolder(){

    }

}
