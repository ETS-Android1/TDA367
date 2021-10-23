package edu.stulb.rentalcar.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.tda367.R;

import edu.stulb.rentalcar.controller.CarDetailViewModel;
import edu.stulb.rentalcar.model.listing.Listing;
import edu.stulb.rentalcar.model.listing.ListingHandler;


public class CarDetailFragment extends Fragment {
    CarDetailViewModel carDetailViewModel = new CarDetailViewModel();
    EditText carName, carBrand, carModel, carYear, carPrice, carLocation;
    private ImageView imageView;
    String value1;
    ListingHandler listingHandler = ListingHandler.getInstance();


    @SuppressLint("SetTextI18n")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_car_detail, container, false);
        Bundle bundle = getArguments();
        if (bundle != null){
            value1 = bundle.getString("listingId");
        }
        Listing clickedListing = carDetailViewModel.getClickedListing(value1);


        carName = (EditText) view.findViewById(R.id.carName);
        carBrand = (EditText) view.findViewById(R.id.carBrand);
        carModel = (EditText) view.findViewById(R.id.carModel);
        carYear = (EditText) view.findViewById(R.id.carYear);
        carPrice = (EditText) view.findViewById(R.id.carPrice);
        carLocation = (EditText) view.findViewById(R.id.carLocation);
        imageView = (ImageView) view.findViewById(R.id.imageView);
    if (clickedListing!=null){
        carName.setText(clickedListing.getCar().getCarManufacturer().getManufacturer() + " " + clickedListing.getCar().getCarModel());
        carBrand.setText(clickedListing.getCar().getCarManufacturer().getManufacturer());
        carModel.setText(clickedListing.getCar().getCarModel());
        carYear.setText(clickedListing.getCar().getCarYear());
        carPrice.setText(String.valueOf(clickedListing.getPricePerDay()));
        carLocation.setText(clickedListing.getLocation().getCity());
    }


        return view;
    }
}