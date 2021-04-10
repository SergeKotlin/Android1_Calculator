package com.android1.hw2_android1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, Lesson5_Android1_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        new Handler(Looper.getMainLooper()).postDelayed(() ->
                startActivity(intent), 1000);
    }
}