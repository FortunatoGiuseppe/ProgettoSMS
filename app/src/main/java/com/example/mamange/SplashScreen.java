package com.example.mamange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                //Intent i = new Intent(SplashScreen.this, MainActivity.class); startActivity(i);
                Intent i = new Intent(SplashScreen.this, LoginActivity.class); startActivity(i);
                finish(); }}, 2000);
            /*
                Intent intent = new Intent(SplashScreen.this,LoginActivity.class);
                startActivity(intent);*/
    }



}