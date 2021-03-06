package com.example.spydey.prototypeone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class RecordDataActivity extends AppCompatActivity {

    public TextView diabetesTextView, heartRateTextView, bloodPressureTextView, BMITextView,
            ageTextView, stressTextView, attentionTextView, meditationTextView, probabilityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_data);

        Log.i("customLog", "RecordDataActivity -> onCreate(): exectuted");

        //Intent from Record Fragment
        Intent intent = getIntent();
        String modelStringArray[] = intent.getStringArrayExtra("modelStringArray");

        Log.i("customLog", "RecordDataActivity -> onCreate(): IntendData");
        for (String data:modelStringArray) { Log.i("customLog", "modelStringArray[]: "+data); }

        diabetesTextView = findViewById(R.id.diabetesRecordData);
        heartRateTextView = findViewById(R.id.heartRateRecordData);
        bloodPressureTextView = findViewById(R.id.bloodPressureRecordData);
        BMITextView = findViewById(R.id.BMIRecordData);
        ageTextView = findViewById(R.id.ageRecordData);
        stressTextView = findViewById(R.id.stressRecordData);
        //attentionTextView = findViewById(R.id.attentionRecordData);
        //meditationTextView = findViewById(R.id.meditationRecordData);
        probabilityTextView = findViewById(R.id.probabilityRecordData);

        diabetesTextView.setText(modelStringArray[0]);
        heartRateTextView.setText(modelStringArray[1]);
        bloodPressureTextView.setText(modelStringArray[2]);
        BMITextView.setText(modelStringArray[3]);
        ageTextView.setText(modelStringArray[4]);
        stressTextView.setText(modelStringArray[5]);
        //attentionTextView.setText(modelStringArray[6]);
        //meditationTextView.setText(modelStringArray[7]);
        probabilityTextView.setText(modelStringArray[8]+"% chances of having a heart attack.");
    }
}
