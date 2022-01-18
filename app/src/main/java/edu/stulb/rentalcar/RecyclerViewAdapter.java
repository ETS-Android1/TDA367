package edu.stulb.rentalcar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tda367.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import edu.stulb.rentalcar.model.listing.Listing;
import edu.stulb.rentalcar.view.Browse.CarDetailFragment;
import edu.stulb.rentalcar.view.Browse.DashboardFragment;

/**
 *
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements Filterable {
    private final ArrayList<Listing> listingsList;
    List<Listing> listFull;
    Context context;


    public RecyclerViewAdapter(ArrayList<Listing> listingsList, Context context) {
        ArrayList<Listing> listingscopy = (ArrayList<Listing>) listingsList.clone();
        this.listingsList = listingscopy;
        listFull = new ArrayList<>(listingsList);
        this.context = context;
    }

    @Override
    public Filter getFilter() {
        return FilterResult;
    }

    //call this method when use call adapter from fragment
    Filter FilterResult = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String searchedText = constraint.toString().toLowerCase().trim();
            ArrayList<Listing> listTemp = new ArrayList<>();
            //if user didn'ot added anything in inputfield
            if (searchedText.isEmpty()) {

                //if user didnot selected any location
                if (!(DashboardFragment.location == null)) {
                    for (Listing mylist : listFull) {
                        if (mylist.getLocation().getCity().toLowerCase().contains(DashboardFragment.location)) {
                            listTemp.add(mylist);
                        }
                    }
                }
                //if user did'nt selected any location and didn't typed anything in EditTExt Field
                else {
                    listTemp.addAll(listFull);
                }

            } else {
                searchedText.length();
                // if user didnt typed in edittext text
                for (Listing myList : listFull) {
                    //&& car.getLocation().toString().equals(DashboardFragment.location.toLowerCase()
                    if (DashboardFragment.location == null) {
                        if (myList.getCar().getCarManufacturer().getManufacturer().toLowerCase().contains(searchedText)) {
                            listTemp.add(myList);
                        }
                    } else {
                        if (myList.getCar().getCarManufacturer().getManufacturer().toLowerCase().contains(searchedText) && myList.getLocation().getCity().toLowerCase().contains(DashboardFragment.location)) {
                            listTemp.add(myList);
                        }
                    }


                }

            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = listTemp;
            return filterResults;
        }

        //publish result=mean if i typed anything in input field it will live show onn recyclerview
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listingsList.clear();
            listingsList.addAll((Collection<? extends Listing>) results.values);
            notifyDataSetChanged();
        }
    };


    // Search function



    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView carBrand;
        private final TextView carModel;
        private final TextView carYear;
        private final TextView carTitle;
        private final TextView carLocation;
        private final TextView carPrice;
        ImageView imageView;

        public MyViewHolder(final View view) {
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Listing listing = listingsList.get(position);
        holder.carBrand.setText(listing.getCar().getCarManufacturer().getManufacturer());
        holder.carModel.setText(listing.getCar().getCarModel());
        holder.carYear.setText(listing.getCar().getCarYear());
        holder.carTitle.setText(listing.getCar().getCarManufacturer().getManufacturer() + " " + listing.getCar().getCarModel());
        holder.carLocation.setText(listing.getLocation().getCity());
        holder.carPrice.setText(String.valueOf(listing.getPricePerDay())+" kr/day");
        Glide.with(context).load(listing.getImagePath()).into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            Bundle args = new Bundle();
            args.putString("listingId", listing.getUid());
            CarDetailFragment carDetailFragment = new CarDetailFragment();

            carDetailFragment.setArguments(args);
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, carDetailFragment).commit();

        });
    }

    @Override
    public int getItemCount() {
        return listingsList.size();
    }
}