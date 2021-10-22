package edu.stulb.rentalcar.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import edu.stulb.rentalcar.model.CarAdModel;

import com.example.tda367.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.FirebaseFirestore;


public class CarDetailActivity extends Fragment {

    CarAdModel carAdModel;
    EditText carName, carBrand, carModel, carYear, carPrice, carLocation;
    private ImageView imageView;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_car_detail, container, false);

            // TODO change serializable
            //carAdModel = (CarAdModel) getIntent().getSerializableExtra("car");

            carName = (EditText) view.findViewById(R.id.carName);
            carBrand = (EditText) view.findViewById(R.id.carBrand);
            carModel = (EditText) view.findViewById(R.id.carModel);
            carYear = (EditText) view.findViewById(R.id.carYear);
            carPrice = (EditText) view.findViewById(R.id.carPrice);
            carLocation = (EditText) view.findViewById(R.id.carLocation);
            imageView = (ImageView) view.findViewById(R.id.imageView);

            carName.setText(carAdModel.getCarTitle());
            carBrand.setText(carAdModel.getCarBrand());
            carModel.setText(carAdModel.getCarModel());
            carYear.setText(carAdModel.getCarYear());
            //carPrice.setText(carAdModel.getCarPrice());
            carLocation.setText(carAdModel.getCarLocation());


            firestore = FirebaseFirestore.getInstance();

            return view;
        }
}
