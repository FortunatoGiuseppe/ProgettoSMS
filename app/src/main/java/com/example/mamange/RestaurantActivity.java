package com.example.mamange;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class RestaurantActivity extends AppCompatActivity {

    public ImageView imgRestaurant;
    public TextView nomeRestaurant;
    public TextView cittaRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        cittaRestaurant=findViewById(R.id.citta_rest_act);
        imgRestaurant=findViewById(R.id.img_rest_act);
        nomeRestaurant=findViewById(R.id.nome_rest_act);



    }
}
