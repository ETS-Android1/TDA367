package edu.stulb.rentalcar.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.tda367.R;

import edu.stulb.rentalcar.controller.CarDetailViewModel;
import edu.stulb.rentalcar.controller.ProfileViewModel;
import edu.stulb.rentalcar.controller.SignInViewModel;
import edu.stulb.rentalcar.model.listing.Listing;
import edu.stulb.rentalcar.model.listing.ListingHandler;


public class CarDetailFragment extends Fragment {
    CarDetailViewModel carDetailViewModel = new CarDetailViewModel();
    EditText carName, carBrand, carModel, carYear, carPrice, carLocation;
    Button reservationButton;
    private ImageView imageView;
    String receivedString;
    ListingHandler listingHandler = ListingHandler.getInstance();


    @SuppressLint("SetTextI18n")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_car_detail, container, false);
        Bundle bundle = getArguments();
        if (bundle != null){
            receivedString = bundle.getString("listingId");
        }
        Listing clickedListing = carDetailViewModel.getClickedListing(receivedString);


        carName = (EditText) view.findViewById(R.id.carName);
        carBrand = (EditText) view.findViewById(R.id.carBrand);
        carModel = (EditText) view.findViewById(R.id.carModel);
        carYear = (EditText) view.findViewById(R.id.carYear);
        carPrice = (EditText) view.findViewById(R.id.carPrice);
        carLocation = (EditText) view.findViewById(R.id.carLocation);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        reservationButton = (Button) view.findViewById(R.id.reservationButton);
    if (clickedListing!=null){
        carName.setText(clickedListing.getCar().getCarManufacturer().getManufacturer() + " " + clickedListing.getCar().getCarModel());
        carBrand.setText(clickedListing.getCar().getCarManufacturer().getManufacturer());
        carModel.setText(clickedListing.getCar().getCarModel());
        carYear.setText(clickedListing.getCar().getCarYear());
        carPrice.setText(String.valueOf(clickedListing.getPricePerDay()));
        carLocation.setText(clickedListing.getLocation().getCity());
    }
    reservationButton.setOnClickListener(v -> {
        if (!carDetailViewModel.isUserSignedIn()) {
            carDetailViewModel.loadSignInFragment(getParentFragmentManager());
            makeToast("You need to log in first!");
        }
        else {
            carDetailViewModel.loadDateSelectorFragment(getParentFragmentManager(), receivedString);
        }

    });


        return view;
    }

    private void makeToast(CharSequence message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getContext(), message, duration);
        toast.show();
    }
}
