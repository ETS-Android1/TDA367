package com.example.tda367.ui.dashboard;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tda367.CarAdModel;
import com.example.tda367.RecyclerViewAdapter;
import com.example.tda367.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;



import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private RecyclerViewAdapter recyclerViewAdapter;
    private DashboardViewModel dashboardViewModel;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    //FirebaseFirestore db;
    ProgressDialog progressDialog;
    EditText inputSearch;
    RecyclerView recyclerView;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    ArrayList<CarAdModel> carList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_dashboard, container, false);

        progressDialog = new ProgressDialog(getContext());
        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        recyclerView=view.findViewById(R.id.recyclerView);

        carList = new ArrayList<>();
        recyclerViewAdapter = new RecyclerViewAdapter(carList, getContext());



        setUpRecyclerView();
        return view;
    }

    private void setAdapter(){
        progressDialog.dismiss();
        recyclerViewAdapter = new RecyclerViewAdapter(carList, getContext());
        //RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);
    }
    //Kanske ska köra Long istället för int
    private void setUpRecyclerView(){
        firestore.collection("cars").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        carList.add(new CarAdModel(documentSnapshot.getString("CarBrand"), documentSnapshot.getString("CarModel"), documentSnapshot.getString("CarTitle"), documentSnapshot.getString("CarYear"),
                                documentSnapshot.getString("CarLocation"), documentSnapshot.getLong("CarPrice").intValue(), documentSnapshot.getString("CarId"), documentSnapshot.getString("CarImageUrl")));//Kanske är CarID
                    }
                    setAdapter();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "" + task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpRecyclerView();
    }

}