package com.example.tda367.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tda367.CarModel;
import com.example.tda367.RecyclerViewAdapter;
import com.example.tda367.R;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    private RecyclerView recyclerView;
    private DashboardViewModel dashboardViewModel;

    ArrayList<CarModel> carList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        fillList();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_dashboard, container, false);


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
        carList.add(new CarModel("skoda", "gbg", 200));
        carList.add(new CarModel("audi", "gbg", 300));
        carList.add(new CarModel("merca", "gbg", 300));
    }

}