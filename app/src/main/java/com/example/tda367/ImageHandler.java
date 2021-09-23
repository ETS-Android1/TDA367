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
    StorageReference imagesRef = storageRef.child("images"); //image reference /images
    StorageReference userRef = imagesRef.child(getCurrentUid()); //user reference in /images/user

    private String getCurrentUid(){
        return FirebaseAuth.getInstance().getUid();
    }

    private String generatePath(String userid){
        return "TestString";
    }

    private void generateFolder(){

    }

}
