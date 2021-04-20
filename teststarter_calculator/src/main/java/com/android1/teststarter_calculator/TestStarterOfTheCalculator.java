package com.android1.teststarter_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class TestStarterOfTheCalculator extends AppCompatActivity {

    private MaterialButton start_calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_starter_of_calculator);

        findViews();
        setStarterCalcBtnBehaviour();
    }



    //TODO Чё за "@"неправильный лист приведения"? Избавиться от этого
    @SuppressLint("WrongViewCast")
    private void findViews() {
        start_calculator = findViewById(R.id.calc_btn_starter_to_calculator);
    }

    private void setStarterCalcBtnBehaviour() {
        start_calculator.setOnClickListener(v -> {
            Uri uri = Uri.parse("calculator://run");
            Intent runStartingIntent = new Intent(Intent.ACTION_VIEW, uri);
            // startActivity(runStartingIntent); А если принимающая активити отсутствует? Критическая ситуация =)
            toStartCalledActivity(runStartingIntent);
        });
    }

    private void toStartCalledActivity(Intent runStartingIntent) {
        // Метод для случая крит. ошибки, если принимающая активити отсутствует.
        ActivityInfo activityInfo = runStartingIntent.resolveActivityInfo(getPackageManager(),
                runStartingIntent.getFlags());
        if (activityInfo != null) {
            startActivity(runStartingIntent);
        }
    }
}