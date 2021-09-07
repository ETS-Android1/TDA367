package com.example.tda367.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tda367.RecyclerViewAdapter;
import com.example.tda367.R;

public class DashboardFragment extends Fragment {
    private RecyclerView recyclerView;
    private DashboardViewModel dashboardViewModel;

    String[] carList = {"skoda", "merca", "audi", "abbos skrotaraba"};

    public View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_dashboard, container, false);


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new RecyclerViewAdapter(carList));

        return view;
    }
    private void setAdapter(){
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(carList);
    }

}