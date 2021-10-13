package com.example.tda367;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private ArrayList<CarAdModel> carList;
    List<CarAdModel> listFull;
    Context context;

    public RecyclerViewAdapter(ArrayList<CarAdModel> carList, Context context){
        this.carList = carList;
        this.context = context;
        listFull=new ArrayList<>(carList);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView carBrand;
        private TextView carModel;
        private TextView carYear;
        private TextView carTitle;
        private TextView carLocation;
        private TextView carPrice;
        private TextView carID;
        ImageView imageView;

        public MyViewHolder(final View view){
            super(view);
            carBrand = view.findViewById(R.id.carBrand);
            carModel = view.findViewById(R.id.carModel);
            carYear = view.findViewById(R.id.carYear);
            carTitle = view.findViewById(R.id.carTitle);
            carLocation = view.findViewById(R.id.carLocation);
            carPrice = view.findViewById(R.id.carPrice);
            imageView = view.findViewById(R.id.carImage);
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

        Picasso.get().load(carList.get(position).getCarImageUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return carList.size();
    }
}