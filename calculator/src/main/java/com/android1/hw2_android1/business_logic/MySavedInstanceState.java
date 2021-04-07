package com.android1.hw2_android1.business_logic;
// Сохранение информации перед поворотом экрана через контейнер SaveInstanceState/onRestoreInstanceState

//    import android.os.Parcel; Альтернатива Serializable
//    import android.os.Parcelable;
// В Parcelable мы сами указываем поля, куда, что запихиваем, и получается быстрее -
// без Сериализации&Десериализации, без рефлексии. Но замороченнее.
// А мне заморочек не надо сейчас =)

    import java.io.Serializable;

public class MySavedInstanceState implements Serializable {
    private String value = "ввод";
    private String result = "результат";

    public String getValue() {
        return value;
    }

    public String getResult() {
        return result;
    }

    public void setValueForSavedInstanceState(String preRotationValue) {
        this.value = preRotationValue;
    }

    public void setResultForSavedInstanceState(String preRotationResult) {
        this.result = preRotationResult;
    }
}
