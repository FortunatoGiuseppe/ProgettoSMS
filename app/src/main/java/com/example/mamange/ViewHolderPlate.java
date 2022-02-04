package com.example.mamange;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderPlate extends RecyclerView.ViewHolder {
    public TextView textViewPlate;
    public View v_plate;

    public ViewHolderPlate(@NonNull View itemView) {
        super(itemView);
        textViewPlate= itemView.findViewById(R.id.plate);
        v_plate = itemView;
    }
}