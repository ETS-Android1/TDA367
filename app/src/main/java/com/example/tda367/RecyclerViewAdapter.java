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
    private ArrayList<CarModel> carList;

    public RecyclerViewAdapter(ArrayList<CarModel> carList){
        this.carList = carList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameText;
        private TextView areaText;
        private TextView priceText;

        public MyViewHolder(final View view){
            super(view);
            nameText = view.findViewById(R.id.carName);
            areaText = view.findViewById(R.id.carArea);
            priceText = view.findViewById(R.id.carCost);
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
        CarModel car = carList.get(position);
        holder.nameText.setText(car.getCarName());
        holder.areaText.setText(car.getCarArea());
        String cost = Integer.toString(car.getCarCost());
        holder.priceText.setText(cost);
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }
}