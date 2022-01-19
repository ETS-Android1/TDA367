package edu.stulb.rentalcar.view.Browse;

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

import com.bumptech.glide.Glide;
import com.example.tda367.R;

import java.util.Objects;

import edu.stulb.rentalcar.controller.Browse.CarDetailViewModel;
import edu.stulb.rentalcar.model.listing.Listing;

public class CarDetailFragment extends Fragment {
    CarDetailViewModel carDetailViewModel = new CarDetailViewModel();
    EditText carName, carBrand, carModel, carYear, carPrice, carLocation, contactEmail;
    Button reservationButton;
    private ImageView imageView;
    String receivedString;


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
        contactEmail = (EditText) view.findViewById(R.id.contactEmail) ;
        reservationButton = (Button) view.findViewById(R.id.reservationButton);

    if (clickedListing!=null){
        carName.setText(clickedListing.getProduct().getName());
        carBrand.setText(Objects.requireNonNull(clickedListing.getProduct().getExtraInfo().get("CarManufacturer")).toString());
        carModel.setText(Objects.requireNonNull(clickedListing.getProduct().getExtraInfo().get("CarModel")).toString());
        carYear.setText(Objects.requireNonNull(Objects.requireNonNull(clickedListing.getProduct().getExtraInfo().get("CarYear")).toString()));
        carPrice.setText(String.valueOf(clickedListing.getProduct().getPricePerDay())+" kr");
        carLocation.setText(clickedListing.getProduct().getLocation().getCity());
        contactEmail.setText(clickedListing.getUserEmail());
        Glide.with(getContext()).load(clickedListing.getProduct().getImagePath()).into(imageView);
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
