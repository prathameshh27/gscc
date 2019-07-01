package com.example.spydey.prototypeone;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseDatabase realdb;
    private DatabaseReference databaseUserReference;
    private Date date;
    private DateFormat dateFormat;
    private UserData userdata;
    public AlertDialog.Builder alertDialog;
    public Intent intent, intentHome;
    private String uid, dateString;
    public String diabetesString, heartRateString, bloodPressureString, BMIString,
                ageString, stressString, attentionString, meditationString, probabilityString;
    public TextView diabetesTextView, heartRateTextView, bloodPressureTextView, BMITextView,
                ageTextView, stressTextView, attentionTextView, meditationTextView, probabilityTextView;

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


        auth = FirebaseAuth.getInstance();
        uid = auth.getUid();
        realdb = FirebaseDatabase.getInstance();
        databaseUserReference = realdb.getReference("UserData").child(uid);

        dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
        date = new Date();
        dateString=dateFormat.format(date);
        Log.i("customLog", "onCreate: "+dateString);

        //opens the Home activity on back button press with the help of Alert Dialog
        intentHome=new Intent(this, HomeActivity.class);

        //get intent from Analyse Fragment
        intent = getIntent();

        diabetesString = intent.getStringExtra("diabetes");
        stressString = intent.getStringExtra("stress");
        probabilityString = String.valueOf(intent.getDoubleExtra("probability",0.0));

        //Numerical value
//        heartRateString = String.valueOf(intent.getDoubleExtra("heartRate",0.0));
//        bloodPressureString = String.valueOf(intent.getDoubleExtra("bloodPressure",0.0));
//        BMIString = String.valueOf(intent.getDoubleExtra("bmi",0.0));
//        ageString = String.valueOf(intent.getDoubleExtra("age",0.0));
//        meditationString = String.valueOf(intent.getDoubleExtra("meditation",0.0));;
//        attentionString = String.valueOf(intent.getDoubleExtra("attention",0.0));

        //Decision value
        heartRateString = intent.getStringExtra("heartRateString");
        bloodPressureString = intent.getStringExtra("bloodPressureString");
        BMIString = intent.getStringExtra("bmiString");
        ageString = intent.getStringExtra("ageString");
        meditationString = intent.getStringExtra("meditationString");
        attentionString = intent.getStringExtra("attentionString");

        //getContext of all the textviews
        diabetesTextView = findViewById(R.id.diabetesResult);
        heartRateTextView = findViewById(R.id.heartRateResult);
        bloodPressureTextView = findViewById(R.id.bloodPressureResult);
        BMITextView = findViewById(R.id.BMIResult);
        ageTextView = findViewById(R.id.ageResult);
        stressTextView = findViewById(R.id.stressResult);
        //attentionTextView = findViewById(R.id.attentionResult);
        //meditationTextView = findViewById(R.id.meditationResult);
        probabilityTextView = findViewById(R.id.probabilityTextView);

        //set text into the textview
        diabetesTextView.setText(diabetesString);
        heartRateTextView.setText(heartRateString);
        bloodPressureTextView.setText(bloodPressureString);
        BMITextView.setText(BMIString);
        ageTextView.setText(ageString);
        stressTextView.setText(stressString);
        //attentionTextView.setText(attentionString);
        //meditationTextView.setText(meditationString);
        probabilityTextView.setText("You have "+probabilityString+"% chances of having a heart attack.");
    }

    //submit button
    public void onClickSubmitResult(View view) {
        userdata = new UserData(diabetesString, heartRateString, bloodPressureString, BMIString,
                ageString, probabilityString, stressString, meditationString, attentionString);
        databaseUserReference.child(dateString).setValue(userdata)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ResultActivity.this, "Data submitted Successfully.", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(intentHome);
                    }
                });
    }
}
