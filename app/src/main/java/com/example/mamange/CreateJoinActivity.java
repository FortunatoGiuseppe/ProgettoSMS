package com.example.mamange;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public  class CreateJoinActivity extends Fragment {
    TextView username;
    Button createGroupOrder;
    Button joinGroupOrder ;
    //private FirebaseAuth mAuth;

    public ViewGroup onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_createjoin_group, container, false);

        username = root.findViewById(R.id.username);
        createGroupOrder = root.findViewById(R.id.creaGroupOrder);
        joinGroupOrder = root.findViewById(R.id.uniscitiGroupOrder);

        createGroupOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),RestaurantActivity_HomeActivity.class));
            }
        });
        return root;
    }
}



