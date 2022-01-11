package com.example.mamange;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.EditText;

import android.os.Bundle;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Random;

public class CreateQrCodeActivity extends AppCompatActivity {

    ImageView imageQR;
    final String alphabet = "0123456789ABCDE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_qr);

        imageQR = findViewById(R.id.image_qr);

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
}


