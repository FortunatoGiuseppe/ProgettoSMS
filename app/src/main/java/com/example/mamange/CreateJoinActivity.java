package com.example.mamange;

import android.os.Bundle;
import android.view.LayoutInflater;
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
        return root;
    }
}



