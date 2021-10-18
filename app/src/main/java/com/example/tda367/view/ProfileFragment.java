package com.example.tda367.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tda367.model.CarAdModel;
import com.example.tda367.R;
import com.example.tda367.RecyclerViewAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {
    private RecyclerViewAdapter recyclerViewAdapter;
    private ProfileFragment profileFragment;
    private FirebaseAuth firebaseAuth;
    private Button addCarAdButton;
    private Button logOutButton;
    private String userEmail;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    ArrayList<CarAdModel> adList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        userEmail = firebaseAuth.getCurrentUser().getEmail().toString();
        System.out.println(userEmail);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.profile_user, container, false);
        setUpRecyclerView(view);
        addCarAdButton = (Button) view.findViewById(R.id.addCarAdButton);
        addCarAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAddCarAdFragment();
            }
        });
        logOutButton = (Button) view.findViewById(R.id.logOutButton);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                makeToast("You have been logged out!");
            }
        });
        return view;
    }

    private void setAdapter(View view) {
        recyclerViewAdapter = new RecyclerViewAdapter(adList);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewProfile);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void setUpRecyclerView(View view){
        firestore.collection("cars").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        if (documentSnapshot.getString("CarEmail").equals(userEmail)){
                            adList.add(new CarAdModel(documentSnapshot.getString("CarBrand"), documentSnapshot.getString("CarModel"), documentSnapshot.getString("CarTitle"), documentSnapshot.getString("CarYear"),
                                    documentSnapshot.getString("CarLocation"), documentSnapshot.getLong("CarPrice").intValue(), documentSnapshot.getString("CarId"), documentSnapshot.getString("CarEmail"), (ArrayList<Long>) documentSnapshot.get("CarBookedDates")));//Kanske är CarID
                        }

                    }
                    setAdapter(view);
                } else {
                    System.out.println("Error: " + task.getException());
                }
            }
        });
    }

    private void loadAddCarAdFragment(){
        Fragment addCarAdFragment = new AddCarAdFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, addCarAdFragment).commit();
    }
    private void loadSignInFragment(){
        Fragment signInFragment = new SignInFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signInFragment).commit();
    }
    private void signOut(){
        FirebaseAuth.getInstance().signOut();
        loadSignInFragment();
    }

    private void makeToast(CharSequence message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getContext(), message, duration);
        toast.show();
    }



}