package edu.stulb.rentalcar.view;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import edu.stulb.rentalcar.model.CarAdModel;

import com.example.tda367.R;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;


public class CarDetailActivity extends AppCompatActivity {

    CarAdModel carAdModel;
    EditText carName, carBrand, carModel, carYear, carPrice, carLocation;
    private ImageView imageView;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        carAdModel = (CarAdModel) getIntent().getSerializableExtra("car");
        carName = (EditText) findViewById(R.id.carName);
        carBrand = (EditText) findViewById(R.id.carBrand);
        carModel = (EditText) findViewById(R.id.carModel);
        carYear = (EditText) findViewById(R.id.carYear);
        carPrice = (EditText) findViewById(R.id.carPrice);
        carLocation = (EditText) findViewById(R.id.carLocation);
        imageView = (ImageView) findViewById(R.id.imageView);

        carName.setText(carAdModel.getCarTitle());
        carBrand.setText(carAdModel.getCarBrand());
        carModel.setText(carAdModel.getCarModel());
        carYear.setText(carAdModel.getCarYear());
        //carPrice.setText(carAdModel.getCarPrice());
        carLocation.setText(carAdModel.getCarLocation());


        firestore = FirebaseFirestore.getInstance();
    }

}
