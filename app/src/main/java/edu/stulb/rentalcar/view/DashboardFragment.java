package edu.stulb.rentalcar.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tda367.R;

import edu.stulb.rentalcar.RecyclerViewAdapter;
import edu.stulb.rentalcar.controller.DashboardViewModel;

public class DashboardFragment extends Fragment {

    private RecyclerViewAdapter recyclerViewAdapter;
    private DashboardViewModel dashboardViewModel = new DashboardViewModel();
    EditText inputSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_dashboard, container, false);
        //Search function
        inputSearch = view.findViewById(R.id.searchBar);


        setAdapter(view);
        return view;
    }

    private void setAdapter(View view){
        recyclerViewAdapter = new RecyclerViewAdapter(dashboardViewModel.getListings(), getContext());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

}