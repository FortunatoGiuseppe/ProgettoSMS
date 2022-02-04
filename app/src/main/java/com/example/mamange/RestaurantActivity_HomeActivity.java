package com.example.mamange;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;
//Giuseppe Diasparra
/*
Schermata per selezionare il ristorante
 */
public class RestaurantActivity_HomeActivity extends AppCompatActivity {

    EditText inputSearch;
    RecyclerView recyclerView;
    FirebaseRecyclerOptions<Restaurant> options;
    FirebaseRecyclerAdapter<Restaurant,ViewHolderRestaurant> adapter;
    DatabaseReference dataref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_home);

        dataref= FirebaseDatabase.getInstance().getReference().child("Restaurant");

        inputSearch=findViewById(R.id.barra_cerca_restaurant);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        
        loadData("");

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString()!=null){
                    loadData(editable.toString());
                }else{
                    loadData("");
                }
            }
        });
    }

    private void loadData(String data) {

        Query query= dataref.orderByChild("nome").startAt(data).endAt(data+"\uf8ff");

        options=new FirebaseRecyclerOptions.Builder<Restaurant>().setQuery(query,Restaurant.class).build();
        adapter=new FirebaseRecyclerAdapter<Restaurant, ViewHolderRestaurant>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolderRestaurant holder, @SuppressLint("RecyclerView") int position, @NonNull Restaurant model) {
                holder.textViewcitta.setText(model.citta);
                Picasso.get().load(model.img).into(holder.imageView);
                holder.textView.setText(model.nome);
                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent =new Intent(RestaurantActivity_HomeActivity.this,CreateQrCodeActivity.class);
                        intent.putExtra("RestaurantKey",getRef(position).getKey());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public ViewHolderRestaurant onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_restaurant,parent,false);
                return new ViewHolderRestaurant(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}
