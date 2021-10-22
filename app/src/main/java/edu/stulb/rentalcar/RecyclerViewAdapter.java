package edu.stulb.rentalcar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tda367.R;

import java.util.ArrayList;
import java.util.List;

import edu.stulb.rentalcar.model.listing.Listing;


/**
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private ArrayList<Listing> listingsList;
    List<Listing> listFull;
    Context context;


    public RecyclerViewAdapter(ArrayList<Listing> listingsList, Context context){
        this.listingsList = listingsList;
        listFull = new ArrayList<>(listingsList);
        this.context = context;
    }


    // Search function
    /*
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
*/
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Listing listing = listingsList.get(position);
        String carID = listing.getUid();
        //String url = firebaseHandler.GetCarImageUrl(carID);
        holder.carBrand.setText(listing.getCar().getCarManufacturer().getManufacturer());
        holder.carModel.setText(listing.getCar().getCarModel());
        holder.carYear.setText(listing.getCar().getCarModel());
        holder.carTitle.setText(listing.getCar().getCarManufacturer().getManufacturer()+" "+listing.getCar().getCarModel());
        holder.carLocation.setText(listing.getLocation().getCity());
        holder.carPrice.setText(String.valueOf(listing.getPricePerDay()));

        //Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/stulb-a595c.appspot.com/o/images%2Fe2eF5egOPkvoxfLt6jMM%2Fcar?alt=media&token=08f77c2b-c312-4648-8462-11c20fd4bc07").fit().centerCrop().into(holder.imageView);
/*
        //Move to car detail activity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CarDetailFragment.class);
                intent.putExtra("car", listingsList.get(position));
                context.startActivity(intent);
            }
        });

 */
    }

    @Override
    public int getItemCount() {
        return listingsList.size();
    }
}