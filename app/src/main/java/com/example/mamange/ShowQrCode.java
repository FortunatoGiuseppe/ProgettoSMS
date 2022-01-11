package com.example.mamange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import android.os.Bundle;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Random;

public class ShowQrCode extends AppCompatActivity {

    ImageView imageQR;
    Button closeQrCode;


    final String alphabet = "0123456789ABCDE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_qr);
        imageQR = findViewById(R.id.image_qr2);
        closeQrCode = findViewById(R.id.closeQr);

        closeQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeQrCode();
            }
        });

        String StringaRandom = CreateStringRandom();
        if (!StringaRandom.isEmpty()){
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(StringaRandom, BarcodeFormat.QR_CODE,300,300);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                imageQR.setImageBitmap(bitmap);
            }catch(Exception e){e.printStackTrace();}
        }
    }

    private String CreateStringRandom(){
        Random r = new Random();
        String stringRandom = null;

        for (int i = 0; i < 8; i++) {
            stringRandom += alphabet.charAt(r.nextInt(alphabet.length()));
        }
        return stringRandom;
    }

    private void closeQrCode(){
        startActivity(new Intent(ShowQrCode.this,CreateQrCodeActivity.class));
    }
}


