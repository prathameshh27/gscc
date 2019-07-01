package com.example.spydey.prototypeone.Model;
import android.util.Log;
import com.example.spydey.prototypeone.UserData;

public class RecordItem extends UserData
{
    public String recordDate;

    public RecordItem()
    {
        recordDate="Record Name";
        //Log.i("customLog", "RecordItem: child Executed");
    }

    public RecordItem(String diabetes, String heartrate, String bloodpressure, String bmi,
                      String age, String probability, String stress, String meditation, String attention) {
        super(diabetes, heartrate, bloodpressure, bmi, age, probability, stress, meditation, attention);

        Log.i("customLog", "RecordItem -> Parametric Constructor: "+diabetes+" "
                +heartrate+" "+bloodpressure+" "+bmi+" "+age+" "+probability+" "+meditation+" "+attention);
    }
}
