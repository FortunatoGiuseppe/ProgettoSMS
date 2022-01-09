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
public class RestaurantActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseRecyclerOptions<Category> options_cat;
    FirebaseRecyclerAdapter<Category,ViewHolderCategory> adapter_cat;
    DatabaseReference dataref2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        dataref2 =  FirebaseDatabase.getInstance().getReference().child("Categories");

        recyclerView=findViewById(R.id.recyclerView_categorie);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        loadData("");

    }

    private void loadData(String data) {


        Query query2= dataref2.orderByChild("nome").startAt(data).endAt(data+"\uf8ff");

        options_cat=new FirebaseRecyclerOptions.Builder<Category>().setQuery(query2,Category.class).build();
        adapter_cat=new FirebaseRecyclerAdapter<Category, ViewHolderCategory>(options_cat) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolderCategory holder, @SuppressLint("RecyclerView") int position, @NonNull Category model) {
                holder.textViewcategory.setText(model.nome);
            }

            @NonNull
            @Override
            public ViewHolderCategory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v_cat= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_category_plate,parent,false);
                return new ViewHolderCategory(v_cat);
            }
        };


        adapter_cat.startListening();
        recyclerView.setAdapter(adapter_cat);

    }
}
