package com.example.spydey.prototypeone;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnalyseFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private EditText heartRateEditText, bloodPressureEditText, heightEditText, weightEditText,
            ageEditText, attentionEditText, meditationEditText, cholestrolEditText;
    private String dibeticString, heartRateString, bloodPressureString, heightString, weightString,
            bmiString, ageString, stressString, attentionString, meditationString, cholestrolString, authUid;
    private Double heartRateVal, bloodPressureVal, heightVal, weightVal, bmiVal,
            ageVal, attentionVal, meditationVal, cholestrolVal, probabilityVal;
    private DecimalFormat decimalFormat;
    private FirebaseAuth auth;
    private Button anlayseButton;
    private View view;
    private Spinner dibeticSpinner;
    private FirebaseDatabase realdb;
    private DatabaseReference dbRef;
    public Clusters cluster;

    public AnalyseFragment() {
        // Required empty public constructor
        Log.i("customLog","AnalyseFragment -> Default constructor: executed");
        heartRateVal=bloodPressureVal=heightVal=weightVal=bmiVal=ageVal=probabilityVal=0.0;

        //TODO: to be deleted
        probabilityVal=Math.random()*20+10;
    }

    Boolean validateData(){
        Log.i("customLog", "AnalyseFragment -> validateData: executed");
        decimalFormat = new DecimalFormat("#.00");

        heartRateString=heartRateEditText.getText().toString();
        bloodPressureString=bloodPressureEditText.getText().toString();
        ageString=ageEditText.getText().toString();
        heightString=heightEditText.getText().toString(); 
        weightString=weightEditText.getText().toString();
        attentionString = attentionEditText.getText().toString();
        meditationString = meditationEditText.getText().toString();
        
        if(!heartRateString.isEmpty() && !bloodPressureString.isEmpty()
                && !heightString.isEmpty() && !weightString.isEmpty() &&
                !ageString.isEmpty() && !attentionString.isEmpty() && !meditationString.isEmpty())
        {
            heartRateVal=Double.valueOf(heartRateString);
            bloodPressureVal=Double.valueOf(bloodPressureString);
            ageVal=Double.valueOf(ageString);
            heightVal=Double.valueOf(heightString);
            weightVal=Double.valueOf(weightString);
            bmiVal=Double.valueOf(decimalFormat.format(weightVal/(heightVal*heightVal)));
            attentionVal=Double.valueOf(attentionString);
            meditationVal=Double.valueOf(meditationString);

            Log.i("customLog", heartRateVal+" "+bloodPressureVal+" "+heightVal+" "
                    +weightVal+" " +ageVal+" "+attentionVal+" "+meditationVal);

            /////////////////////////////////////////////////
            determineHeartRate();
            determineBloodPressure();
            determineBMI();
            determineAge();
            determineStress();

            return true;
        }
        else { return false; }
    }

    void determineHeartRate(){
        Log.i("customLog", "AnalyseFragment -> determineHeartRate(): executed");
        if(heartRateVal>=0 && heartRateVal<60) {heartRateString="Low";
            probabilityVal=(Math.random()*20)+10;
        }

        if(heartRateVal>=60 && heartRateVal<100) {heartRateString="Normal";
            probabilityVal=(Math.random()*8)+1;
        }

        if(heartRateVal>=100 && heartRateVal<200) {heartRateString="High";
            probabilityVal=(Math.random()*20)+10;
        }
    }

    void determineDibetes(){
        Log.i("customLog", "AnalyseFragment -> determineDibetes(): executed");
        if(dibeticString.equals("YES")) {
            probabilityVal = probabilityVal + 10;
        }
        else { }
    }

    void determineBloodPressure(){  //systolic (upper)
        Log.i("customLog", "AnalyseFragment -> determineBloodPressure(): executed");

        if(bloodPressureVal<120) {bloodPressureString="Normal";}

        else if(bloodPressureVal>=120 && bloodPressureVal<139) {
            bloodPressureString="Pre-hypertension";
            probabilityVal = probabilityVal + 5;
        }

        else if(bloodPressureVal>=140 && bloodPressureVal<159) {
            bloodPressureString="Hypertension";
            probabilityVal = probabilityVal + 10;
        }
    }

    void determineBMI(){
        Log.i("customLog", "AnalyseFragment -> determineBMI(): executed");

        if(bmiVal<18.5) {bmiString="Underweight";}

        else if(bmiVal>=18.5 && bmiVal<24.9) {
            bmiString="Normal";
        }

        else if(bmiVal>=25 && bmiVal<29.9) {
            bmiString="Overweight";
            probabilityVal = probabilityVal + 5;
        }

        else {
            bmiString="Obesity";
            probabilityVal = probabilityVal + 10;
        }
    }

    void determineAge(){
        Log.i("customLog", "AnalyseFragment -> determineAge(): executed");

        if(ageVal<30){ageString="Young";}

        else if(ageVal>=30 && ageVal<=50) {
            ageString="Middle";
            probabilityVal = probabilityVal + 5;
        }

        else {
            ageString="Elderly";
            probabilityVal = probabilityVal + 10;
        }
    }

    void determineStress()      //Using attention and meditation
    {
        Log.i("customLog", "AnalyseFragment -> determineStress(): executed");

        int clusterNum=-1;
        double att, med, distance, shortestDistance=9999.0;
        for (int i = 0; i < Clusters.attentionCluster.clusterList.length; i++) {
            att=Clusters.attentionCluster.clusterList[i];
            med=Clusters.meditationCluster.clusterList[i];
            distance = Math.sqrt(Math.pow(attentionVal-att, 2) + Math.pow(meditationVal-med, 2));
            if(distance<shortestDistance)
            {
                shortestDistance=distance;
                clusterNum=i;
            }
        }
        switch (clusterNum)
        {
            case 0:
                stressString = "No";
                //probabilityVal = probabilityVal + 30;
                break;

            case 1:
                stressString = "Yes";
                probabilityVal = probabilityVal + 20;
                break;

            case 2:
                stressString = "No";
                //probabilityVal = probabilityVal + 20;
                break;

            default:
                stressString ="Null";
                break;
        }
    }

    void determineCholestrol(){
        Log.i("customLog", "AnalyseFragment -> determineCholestrol(): executed");

        if(cholestrolVal<200) {cholestrolString="Yes";}
        else {cholestrolString="NO";}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Log.i("customLog", "Analyse Fragment: onCreateView() executed");

        view = inflater.inflate(R.layout.fragment_analyse, container, false);
        auth = FirebaseAuth.getInstance();
        authUid = auth.getUid();

        dibeticSpinner = view.findViewById(R.id.analyseDiabetesSpinner);
        ArrayAdapter<CharSequence> dibeticAdapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.dibetic, android.R.layout.simple_spinner_item);
        dibeticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dibeticSpinner.setAdapter(dibeticAdapter);
        dibeticSpinner.setOnItemSelectedListener(this);

        heartRateEditText = (EditText) view.findViewById(R.id.analyseHeartRateEditText);
        bloodPressureEditText = (EditText) view.findViewById(R.id.analyseBloodPressureEditText);
        heightEditText = (EditText) view.findViewById(R.id.analyseHeightEditText);
        weightEditText = (EditText) view.findViewById(R.id.analyseWeightEditText);
        ageEditText = (EditText) view.findViewById(R.id.analyseAgeEditText);
        attentionEditText = (EditText) view.findViewById(R.id.analyseAttentionEditText);
        meditationEditText = (EditText) view.findViewById((R.id.analyseMeditationEditText));

        anlayseButton = view.findViewById(R.id.analyseFragmentButton);
        anlayseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateData()==true)
                {
                    probabilityVal=Double.valueOf(decimalFormat.format(probabilityVal));

                    Intent intent = new Intent(getActivity(), ResultActivity.class);

                    intent.putExtra("diabetes", dibeticString);
                    intent.putExtra("probability", probabilityVal);
                    intent.putExtra("stress", stressString);

                    //Numerical Values
                    intent.putExtra("heartRate", heartRateVal);
                    intent.putExtra("bloodPressure", bloodPressureVal);
                    intent.putExtra("bmi", bmiVal);
                    intent.putExtra("age", ageVal);
                    intent.putExtra("meditation", meditationVal);
                    intent.putExtra("attention", attentionVal);

                    //Decision Values
                    intent.putExtra("heartRateString", heartRateString);
                    intent.putExtra("bloodPressureString", bloodPressureString);
                    intent.putExtra("bmiString", bmiString);
                    intent.putExtra("ageString", ageString);
                    intent.putExtra("meditationString", meditationString);
                    intent.putExtra("attentionString", attentionString);

                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });

        return view;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        dibeticString = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
