package com.example.mamange;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

//Giuseppe Diasparra
public class RestaurantActivity extends AppCompatActivity {

    public ImageView imgRestaurant;
    public TextView nomeRestaurant;
    public TextView cittaRestaurant;

    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        cittaRestaurant=findViewById(R.id.citta_rest_act);
        imgRestaurant=findViewById(R.id.img_rest_act);
        nomeRestaurant=findViewById(R.id.nome_rest_act);
        ref= FirebaseDatabase.getInstance().getReference().child("Restaurant");

        String RestaurantKey = getIntent().getStringExtra("RestaurantKey");

        ref.child(RestaurantKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String nomeRes= snapshot.child("nome").getValue().toString();
                    String imgUrlRes= snapshot.child("img").getValue().toString();
                    String cittaRes= snapshot.child("citta").getValue().toString();

                    Picasso.get().load(imgUrlRes).into(imgRestaurant);
                    nomeRestaurant.setText(nomeRes);
                    cittaRestaurant.setText(cittaRes);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
