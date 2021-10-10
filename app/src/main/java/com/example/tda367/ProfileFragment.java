package com.example.tda367;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.example.tda367.ui.notifications.NotificationsFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment {
    private RecyclerViewAdapter recyclerViewAdapter;
    //private ProfileFragment profileFragment;
    //private FirebaseAuth firebaseAuth;

    private Button addCarAdButton;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    List<CarAdModel> carList;
    RecyclerView recyclerView;

    private Button logOutButton;

    //private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    //ArrayList<CarAdModel> adList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        //firebaseAuth = FirebaseAuth.getInstance();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.profile_user, container, false);

        progressDialog = new ProgressDialog(getContext());
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        carList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerViewProfile);

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

        setUpRecyclerView();
        return view;
    }


    private void setAdapter() {
        progressDialog.dismiss();
        recyclerViewAdapter = new RecyclerViewAdapter(carList,getContext());
        //RecyclerView recyclerView = view.findViewById(R.id.recyclerViewProfile);
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

    //Kanske ska köra Long istället för int
    private void setUpRecyclerView() {
        db.collection("Cars").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    carList = new ArrayList<>();
                    List<DocumentSnapshot> documents = task.getResult().getDocuments();
                    for (DocumentSnapshot documentSnapshot : documents) {


                        carList.add(new CarAdModel(documentSnapshot.getId().toString(), documentSnapshot.getString("title"), documentSnapshot.getString("brand"), documentSnapshot.getString("model")
                                , documentSnapshot.getString("year"), documentSnapshot.getString("price"),
                                documentSnapshot.getString("location"), documentSnapshot.getString("imageUrl")));//Kanske är CarID
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