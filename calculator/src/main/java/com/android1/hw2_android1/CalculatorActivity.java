package com.android1.hw2_android1;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android1.hw2_android1.business_logic.Calculations;
import com.android1.hw2_android1.business_logic.MySavedInstanceState;

public class CalculatorActivity extends AppCompatActivity {

    // Объявлю все свои штучки
    private TextView inputText, resultText, updateTextView;
    private final int[] numberButtonIds = new int[]{R.id.calc_btn_0, R.id.calc_btn_1,
            R.id.calc_btn_2, R.id.calc_btn_3, R.id.calc_btn_4, R.id.calc_btn_5, R.id.calc_btn_6,
            R.id.calc_btn_7, R.id.calc_btn_8, R.id.calc_btn_9};
    private Button btn_del, btn_plus, btn_minus, btn_increase, btn_division, btn_equals;
    private SwitchCompat btn_switcher_theme;
    private final Calculations calculations = new Calculations();
    private MySavedInstanceState savedMyInstanceState = new MySavedInstanceState();
    private final String operationValuesKey = "operationValuesKey", operationResultKey = "operationResultKey";
    private static final String nameSharedPreference = "CURRENT_THEME";
    private static final String calcTheme = "CALCULATOR_THEME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        savedMyInstanceState.setCurrent_theme(getTheme());

        ConstraintLayout calc_layout = findViewById(R.id.calculator_layout_id);
//        calc_layout.setBackgroundResource(R.drawable.ic_launcher_background);
//        calc_layout.setBackgroundResource(R.drawable.ic_bender_xml);
        LinearLayout calc_linear_layout = findViewById(R.id.calculator_linear_layout_id);
        calc_linear_layout.setBackgroundResource(R.drawable.ic_bender_xml);

        findViews();
        setIncreaseCounterBtnBehaviour();
        setNumberButtonListeners();
        themeListener();
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

        btn_switcher_theme = findViewById(R.id.calc_btn_switcher_theme);
    }

    private void setIncreaseCounterBtnBehaviour() {
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

    private void themeListener() {
//        if (btn_switcher_theme != null && savedMyInstanceState.getCurrent_theme() == getTheme()) {
        if (btn_switcher_theme != null) {
            btn_switcher_theme.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) (v, hasFocus) -> {
                if (btn_switcher_theme.isEnabled()) {
                    setTheme(R.style.My_Night_mode);
//                    SharedPreferences sharedPref =  getSharedPreferences(nameSharedPreference, MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPref.edit();
    //                    editor.putInt(calcTheme, R.style.My_Night_mode);
    //                    editor.apply();

                    /*finish();
                    setTheme(R.style.My_Night_mode);
                    startActivity(getIntent());*/

                    /*setTheme(R.style.My_Night_mode);
                    setContentView(R.layout.activity_calculator);*/
                }
            });
        }
    }
}

/* HW5
1. Создайте активити с настройками, где включите выбор темы приложения.
3. * Сделайте интент-фильтр для запуска калькулятора извне, а также напишите тестовое приложение, запускающее приложение-калькулятор.
(✓ 2. Доделайте приложение «Калькулятор». Это последний урок с созданием приложения «Калькулятор».)
**/ //Serega, sure