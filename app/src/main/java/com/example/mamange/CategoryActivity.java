package com.example.mamange;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
/*
Giuseppe Diasparra
Schermata dei piatti una volta selezionata la categoria in RestaurantActivity
 */

public class CategoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseRecyclerOptions<Plate> options_plates;
    FirebaseRecyclerAdapter<Plate, ViewHolderPlate> adapter_plates;
    DatabaseReference dataref_plates;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plates_of_category);

        dataref_plates = FirebaseDatabase.getInstance().getReference().child("Categories").child("NIGIRI"); //.child(nigiri)
        // non funziona la lettura dal DB dei piatti una vota scelta la categoria

        recyclerView = findViewById(R.id.recyclerView_piatti);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        loadData("");

    }

    private void loadData(String data) {


        Query query_Plate = dataref_plates.orderByChild("nome").startAt(data).endAt(data + "\uf8ff");

        options_plates = new FirebaseRecyclerOptions.Builder<Plate>().setQuery(query_Plate, Plate.class).build();
        adapter_plates = new FirebaseRecyclerAdapter<Plate,ViewHolderPlate>(options_plates) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolderPlate holder, @SuppressLint("RecyclerView") int position, @NonNull Plate model) {
                holder.textViewPlate.setText(model.nome);
                Picasso.get().load(model.img).into(holder.imageView);
               /* holder.v_plate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent =new Intent(CategoryActivity.this,PlateActivity.class);
                        intent.putExtra("PlateKey",getRef(position).getKey());
                        startActivity(intent);
                    }
                });*/
            }

            @NonNull
            @Override
            public ViewHolderPlate onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v_plate = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_plate, parent, false);
                return new ViewHolderPlate(v_plate);
            }
        };

        adapter_plates.startListening();
        recyclerView.setAdapter(adapter_plates);
    }
}