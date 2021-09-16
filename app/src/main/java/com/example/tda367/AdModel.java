package com.example.tda367;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tda367.ui.dashboard.DashboardViewModel;
import com.example.tda367.ui.home.HomeViewModel;
import com.example.tda367.ui.notifications.NotificationsViewModel;

import org.w3c.dom.Text;

public class AdModel extends Fragment {
    private NotificationsViewModel notificationsViewModel;
    int price;
    String name;

    EditText editName;
    EditText editPrice;

    Button saveButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        super.onCreate(savedInstanceState);

        editName = (EditText) view.findViewById(R.id.editName);
        editPrice = (EditText) view.findViewById(R.id.editPrice);
        saveButton = (Button) view.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editName.getText().toString();
                price = Integer.valueOf(editPrice.getText().toString());

            }
        });
        return view;
    }
}