package com.android1.starterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StarterApp_Activity extends AppCompatActivity {

    private final static String TEXT = "PARAM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter_app);

        final EditText text = findViewById(R.id.editText);
        Button runEcho = findViewById(R.id.button);
        runEcho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("example://intent");
                Intent runEchoIntent = new Intent(Intent.ACTION_VIEW, uri);
                runEchoIntent.putExtra(TEXT, text.getText().toString());
                // startActivity(runEchoIntent); А если принимающая активити отсутствует? Критическая ситуация =)
                toStartCalledActivity(runEchoIntent);
            }
        });
    }

    private void toStartCalledActivity(Intent runEchoIntent) {
        ActivityInfo activityInfo = runEchoIntent.resolveActivityInfo(getPackageManager(),
                        runEchoIntent.getFlags());
        if (activityInfo != null) {
            startActivity(runEchoIntent);
        }
    }
}