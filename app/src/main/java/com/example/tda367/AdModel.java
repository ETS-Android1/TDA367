package com.example.tda367;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdModel extends AppCompatActivity {
    int price;
    EditText editPrice;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editPrice = (EditText) findViewById(R.id.editPrice); // använd "editPrice" som id för xml textfieldet
        saveButton = (Button) findViewById(R.id.saveButton); // samma här
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                price = Integer.valueOf(editPrice.getText().toString());
                showToast("Price is updated!");

            }
        });

    }

    private void showToast(String text) {
        Toast.makeText(AdModel.this, text, Toast.LENGTH_LONG).show();
    }
}