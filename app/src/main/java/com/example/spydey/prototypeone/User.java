package com.example.spydey.prototypeone;

import android.util.Log;

/**
 * Created by Belal on 4/15/2018.
 */

public class User {
    public String firstName, lastName, email, phone, birth, gender;

    public User() {}

    public User(String fName, String lName, String email, String phone, String birth, String gender) {
        Log.i("customLog", "User -> Parametric Constructor: executed");
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
        this.phone = phone;
        this.birth = birth;
        this.gender = gender;
    }
}
