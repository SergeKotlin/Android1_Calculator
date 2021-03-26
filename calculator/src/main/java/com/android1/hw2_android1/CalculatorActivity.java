package com.android1.hw2_android1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        ConstraintLayout calc_layout = findViewById(R.id.calculator_layout_id);
        calc_layout.setBackgroundResource(R.drawable.ic_bender_xml);
//        calc_layout.setBackgroundResource(R.drawable.ic_launcher_background);
    }
}