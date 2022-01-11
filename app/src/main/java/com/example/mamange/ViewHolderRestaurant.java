package com.example.mamange;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
//Giuseppe Diasparra
public class ViewHolderRestaurant extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView textView;
    public TextView textViewcitta;
    public View v;

    public ViewHolderRestaurant(@NonNull View itemView) {
        super(itemView);
        textViewcitta=itemView.findViewById(R.id.citta_rest_single);
        imageView=itemView.findViewById(R.id.img_rest_single);
        textView=itemView.findViewById(R.id.nome_rest_single);
        v=itemView;
    }
}
