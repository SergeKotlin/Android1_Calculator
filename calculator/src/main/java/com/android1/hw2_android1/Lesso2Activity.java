package com.android1.hw2_android1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;

import android.os.Bundle;
import android.view.View;

public class Lesso2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_lesson2);
        setContentView(R.layout.activity_lesson2);
        /*ConstraintLayout layout = findViewById(R.id.rootLayout);
//        layout.setBackgroundColor();
        layout.setBackgroundResource(R.color.purple_200);*/
//here go uncomment        Group group = findViewById(R.id.group);
//        group.setVisibility(View.INVISIBLE); //
//here go uncomment        group.setVisibility(View.GONE); // не только прячет, но и вообще удаляет с экрана
        // В отличие от GONE, INVISIBLE прячет элемент но при этом оставляет его контейнер

    }
}