package com.android1.calledapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CalledApp_Activity extends AppCompatActivity {

    private final static String TEXT = "PARAM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_called_app);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle == null){    // Если никакие параметры не были переданы
            return;
        }
        String text  = bundle.getString(TEXT); // получить данные из Intent
        TextView textView = findViewById(R.id.textEcho);
        textView.setText(text); // Сохранить их в TextView
    }
}