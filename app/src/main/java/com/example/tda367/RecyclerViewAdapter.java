package com.example.tda367;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements Filterable {
    List<CarAdModel> list;
    List<CarAdModel> listFull;
    Context context;


    public RecyclerViewAdapter(List<CarAdModel> carList, Context context) {
        this.list = carList;
        this.context = context;
        listFull=new ArrayList<>(list);
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    Filter FilterResult = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String searchedText=constraint.toString().toLowerCase().trim();
            List<CarAdModel>listTemp=new ArrayList<>();
            if (searchedText.isEmpty() || searchedText.length()==0)
            {
                listTemp.addAll(listFull);
            }else {
                for ( CarAdModel car:listFull)
                {
                    if (car.getCarTitle().toString().toLowerCase().contains(searchedText))
                    {
                        listTemp.add(car);
                    }
                }

            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=listTemp;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((Collection<? extends CarAdModel>) results.values);
            notifyDataSetChanged();
        }
    };


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
        CarAdModel car = list.get(position);
        holder.carBrand.setText(car.getCarBrand());
        holder.carModel.setText(car.getCarModel());
        holder.carYear.setText(car.getCarYear());
        holder.carTitle.setText(car.getCarTitle());
        holder.carLocation.setText(car.getCarLocation());
        holder.carPrice.setText(String.valueOf(car.getCarPrice()));

        Picasso.get().load(list.get(position).getImageUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}