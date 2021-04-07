package com.android1.hw2_android1.business_logic;

import java.text.NumberFormat;

public class Calculations {

    private Double currentResult = 0.0d;
    private String valueToCalculate = "";
    private String currentCmd = "";

    public String getCalculations(boolean isResult) {
        if (!isResult) {
            return valueToCalculate;
        } else {
            return makeResultString();
        }
    }

    private String makeResultString() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(3);
        numberFormat.setGroupingUsed(false);
        return numberFormat.format(currentResult);
    }

    public void setValueToCalculate(String value) {
        if (value.equals("d")) {
            this.valueToCalculate = (valueToCalculate.length() == 0) ? "" : (valueToCalculate.substring(0, valueToCalculate.length() - 1));
        } else {
            this.valueToCalculate += value;
        }
    }

//    TODO #1Падает при выполнении операторов с пустым значением строки
//    TODO #2Бесконечно выходит за экран. Например, офисные калькуляторы бывают 12 и 14 разрядные
    public Double calculate(String calculateCmd) {
        if (!calculateCmd.equals("=")) {
            currentCmd = calculateCmd;
            if (!valueToCalculate.equals("")) {
                currentResult = Double.parseDouble(valueToCalculate);
            } else {
                currentResult = 0.0d;
            }
            resetCalculateValuesBuffer(false);
        } else {
            if (currentCmd.equals("+")) {
                currentResult += Double.parseDouble(valueToCalculate);
                resetCalculateValuesBuffer();
            }
            if (currentCmd.equals("-")) {
                currentResult -= Double.parseDouble(valueToCalculate);
                resetCalculateValuesBuffer();
            }
            if (currentCmd.equals("*")) {
                currentResult *= Double.parseDouble(valueToCalculate);
                resetCalculateValuesBuffer();
            }
            if (currentCmd.equals("/")) {
                currentResult /= Double.parseDouble(valueToCalculate);
                resetCalculateValuesBuffer();
            }
        }
        return currentResult;
    }

    private void resetCalculateValuesBuffer(boolean isAll) {
        if (isAll) {
            currentCmd = "";
        }
        valueToCalculate = "";
    }

    private void resetCalculateValuesBuffer() {
        resetCalculateValuesBuffer(true);
    }


}
