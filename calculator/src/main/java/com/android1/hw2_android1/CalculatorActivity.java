package com.android1.hw2_android1;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android1.hw2_android1.business_logic.Calculations;
import com.android1.hw2_android1.business_logic.MySavedInstanceState;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.radiobutton.MaterialRadioButton;

public class CalculatorActivity extends AppCompatActivity {

    // Объявлю все свои штучки
    private TextView inputText, resultText, updateTextView;
    private final int[] numberButtonIds = new int[]{R.id.calc_btn_0, R.id.calc_btn_1,
            R.id.calc_btn_2, R.id.calc_btn_3, R.id.calc_btn_4, R.id.calc_btn_5, R.id.calc_btn_6,
            R.id.calc_btn_7, R.id.calc_btn_8, R.id.calc_btn_9};
    private MaterialButton btn_del, btn_plus, btn_minus, btn_increase, btn_division, btn_equals, btn_show_chooser_theme;
    private RadioGroup theme_chooser_radio_group;
    private final Calculations calculations = new Calculations();
    private MySavedInstanceState savedMyInstanceState = new MySavedInstanceState();
    private final String operationValuesKey = "operationValuesKey", operationResultKey = "operationResultKey";
    // Имя настроек
    private static final String NameSharedPreference = "CURRENT_THEME";
    // Имя параметра в настройках (т.е "ключ")
    private static final String calcTheme = "CALCULATOR_THEME";
    // (дефолтные значения)
    private static final int AppThemeLightStyle = 0;
    private static final int AppThemeNightmareStyle = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Устанавливать тему надо только до установки макета активити (вовремя .onCreate(...))
        setTheme(getAppTheme(R.style.ThemeHW2_Android1));
        setContentView(R.layout.activity_calculator);

        ConstraintLayout calc_layout = findViewById(R.id.calculator_layout_id);
        LinearLayout calc_linear_layout = findViewById(R.id.calculator_linear_layout_id);
        calc_linear_layout.setBackgroundResource(R.drawable.ic_bender_xml);

        findViews();
        setOperationsBtnBehaviour();
        setNumberButtonListeners();
        setShowChooserThemeBtnListener();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(operationValuesKey, savedMyInstanceState);
        outState.putSerializable(operationResultKey, savedMyInstanceState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedMyInstanceState = (MySavedInstanceState)savedInstanceState.getSerializable(operationValuesKey);
        savedMyInstanceState = (MySavedInstanceState)savedInstanceState.getSerializable(operationResultKey);
        updateValuesTextViews();
    }

    // For RestoreInstanceState
    private void updateValuesTextViews() {
        String textValue = String.valueOf(savedMyInstanceState.getValue());
        String textResult = String.valueOf(savedMyInstanceState.getResult());
        updateVariableText(textValue);
        updateResultText(textResult);
    }

    private void findViews() {
        inputText = findViewById(R.id.calc_inputTextView);
        resultText = findViewById(R.id.calc_resultText);

        btn_plus = findViewById(R.id.calc_btn_plus);
        btn_minus = findViewById(R.id.calc_btn_minus);
        btn_increase = findViewById(R.id.calc_btn_increase);
        btn_division = findViewById(R.id.calc_btn_division);
        btn_equals = findViewById(R.id.calc_btn_equals);
        btn_del = findViewById(R.id.calc_btn_delete);
        btn_show_chooser_theme = findViewById(R.id.calc_btn_show_chooser_theme);

        // Инициализация радио-кнопок очень похожа, поэтому создан метод для переиспользования
        chooserThemeListener(findViewById(R.id.calc_btn_AppThemeLightStyle),
                AppThemeLightStyle);
        chooserThemeListener(findViewById(R.id.calc_btn_AppThemeNightmareStyle),
                AppThemeNightmareStyle);
        theme_chooser_radio_group = findViewById(R.id.calc_btn_chooser_theme);
        //TODO ПОЧЕМУ ПАДАЕТ?? И ЧТО ЭТО ЗА КУСОК ТАКОЙ - ЕСЛИ БЕЗ НЕГО ВСЁ-РАВНО КАК НАДО РАБОТАЕТ! ДАН УЧИТЕЛЯМИ, КАК ЕСТЬ, С СЛОЖНЫМИ ВЫЗОВАМИ - БЕЗ ПОЯСНЕНИЙ.
//        ((MaterialRadioButton)theme_chooser_radio_group.getChildAt(getCodeStyle(AppThemeLightStyle))).setChecked(true);
    }

    private void setOperationsBtnBehaviour() {
        btn_del.setOnClickListener(v -> {
            actionsWithNumbers("d");
        });
        btn_plus.setOnClickListener(v -> {
            actionsWithOperator("+", false);
        });
        btn_minus.setOnClickListener(v -> {
            actionsWithOperator("-", false);
        });
        btn_increase.setOnClickListener(v -> {
            actionsWithOperator("*", false);
        });
        btn_division.setOnClickListener(v -> {
            actionsWithOperator("/", false);
        });
        btn_equals.setOnClickListener(v -> {
            actionsWithOperator("=", true);
            updateVariableText("");
        });
    }

    private void setNumberButtonListeners() {
        for (int i = 0; i < numberButtonIds.length; i++) {
            int index = i;
            findViewById(numberButtonIds[i]).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CalculatorActivity.this.actionsWithNumbers(String.valueOf(index));
                }
            });
        }
    }

    private void setShowChooserThemeBtnListener() {
        btn_show_chooser_theme.setOnClickListener(v -> {
            theme_chooser_radio_group.setVisibility(View.VISIBLE);
        });
    }

    private void actionsWithNumbers(String s) {
        calculations.setValueToCalculate(s);
        updateVariableText(calculations.getCalculations(false));
    }

    private void actionsWithOperator(String s, boolean b) {
        calculations.calculate(s);
        updateResultText(calculations.getCalculations(b));
    }

    private void updateVariableText(String calculations) {
        String text = String.valueOf(calculations);
        inputText.setText(text);
        savedMyInstanceState.setValueForSavedInstanceState(text);
    }

    private void updateResultText(String calculations) {
        String text = String.valueOf(calculations);
        resultText.setText(text);
        savedMyInstanceState.setResultForSavedInstanceState(text);

    }

    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    private int codeStyleToStyleId(int codeStyle){
        switch(codeStyle){
            case AppThemeNightmareStyle:
                return R.style.My_Night_mode;
            case AppThemeLightStyle:
            default:
                return R.style.ThemeHW2_Android1;
        }
    }

    private void chooserThemeListener(MaterialRadioButton button, final int codeStyle){
        button.setOnClickListener(v -> {
            // сохраним настройки
            setAppTheme(codeStyle);
            // пересоздадим активити, чтобы тема применилась
            recreate();
            // прятать меню выбора темы нет необходимости, после recreate() - оно сбрасывается само собой
                // theme_chooser_radio_group.setVisibility(View.INVISIBLE);
        });
    }

    // Сохранение настроек
    private void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        // Настройки сохраняются посредством специального класса editor.
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(calcTheme, codeStyle);
        //Немедленное сохранение - commit(), отложенное - apply()
        editor.apply();
    }

    // Чтение настроек, параметр «тема»
    private int getCodeStyle(int codeStyle){
        // Работаем через специальный класс сохранения и чтения настроек
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        //Прочитать тему, если настройка не найдена - взять по умолчанию
        return sharedPref.getInt(calcTheme, codeStyle);
    }

    /*private void themeListener() {
//        if (btn_switcher_theme != null && savedMyInstanceState.getCurrent_theme() == getTheme()) {
        if (btn_switcher_theme != null) {
            btn_switcher_theme.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) (v, hasFocus) -> {
                if (btn_switcher_theme.isEnabled()) {
                    setTheme(R.style.My_Night_mode);
//                    SharedPreferences sharedPref =  getSharedPreferences(nameSharedPreference, MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPref.edit();
    //                    editor.putInt(calcTheme, R.style.My_Night_mode);
    //                    editor.apply();

                    *//*finish();
                    setTheme(R.style.My_Night_mode);
                    startActivity(getIntent());*//*

                    *//*setTheme(R.style.My_Night_mode);
                    setContentView(R.layout.activity_calculator);*//*
                }
            });
        }
    }*/
}

/* HW5
✓ 1. Создайте активити с настройками, где включите выбор темы приложения.
3. * Сделайте интент-фильтр для запуска калькулятора извне, а также напишите тестовое приложение, запускающее приложение-калькулятор.
(2. Доделайте приложение «Калькулятор». Это последний урок с созданием приложения «Калькулятор».)
**/ //Serega, sure