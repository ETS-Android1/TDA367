package com.example.tda367.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tda367.CarAdModel;
import com.example.tda367.RecyclerViewAdapter;
import com.example.tda367.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {
    private RecyclerView recyclerView;
    private DashboardViewModel dashboardViewModel;
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    ArrayList<CarAdModel> carList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_dashboard, container, false);
        setUpRecyclerView();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        recyclerView.setAdapter(new RecyclerViewAdapter(carList));

        return view;
    }
    private void setAdapter(){
        //fillList();
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(carList);
    }
    private void fillList(){

        carList.add(new CarAdModel("skoda", "gbg", 200));
        carList.add(new CarAdModel("audi", "gbg", 300));
        carList.add(new CarAdModel("merca", "gbg", 300));
    }
    private void setUpRecyclerView(){
        firestore.collection("cars").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    List<String> list = new ArrayList<>();
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        list.add(documentSnapshot.getId());
                    }
                    System.out.println(list.toString());
                } else {
                    System.out.println("Error: " + task.getException());
                }

            }
        });
    }

}