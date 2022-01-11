package com.example.mamange;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanQrCodeActivity extends AppCompatActivity {

    Button buttonScan;
    TextView qrResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        buttonScan = findViewById(R.id.button_scan);
        qrResult = findViewById(R.id.qr_text);

        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(ScanQrCodeActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan Qr Code");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.setOrientationLocked(false);
                integrator.setCaptureActivity(OrientationCapture.class);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data ){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() == null){
                Toast.makeText(ScanQrCodeActivity.this, "Cancel scan", Toast.LENGTH_LONG).show();
            }
            else {
                String nn = result.getContents();
                Toast.makeText(ScanQrCodeActivity.this, "The result is "+nn, Toast.LENGTH_SHORT).show();
                qrResult.setText(nn);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}