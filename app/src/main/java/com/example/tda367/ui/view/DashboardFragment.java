package com.example.tda367.ui.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tda367.ui.controller.DashboardViewModel;
import com.example.tda367.ui.model.CarAdModel;
import com.example.tda367.RecyclerViewAdapter;
import com.example.tda367.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;



import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private RecyclerViewAdapter recyclerViewAdapter;
    private DashboardViewModel dashboardViewModel;
    private FirebaseAuth firebaseAuth;
    EditText inputSearch;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    ArrayList<CarAdModel> carList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_dashboard, container, false);

        //Search function
        inputSearch = view.findViewById(R.id.searchBar);
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    recyclerViewAdapter.getFilter().filter(s.toString());
                }
            }
        });

        setUpRecyclerView(view);
        return view;
    }

    private void setAdapter(View view){
        recyclerViewAdapter = new RecyclerViewAdapter(carList);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);
    }
    //Kanske ska köra Long istället för int
    private void setUpRecyclerView(View view){
        firestore.collection("cars").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        carList.add(new CarAdModel(documentSnapshot.getString("CarBrand"), documentSnapshot.getString("CarModel"), documentSnapshot.getString("CarTitle"), documentSnapshot.getString("CarYear"),
                                documentSnapshot.getString("CarLocation"), documentSnapshot.getLong("CarPrice").intValue(), documentSnapshot.getString("CarId"), documentSnapshot.getString("CarEmail")));//Kanske är CarID
                    }
                    setAdapter(view);
                } else {
                    System.out.println("Error: " + task.getException());
                }
            }
        });
    }
}