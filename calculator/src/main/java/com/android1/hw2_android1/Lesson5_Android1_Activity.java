package com.android1.hw2_android1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Lesson5_Android1_Activity extends AppCompatActivity {

    private Button openSecondActivityBtn, increaseCounterBtn, openUrlBtn;
    private TextView counterTextView;
    private EditText dataEditText, urlEditText;
    private TextView resultTextView;

    private int secondActivityRequestCode = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson5_activity);
        initViews();
        setOnOpenActivityBtnClick();
        setOnIncreaseCntBtnClick();
        setOnOpenUrlBtnClick();
    }

    private void initViews() {
        openSecondActivityBtn = findViewById(R.id.openSecondActivityBtn);
        increaseCounterBtn = findViewById(R.id.increaseBtn);
        openUrlBtn = findViewById(R.id.openUrlBtn);
        counterTextView = findViewById(R.id.textViewCounter);
        dataEditText = findViewById(R.id.editText);
        resultTextView = findViewById(R.id.resultTextView);
        urlEditText = findViewById(R.id.urlEditText);
    }

    private void setOnOpenActivityBtnClick() {
        openSecondActivityBtn.setOnClickListener(v -> {
            String text = dataEditText.getText().toString();
            Intent intent = new Intent(Lesson5_Android1_Activity.this, SecondActivity.class);
            intent.putExtra(Constants.mainActivityDataKey, text);
//            startActivity(intent);
            startActivityForResult(intent, secondActivityRequestCode);
        });
    }

    private void setOnIncreaseCntBtnClick() {
        increaseCounterBtn.setOnClickListener(v -> {
            int count = Integer.parseInt(counterTextView.getText().toString());
            String newText = String.valueOf(++count);
            counterTextView.setText(newText);
        });
    }


    // Метод, вызываемый при возврате Активити
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == secondActivityRequestCode && resultCode == RESULT_OK && data != null) {
            LoginData loginData = (LoginData)data.getSerializableExtra(Constants.secondActivityDataKey);
            String text = loginData.getLogin() + ", " + loginData.getPassword();
            resultTextView.setText(text);
        }
    }

    private void setOnOpenUrlBtnClick() {
        openUrlBtn.setOnClickListener(v -> {
            String urlStr = urlEditText.getText().toString();
            // URI - уникальный идентификатор всего в Андроиде. Будь то внутренний файл или ссылка на браузер
            Uri uri = Uri.parse(urlStr);
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browserIntent);
        });
    }
}