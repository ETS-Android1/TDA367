package com.example.tda367.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tda367.CarAdModel;
import com.example.tda367.ProfileFragment;
import com.example.tda367.RecyclerViewAdapter;
import com.example.tda367.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;



import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private RecyclerViewAdapter recyclerViewAdapter;
    private DashboardViewModel dashboardViewModel;
    private FirebaseAuth firebaseAuth;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    ArrayList<CarAdModel> carList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_dashboard, container, false);
        setUpRecyclerView(view);

        return view;
    }
    private void setAdapter(View view){
        recyclerViewAdapter = new RecyclerViewAdapter(carList);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);
    }
    private void setUpRecyclerView(View view){
        firestore.collection("cars").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        //TODO ändra till int, documentSnapshot.getString("price")
                        carList.add(new CarAdModel(documentSnapshot.getString("name"), documentSnapshot.getString("area"), 200));

                    }
                    setAdapter(view);
                } else {
                    System.out.println("Error: " + task.getException());
                }
            }
        });
    }
}