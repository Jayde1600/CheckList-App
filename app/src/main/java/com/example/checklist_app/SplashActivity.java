package com.example.checklist_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        ImageView imageView = findViewById(R.id.imageView);
        // Load the image resource
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.splash_copy);
        if (bitmap != null) {
            // Calculate the desired width and height
            int targetWidth = getResources().getDisplayMetrics().widthPixels;
            int targetHeight = getResources().getDisplayMetrics().heightPixels;

            // Resize the bitmap
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, true);

            // Set the resized bitmap as the background of the ImageView
            imageView.setImageBitmap(resizedBitmap);
        } else {
            // Handle the case where decoding the resource failed
            // You may want to log an error message or provide a fallback image
        }


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 5000);
    }
}
