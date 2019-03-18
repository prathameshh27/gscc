package com.example.spydey.prototypeone;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    public TextView diabetesTextView, heartRateTextView, bloodPressureTextView, BMITextView, ageTextView, probabilityTextView;
    public String diabetesString, heartRateString, bloodPressureString, BMIString, ageString, probabilityString;
    public AlertDialog.Builder alertDialog;
    public Intent intent, intentHome;

    @Override
    public void onBackPressed() {

        alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Exit Report")
                .setMessage("Are you sure you want to cancel the report?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        startActivity(intentHome);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.create().show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        intentHome=new Intent(this, HomeActivity.class);
        intent = getIntent();
        diabetesString=intent.getStringExtra("diabetes");
        heartRateString=String.valueOf(intent.getDoubleExtra("heartRate",0.0));
        bloodPressureString=String.valueOf(intent.getDoubleExtra("bloodPressure",0.0));
        BMIString=String.valueOf(intent.getDoubleExtra("bmi",0.0));
        ageString=String.valueOf(intent.getDoubleExtra("age",0.0));
        probabilityString=String.valueOf(intent.getDoubleExtra("probability",0.0));

        diabetesTextView = findViewById(R.id.diabetesResult);
        heartRateTextView = findViewById(R.id.heartRateResult);
        bloodPressureTextView = findViewById(R.id.bloodPressureResult);
        BMITextView = findViewById(R.id.BMIResult);
        ageTextView = findViewById(R.id.ageResult);
        probabilityTextView = findViewById(R.id.probabilityTextView);

        diabetesTextView.setText(diabetesString);
        heartRateTextView.setText(heartRateString);
        bloodPressureTextView.setText(bloodPressureString);
        BMITextView.setText(BMIString);
        ageTextView.setText(ageString);
        probabilityTextView.setText("You have "+probabilityString+"% chances of having a heart attack.");
    }
}
