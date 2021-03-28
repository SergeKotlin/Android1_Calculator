package com.android1.hw2_android1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android1.hw2_android1.business_logic.Calculations;

public class CalculatorActivity extends AppCompatActivity {

    // Объявлю все свои штучки
    private TextView inputText;
    private TextView resultText;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_0;
    private Button btn_del;
    private Button btn_plus;
    private Button btn_minus;
    private Button btn_equals;
    private final Calculations calculations = new Calculations();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        ConstraintLayout calc_layout = findViewById(R.id.calculator_layout_id);
        calc_layout.setBackgroundResource(R.drawable.ic_bender_xml);
//        calc_layout.setBackgroundResource(R.drawable.ic_launcher_background);

        findViews();
        setIncreaseCounterBtnBehaviour();
    }

// Label for next update
//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        outState.putSerializable(btn_1, counterInfo);
//        super.onSaveInstanceState(outState);
//    }
// End Label.

    private void findViews() {
        inputText = findViewById(R.id.calc_inputTextView);
        resultText = findViewById(R.id.calc_resultText);
        btn_1 = findViewById(R.id.calc_btn_1);
        btn_2 = findViewById(R.id.calc_btn_2);
        btn_3 = findViewById(R.id.calc_btn_3);
        btn_4 = findViewById(R.id.calc_btn_4);
        btn_5 = findViewById(R.id.calc_btn_5);
        btn_6 = findViewById(R.id.calc_btn_6);
        btn_7 = findViewById(R.id.calc_btn_7);
        btn_8 = findViewById(R.id.calc_btn_8);
        btn_9 = findViewById(R.id.calc_btn_9);
        btn_0 = findViewById(R.id.calc_btn_0);

        btn_plus = findViewById(R.id.calc_btn_plus);
        btn_minus = findViewById(R.id.calc_btn_minus);
        btn_equals = findViewById(R.id.calc_btn_equals);
        btn_del = findViewById(R.id.calc_btn_delete);
    }

    private void setIncreaseCounterBtnBehaviour() {
        btn_1.setOnClickListener(v -> {
            actionsWithNumbers("1");
        });
        btn_2.setOnClickListener(v -> {
            actionsWithNumbers("2");
        });
        btn_3.setOnClickListener(v -> {
            actionsWithNumbers("3");
        });
        btn_4.setOnClickListener(v -> {
            actionsWithNumbers("4");
        });
        btn_5.setOnClickListener(v -> {
            actionsWithNumbers("5");
        });
        btn_6.setOnClickListener(v -> {
            actionsWithNumbers("6");
        });
        btn_7.setOnClickListener(v -> {
            actionsWithNumbers("7");
        });
        btn_8.setOnClickListener(v -> {
            actionsWithNumbers("8");
        });
        btn_9.setOnClickListener(v -> {
            actionsWithNumbers("9");
        });
        btn_0.setOnClickListener(v -> {
            actionsWithNumbers("0");
        });
        btn_del.setOnClickListener(v -> {
            actionsWithNumbers("d");
        });
        btn_plus.setOnClickListener(v -> {
            actionsWithOperator("+", false);
        });
        btn_minus.setOnClickListener(v -> {
            actionsWithOperator("-", false);
        });
        btn_equals.setOnClickListener(v -> {
            actionsWithOperator("=", true);
            updateVariableText("");
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
    }

    private void updateResultText(String calculations) {
        String text = String.valueOf(calculations);
        resultText.setText(text);
    }
}

/* HW3
✓ 1. Напишите обработку каждой кнопки из макета калькулятора.
✓ 2. Создайте объект с данными и операциями калькулятора.
    Продумайте, каким образом будете хранить введённые пользователем данные.
(– элементарным. В рамках одной сессии. И без мудреных словарей. Сэнсэй сказал над этим пока
 не заморачиваться)
3. * Создайте макет калькулятора для горизонтальной ориентации экрана и отображайте
его в ландшафтной ориентации.
 **/