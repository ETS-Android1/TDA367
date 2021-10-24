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

import com.example.tda367.R;

import edu.stulb.rentalcar.RecyclerViewAdapter;
import edu.stulb.rentalcar.controller.ProfileViewModel;


public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel = new ProfileViewModel();

    private RecyclerViewAdapter recyclerViewAdapter;
    private Button addCarAdButton;
    private Button logOutButton;
    private Button editButton;

    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.profile_user, container, false);

        addCarAdButton = view.findViewById(R.id.addCarAdButton);
        addCarAdButton.setOnClickListener(v -> profileViewModel.loadAddCarAdFragment(getParentFragmentManager()));

        logOutButton = view.findViewById(R.id.logOutButton);
        logOutButton.setOnClickListener(v -> {
            signOut();
            makeToast("You have been logged out!");
        });

        editButton = view.findViewById(R.id.editButton);
        editButton.setOnClickListener(v ->
                profileViewModel.loadEditProfileFragment(getParentFragmentManager()));

        setAdapter(view);
        return view;
    }

    private void setAdapter(View view) {
        recyclerViewAdapter = new RecyclerViewAdapter(profileViewModel.getUsersListings(), getContext());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewProfile);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);
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