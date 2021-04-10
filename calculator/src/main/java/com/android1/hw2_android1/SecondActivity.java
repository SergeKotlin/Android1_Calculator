package com.android1.hw2_android1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;
    private EditText loginEditText, passwordEditText;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initViews();
        setData();
        setOnRegisterBtnClick();
    }

    private void initViews() {
        textView = findViewById(R.id.textView2ndActivity);
        loginEditText = findViewById(R.id.loginEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerBtn = findViewById(R.id.registerBtn);
    }

    private void setData() {
        Intent intent = getIntent();
        String text = intent.getStringExtra(Constants.mainActivityDataKey);
        textView.setText(text);
    }

    private void setOnRegisterBtnClick() {
        registerBtn.setOnClickListener(v -> {
            String loginText = loginEditText.getText().toString();
            String passwordText = passwordEditText.getText().toString();

            Intent intent = new Intent();
            LoginData data= new LoginData();
            data.setLogin(loginText);
            data.setPassword(passwordText);

            intent.putExtra(Constants.secondActivityDataKey, data);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}