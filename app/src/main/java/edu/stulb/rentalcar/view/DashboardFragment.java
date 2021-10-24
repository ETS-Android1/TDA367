package edu.stulb.rentalcar.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tda367.R;

import edu.stulb.rentalcar.RecyclerViewAdapter;
import edu.stulb.rentalcar.controller.DashboardViewModel;

//implement Radio button and TextWatcher
public class DashboardFragment extends Fragment implements RadioGroup.OnCheckedChangeListener , TextWatcher {

    private RecyclerViewAdapter recyclerViewAdapter;
    private final DashboardViewModel dashboardViewModel = new DashboardViewModel();

    EditText inputSearch;
    RadioGroup radioGroup;
    public static String location = null;

    public View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_dashboard, container, false);
        //init all varibale function
        inputSearch = view.findViewById(R.id.searchBar);
        //assign and init recyclerview adapter
        setAdapter(view);
        //init radio button .
        radioGroup=view.findViewById(R.id.radioGroup);

        //settextwatcher on edittext
        inputSearch.addTextChangedListener(this);
        //add check changed listener when user click on radio button
        radioGroup.setOnCheckedChangeListener(this);


        return view;
    }

    private void setAdapter(View view){
        recyclerViewAdapter = new RecyclerViewAdapter(dashboardViewModel.getListings(), getContext());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        //when user click on any radio button from radio group this onCheckedCHnaged listner invoke
        RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
        location = radioButton.getText().toString().toLowerCase();
        recyclerViewAdapter.getFilter().filter(inputSearch.getText().toString());


    }

    //while typing it will call before putting values to searchbar
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    //while typing it will call when changing and  putting values to searchbar
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    //when successfully added text in search bar
    @Override
    public void afterTextChanged(Editable s) {
        recyclerViewAdapter.getFilter().filter(s.toString());
    }
}