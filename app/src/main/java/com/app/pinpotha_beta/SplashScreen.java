package com.app.pinpotha_beta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        int SPLASH_SCREEN_TIME_OUT = 2000;
        new Handler().postDelayed(() -> {

            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
            finish();

        }, SPLASH_SCREEN_TIME_OUT);
    }
}