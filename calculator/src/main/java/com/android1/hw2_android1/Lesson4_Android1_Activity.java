package com.android1.hw2_android1;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.io.InputStream;

public class    Lesson4_Android1_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson4_activity);

        // Вывод ресурсов в строки программно
        initSomeTexts();
//        AppCompatImageView image = findViewById(R.id.imageView);
        ImageView image = findViewById(R.id.imageView);
        loadImageFromAsset(image, "android.png");
        
        initList();
    }

    private void initSomeTexts() {
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/19659.ttf");
        TextView descriptionLanguage = findViewById(R.id.textVLang);
        descriptionLanguage.setText(getString(R.string.descriptionLanguage));
        descriptionLanguage.setTypeface(tf);
        TextView textLanguage = findViewById(R.id.textLang);
        textLanguage.setText(getResources().getString(R.string.language));
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //...
        }
    }


    // #1 Вариант с спонтанной подгрузкой картинки из заранее-неопределенного места
    private void loadImageFromAsset(ImageView image, String fileName) {
        try {
            InputStream ims = getAssets().open(fileName);
            // загружаем как Drawable
            Drawable d = Drawable.createFromStream(ims, null);

            // #2 Простой вариант, с заранее положенной в Drawable картинкой
//            ContextCompat.getDrawable(getApplicationContext(), R.drawable.android);
            //

            // выводим картинку в ImageView
            image.setImageDrawable(d);
        }
        catch(IOException ex) {
            return;
        }
    }

    private void initList() {
        LinearLayout layoutList = findViewById(R.id.layoutList);
        String[] versions = getResources().getStringArray(R.array.version_names);

        // При помощи этого объекта будем надувать элементы, спрятанные в android_item.xml
        LayoutInflater ltInflater = getLayoutInflater();

        for (int i = 0; i < versions.length; i++){
            String version = versions[i];
            // Достаём элемент из android_item.xml
            // (пример программного создания view..)
            View item = ltInflater.inflate(R.layout.android_item, layoutList, false);
            // Находим в этом элементе TextView
            TextView tv = item.findViewById(R.id.textAndroid);
            tv.setText(version);

            // Получить из ресурсов массив указателей на изображения
            TypedArray imgs = getResources().obtainTypedArray(R.array.version_logos);

            // Выбрать по индексу подходящее изображение
            ImageView imgLogo = item.findViewById(R.id.imageAndroid);
            imgLogo.setImageResource(imgs.getResourceId(i, -1));

            layoutList.addView(item);
        }

    }
}
