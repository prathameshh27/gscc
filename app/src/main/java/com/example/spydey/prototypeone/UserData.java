package com.example.spydey.prototypeone;

import android.util.Log;

public class UserData {
    protected String diabetes, heartrate, bloodpressure, bmi, age, probability, meditation, attention;

    public UserData() {
        this.diabetes = " ";
        this.heartrate = " ";
        this.bloodpressure = " ";
        this.bmi = " ";
        this.age = " ";
        this.probability = " ";
        this.meditation = " ";
        this.attention = " ";
        Log.i("customLog", "UserData -> Default Constructor: executed");
    }

    public UserData(String diabetes, String heartrate, String bloodpressure, String bmi, String age, String probability, String meditation, String attention) {
        this.diabetes = diabetes;
        this.heartrate = heartrate;
        this.bloodpressure = bloodpressure;
        this.bmi = bmi;
        this.age = age;
        this.probability = probability;
        this.meditation = meditation;
        this.attention = attention;

        Log.i("customLog", "UserData -> Parametric Constructor: "+diabetes+" "+heartrate+" "+bloodpressure+" "+bmi+" "+age+" "+probability);
    }

    public String getDiabetes() {
        return diabetes;
    }
    public String getHeartrate() {
        return heartrate;
    }
    public String getBloodpressure() {
        return bloodpressure;
    }
    public String getBmi() {
        return bmi;
    }
    public String getAge() {
        return age;
    }
    public String getProbability() {
        return probability;
    }
}
