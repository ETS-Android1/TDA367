package com.example.tda367;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {
    private RecyclerViewAdapter recyclerViewAdapter;
    private ProfileFragment profileFragment;
    private FirebaseAuth firebaseAuth;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    ArrayList<CarAdModel> adList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.profile_user, container, false);
        return view;
    }

    private void setAdapter(View view) {
        recyclerViewAdapter = new RecyclerViewAdapter(adList);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewProfile);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);
    }



}