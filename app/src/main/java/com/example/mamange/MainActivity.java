package com.example.mamange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button logout;
    FirebaseAuth mAuth;
    TextView username;
    Button crea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        caricaUtente();
        crea= findViewById(R.id.creaGroupOrder2);
        crea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RestaurantActivity_HomeActivity.class));
            }
        });
    }

    public void logout(){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
        finish();
    }
    public void caricaUtente(){
        if(mAuth.getCurrentUser() != null){
            username = findViewById(R.id.usernameLogin);
            String user = mAuth.getCurrentUser().getEmail();
            String[] split = user.split("@");
            username.setText(split[0]);
            username.setKeyListener(null);

        }
    }

}