package com.example.mamange;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class CreateQrCodeActivity extends AppCompatActivity {
    TextView username;
    FirebaseAuth mAuth;
    ImageButton qrCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_qr);
        mAuth = FirebaseAuth.getInstance();
        qrCode = findViewById(R.id.qrCodeButton);
        caricaUtente();
        qrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateQrCodeActivity.this,ShowQrCode.class));
            }
        });
    }

    public void caricaUtente(){
        if(mAuth.getCurrentUser() != null){
            username = findViewById(R.id.username3);
            String user = mAuth.getCurrentUser().getEmail();
            String[] split = user.split("@");
            username.setText(split[0]);
            username.setKeyListener(null);
        }
    }
}


