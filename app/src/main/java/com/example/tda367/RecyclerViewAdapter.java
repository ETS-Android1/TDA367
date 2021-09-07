package com.example.tda367;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private String[] carList;

    public RecyclerViewAdapter(String[] carList){
        this.carList = carList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameText;

        public MyViewHolder(final View view){
            super(view);
            nameText = view.findViewById(R.id.carName);
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_listitem, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        String name = carList[position];
        holder.nameText.setText(name);
    }

    @Override
    public int getItemCount() {
        return carList.length;
    }
}