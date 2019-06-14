package com.example.spydey.prototypeone;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
    private String uid, dateString;
    private Date date;
    private DateFormat dateFormat;
    private DatabaseReference databaseUserReference;
    private UserData userdata;
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


        auth = FirebaseAuth.getInstance();
        uid = auth.getUid();
        realdb = FirebaseDatabase.getInstance();
        databaseUserReference = realdb.getReference("UserData").child(uid);

        dateFormat = new SimpleDateFormat("yyyy-mm-dd_hh-mm-ss");
        date = new Date();
        dateString=dateFormat.format(date);

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

    public void onClickSubmitResult(View view) {
        userdata = new UserData(diabetesString, heartRateString, bloodPressureString, BMIString, ageString, probabilityString);
        databaseUserReference.child(dateString).setValue(userdata)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ResultActivity.this, "Data submitted Successfully.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
