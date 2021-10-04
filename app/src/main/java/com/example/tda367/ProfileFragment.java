package com.example.tda367;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tda367.ui.notifications.NotificationsFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {
    private RecyclerViewAdapter recyclerViewAdapter;
    private ProfileFragment profileFragment;
    private FirebaseAuth firebaseAuth;
    private Button addCarAdButton;
    private Button logOutButton;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    ArrayList<CarAdModel> adList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.profile_user, container, false);

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

    private void loadAddCarAdFragment(){
        Fragment addCarAdFragment = new AddCarAdFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, addCarAdFragment).commit();
    }
    private void loadSignInFragment(){
        Fragment signInFragment = new NotificationsFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signInFragment).commit();
    }
    private void signOut(){
        FirebaseAuth.getInstance().signOut();
        loadSignInFragment();
    }



}