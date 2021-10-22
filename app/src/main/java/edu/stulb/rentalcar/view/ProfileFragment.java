package edu.stulb.rentalcar.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.stulb.rentalcar.controller.ProfileViewModel;
import edu.stulb.rentalcar.model.CarAdModel;
import com.example.tda367.R;
import edu.stulb.rentalcar.RecyclerViewAdapter;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel = new ProfileViewModel();

    private RecyclerViewAdapter recyclerViewAdapter;
    private Button addCarAdButton;
    private Button logOutButton;
    private String userEmail;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    ArrayList<CarAdModel> adList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.profile_user, container, false);
        setUpRecyclerView(view);

        userEmail = profileViewModel.getUserEmail();
        addCarAdButton = (Button) view.findViewById(R.id.addCarAdButton);
        addCarAdButton.setOnClickListener(v -> profileViewModel.loadAddCarAdFragment(getParentFragmentManager()));

        logOutButton = (Button) view.findViewById(R.id.logOutButton);
        logOutButton.setOnClickListener(v -> {
            signOut();
            makeToast("You have been logged out!");
        });
        return view;
    }

    private void setAdapter(View view) {
        recyclerViewAdapter = new RecyclerViewAdapter(adList, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewProfile);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void setUpRecyclerView(View view){
        firestore.collection("cars").get().addOnCompleteListener(task -> {
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
        });
    }

    private void signOut(){
        profileViewModel.signOut();
        profileViewModel.loadSignInFragment(getParentFragmentManager());
    }

    private void makeToast(CharSequence message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getContext(), message, duration);
        toast.show();
    }



}