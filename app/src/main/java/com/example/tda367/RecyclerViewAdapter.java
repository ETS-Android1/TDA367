package com.example.tda367;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private ArrayList<CarAdModel> carList;

    public RecyclerViewAdapter(ArrayList<CarAdModel> carList){
        this.carList = carList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView carBrand;
        private TextView carModel;
        private TextView carYear;
        private TextView carTitle;
        private TextView carLocation;
        private TextView carPrice;
        private TextView carID;

        public MyViewHolder(final View view){
            super(view);
            carBrand = view.findViewById(R.id.carBrand);
            carModel = view.findViewById(R.id.carModel);
            carYear = view.findViewById(R.id.carYear);
            carTitle = view.findViewById(R.id.carTitle);
            carLocation = view.findViewById(R.id.carLocation);
            carPrice = view.findViewById(R.id.carPrice);
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_listitem, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        CarAdModel car = carList.get(position);
        holder.carBrand.setText(car.getCarBrand());
        holder.carModel.setText(car.getCarModel());
        holder.carYear.setText(car.getCarYear());
        holder.carTitle.setText(car.getCarTitle());
        holder.carLocation.setText(car.getCarLocation());
        holder.carPrice.setText(String.valueOf(car.getCarPrice()));

    }

    @Override
    public int getItemCount() {
        return carList.size();
    }
}