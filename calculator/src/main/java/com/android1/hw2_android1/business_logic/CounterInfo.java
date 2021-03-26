package com.android1.hw2_android1.business_logic;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable; // Чтобы могли сохранить counter перед поворотом экрана

//public class CounterInfo implements Serializable {
// В Parcelable мы сами указываем поля, куда, что запихиваем, и получается быстрее - без Сериализации&Десериализации, без рефлексии
public class CounterInfo implements Parcelable {
//    private int counter = 0;
    private int counter;

    public CounterInfo() {
        counter = 0;
    };

    public int getCounter() {
        return counter;
    }

    public void increaseCounter() {
        counter++;
    }

    protected CounterInfo(Parcel in) {
        counter = in.readInt();
    }

    public static final Creator<CounterInfo> CREATOR = new Creator<CounterInfo>() {
        @Override
        public CounterInfo createFromParcel(Parcel in) {
            return new CounterInfo(in);
        }

        @Override
        public CounterInfo[] newArray(int size) {
            return new CounterInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(counter);
    }

}
