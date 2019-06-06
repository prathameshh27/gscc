package com.example.spydey.prototypeone;

public class UserData {
    public String diabetes, heartrate, bloodpressure, bmi, age, probability;

    public UserData() {
        this.diabetes = " ";
        this.heartrate = " ";
        this.bloodpressure = " ";
        this.bmi = " ";
        this.age = " ";
        this.probability = " ";
    }

    public UserData(String diabetes, String heartrate, String bloodpressure, String bmi, String age, String probability) {
        this.diabetes = diabetes;
        this.heartrate = heartrate;
        this.bloodpressure = bloodpressure;
        this.bmi = bmi;
        this.age = age;
        this.probability = probability;
    }
}
