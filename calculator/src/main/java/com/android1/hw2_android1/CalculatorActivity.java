package com.android1.hw2_android1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android1.hw2_android1.business_logic.Calculations;
import com.android1.hw2_android1.business_logic.MySavedInstanceState;

public class CalculatorActivity extends AppCompatActivity {

    // Объявлю все свои штучки
    private TextView inputText;
    private TextView resultText;
    private final int[] numberButtonIds = new int[]{R.id.calc_btn_0, R.id.calc_btn_1,
            R.id.calc_btn_2, R.id.calc_btn_3, R.id.calc_btn_4, R.id.calc_btn_5, R.id.calc_btn_6,
            R.id.calc_btn_7, R.id.calc_btn_8, R.id.calc_btn_9};
    private Button btn_del;
    private Button btn_plus;
    private Button btn_minus;
    private Button btn_increase;
    private Button btn_division;
    private Button btn_equals;
    private final Calculations calculations = new Calculations();
    private MySavedInstanceState savedOperationValues = new MySavedInstanceState();
    private final String operationValuesKey = "operationValuesKey";
    private final String operationResultKey = "operationResultKey";
    private TextView updateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        ConstraintLayout calc_layout = findViewById(R.id.calculator_layout_id);
//        calc_layout.setBackgroundResource(R.drawable.ic_launcher_background);
//        calc_layout.setBackgroundResource(R.drawable.ic_bender_xml);
        LinearLayout calc_linear_layout = findViewById(R.id.calculator_linear_layout_id);
        calc_linear_layout.setBackgroundResource(R.drawable.ic_bender_xml);

        findViews();
        setIncreaseCounterBtnBehaviour();
        setNumberButtonListeners();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(operationValuesKey, savedOperationValues);
        outState.putSerializable(operationResultKey, savedOperationValues);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedOperationValues = (MySavedInstanceState)savedInstanceState.getSerializable(operationValuesKey);
        savedOperationValues = (MySavedInstanceState)savedInstanceState.getSerializable(operationResultKey);
        updateValuesTextViews();
    }

    // For RestoreInstanceState
    private void updateValuesTextViews() {
        String textValue = String.valueOf(savedOperationValues.getValue());
        String textResult = String.valueOf(savedOperationValues.getResult());
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
            findViewById(numberButtonIds[i]).setOnClickListener(v ->
                    actionsWithNumbers(String.valueOf(index)));
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
        savedOperationValues.setValueForSavedInstanceState(text);
    }

    private void updateResultText(String calculations) {
        String text = String.valueOf(calculations);
        resultText.setText(text);
        savedOperationValues.setResultForSavedInstanceState(text);

    }
}

/* HW3
✓ 1. Напишите обработку каждой кнопки из макета калькулятора.
✓ 2. Создайте объект с данными и операциями калькулятора.
    Продумайте, каким образом будете хранить введённые пользователем данные.
(– элементарным. В рамках одной сессии. И без мудреных словарей. Сэнсэй сказал над этим пока
 не заморачиваться)
✓ 3. * Создайте макет калькулятора для горизонтальной ориентации экрана и отображайте
его в ландшафтной ориентации.
 **/