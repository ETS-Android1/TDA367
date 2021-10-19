package com.example.tda367;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.content.Intent;
import android.widget.ImageView;

import com.example.tda367.model.CarAdModel;
import com.example.tda367.model.FirebaseHandler;
import com.example.tda367.view.CarDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements Filterable {
    private ArrayList<CarAdModel> carList;
    private FirebaseHandler firebaseHandler = new FirebaseHandler();
    List<CarAdModel> listFull;
    Context context;


    public RecyclerViewAdapter(ArrayList<CarAdModel> carList, Context context){
        this.carList = carList;
        listFull = new ArrayList<>(carList);
        this.context = context;
    }

    // Search function
    @Override
    public Filter getFilter() {
        return FilterResult;
    }

    Filter FilterResult=new Filter() {
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
            FilterResults filterResults = new FilterResults();
            filterResults.values = listTemp;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            carList.clear();
            carList.addAll((Collection<? extends CarAdModel>) results.values);
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
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CarAdModel car = carList.get(position);
        String carID = car.getCarID();
        String url = firebaseHandler.GetCarImageUrl(carID);
        holder.carBrand.setText(car.getCarBrand());
        holder.carModel.setText(car.getCarModel());
        holder.carYear.setText(car.getCarYear());
        holder.carTitle.setText(car.getCarTitle());
        holder.carLocation.setText(car.getCarLocation());
        holder.carPrice.setText(String.valueOf(car.getCarPrice()));

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/stulb-a595c.appspot.com/o/images%2Fe2eF5egOPkvoxfLt6jMM%2Fcar?alt=media&token=08f77c2b-c312-4648-8462-11c20fd4bc07").fit().centerCrop().into(holder.imageView);


        //Move to car detail activity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CarDetailActivity.class);
                intent.putExtra("car", carList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }
}