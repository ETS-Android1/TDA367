package edu.stulb.rentalcar.model;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class DatabaseHandler implements IQueryManager{

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();



    public User getUser() {
        User user;
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        firestore.collection("testUsers").document("hannes@gmail.com").get().addOnCompleteListener(task -> {

        });

        //User user = new User(name, email, );
        return null;
    }



    @Override
    public void createUser() {

    }

    @Override
    public void createListing() {

    }

    @Override
    public boolean isUserSignedIn() {
        return false;
    }

    @Override
    public void signOut() {

    }

    @Override
    public Listing getListing() {
        return null;
    }
}
