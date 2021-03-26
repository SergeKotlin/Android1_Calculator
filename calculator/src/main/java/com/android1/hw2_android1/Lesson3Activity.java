package com.android1.hw2_android1;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android1.hw2_android1.business_logic.CounterInfo;

public class Lesson3Activity extends AppCompatActivity {

    private TextView counterTextView;
    private Button increaseCounterBtn;
    private CounterInfo counterInfo = new CounterInfo();
    private final String counterInfoKey = "counterInfoKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson3_activity);
        showMessage("onCreate");
        
        findViews();
        setIncreaseCounterBtnBehaviour(); // "Поведение на эту кнопку"
    }

    @Override
    protected void onStart() {
        super.onStart();
        showMessage("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showMessage("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        showMessage("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showMessage("onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showMessage("onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showMessage("onDestroy");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        outState.putSerializable(counterInfoKey, counterInfo); // это контейнер. Кладём сохраняемое (передаваемое) всякое =)
        outState.putParcelable(counterInfoKey, counterInfo); // это контейнер. Кладём сохраняемое (передаваемое) всякое =)
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        counterInfo = (CounterInfo)savedInstanceState.getSerializable(counterInfoKey);
        counterInfo = (CounterInfo)savedInstanceState.getParcelable(counterInfoKey);
        updateTextCounter();
    }

    private void findViews() {
        counterTextView = findViewById(R.id.counterTextView);
        increaseCounterBtn = findViewById(R.id.increaseCounterBtn);
    }

    private void setIncreaseCounterBtnBehaviour() {
        increaseCounterBtn.setOnClickListener(v -> {    // Совершенно для любой вьюхи
            counterInfo.increaseCounter();
            updateTextCounter();
        });

        // Для чекбоксов есть и такой слушатель:
        new CheckBox(getApplicationContext()).setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {

            }
        });
    }

    private void updateTextCounter() {
        String text = String.valueOf(counterInfo.getCounter());
        counterTextView.setText(text);
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT)
                .show();
        Log.i("Lifecycle", message);
    }
}