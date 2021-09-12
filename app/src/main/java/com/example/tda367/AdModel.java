package com.example.tda367;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AdModel extends AppCompatActivity {
    int price;
    String name;

    EditText editName;
    EditText editPrice;

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = (EditText) findViewById(R.id.editName);
        editPrice = (EditText) findViewById(R.id.editPrice);
        saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editName.getText().toString();
                price = Integer.valueOf(editPrice.getText().toString());

                showToast("Your details have been updated!");
            }
        });
    }

    private void showToast(String text) {
        Toast.makeText(AdModel.this, text, Toast.LENGTH_LONG).show();
    }
}