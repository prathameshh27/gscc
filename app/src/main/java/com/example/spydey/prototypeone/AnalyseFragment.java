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
            bmiString, ageString, attentionString, meditationString, cholestrolString, authUid;
    private Double heartRateVal, bloodPressureVal, heightVal, weightVal, bmiVal,
            ageVal, attentionVal, meditationVal, cholestrolVal, probabilityVal;
    private DecimalFormat decimalFormat;
    private FirebaseAuth auth;
    private Button anlayseButton;
    private View view;
    private Spinner dibeticSpinner;
    private Clusters attentionCluster, meditationCluster;
    private FirebaseDatabase realdb;
    private DatabaseReference dbRef;
    public Clusters cluster;

    public AnalyseFragment() {
        // Required empty public constructor
        heartRateVal=bloodPressureVal=heightVal=weightVal=bmiVal=ageVal=probabilityVal=0.0;

        //attentionCluster = Clusters.getAttention();
        //meditationCluster = Clusters.getMeditation();

        Log.d("customLog","Into Analyse constructor");
        //Log.d("customLog", "att: "+attentionCluster.cluster1+" med: "+meditationCluster.cluster1);

        //TODO: to be deleted
        probabilityVal=Math.random()*20+10;
    }

    Boolean validateData(){

        decimalFormat = new DecimalFormat("#.00");

        heartRateString=heartRateEditText.getText().toString();
        bloodPressureString=bloodPressureEditText.getText().toString();
        ageString=ageEditText.getText().toString();
        heightString=heightEditText.getText().toString(); 
        weightString=weightEditText.getText().toString();
        attentionString = attentionEditText.getText().toString();
        meditationString = meditationEditText.getText().toString();
        
        if(!heartRateString.isEmpty() && !bloodPressureString.isEmpty() && !heightString.isEmpty() && !weightString.isEmpty())
        {
            heartRateVal=Double.valueOf(heartRateString);
            bloodPressureVal=Double.valueOf(bloodPressureString);
            ageVal=Double.valueOf(ageString);
            heightVal=Double.valueOf(heightString);
            weightVal=Double.valueOf(weightString);
            bmiVal=Double.valueOf(decimalFormat.format(weightVal/(heightVal*heightVal)));
            attentionVal=Double.valueOf(attentionString);
            meditationVal=Double.valueOf(meditationString);

            Log.d("customLog", heartRateVal+" "+bloodPressureVal+" "+heightVal+" "+weightVal+" "+ageVal+" "+attentionVal+" "+meditationVal);

            /////////////////////////////////////////////////
            determineHeartRate();
            determineBloodPressure();
            determineBMI();
            determineAge();
            determineAttention();
            determineMeditation();

            return true;
        }
        else { return false; }
    }

    void determineDibetes(){
        if(dibeticString=="YES") {
        }

        else{
        }
    }

    void determineHeartRate(){
        if(heartRateVal>=0 && heartRateVal<60) {heartRateString="LOW";
            probabilityVal=(Math.random()*20)+10;
        }

        if(heartRateVal>=60 && heartRateVal<100) {heartRateString="NORMAL";
            probabilityVal=(Math.random()*8)+1;
        }

        if(heartRateVal>=100 && heartRateVal<200) {heartRateString="HIGH";
            probabilityVal=(Math.random()*20)+10;
        }
    }

    void determineBloodPressure(){  //systolic (upper)

        if(bloodPressureVal<120) {bloodPressureString="NORMAL";}

        if(bloodPressureVal>=120 && bloodPressureVal<139) {bloodPressureString="PREHYPERTENSION";}

        if(bloodPressureVal>=140 && bloodPressureVal<159) {bloodPressureString="HYPERTENSION";}
    }

    void determineBMI(){
        if(bmiVal<18.5) {bmiString="UNDERWEIGHT";}

        if(bmiVal>=18.5 && bmiVal<24.9) {bmiString="NORMAL";}

        if(bmiVal>=25 && bmiVal<29.9) {bmiString="OVERWEIGHT";}

        else {bmiString="OBESITY";}
    }

    void determineAge(){
        if(ageVal<30){ageString="YOUNG";}

        else if(ageVal>=31 && ageVal<=50) {ageString="MIDDLE";}

        else {ageString="ELDERLY";}
    }

    void determineMeditation(){

    }

    void determineAttention(){

    }

    void determineCholestrol(){
        if(cholestrolVal<200) {cholestrolString="YES";}

        else {cholestrolString="NO";}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Log.i("customLog", "Analyse Fragment: onCreateView() executed");

        //Clusters.loadClusters();
        Log.i("customLog", "Attention "+Clusters.attentionCluster.cluster1);
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

                    //Numerical Values
                    intent.putExtra("heartRate", heartRateVal);
                    intent.putExtra("bloodPressure", bloodPressureVal);
                    intent.putExtra("bmi", bmiVal);
                    intent.putExtra("age", ageVal);
                    intent.putExtra("meditation", meditationVal);
                    intent.putExtra("attention", attentionVal);

                    //Decision Values
                    intent.putExtra("heartRateString", heartRateVal);
                    intent.putExtra("bloodPressureString", bloodPressureVal);
                    intent.putExtra("bmiString", bmiVal);
                    intent.putExtra("ageString", ageVal);
                    intent.putExtra("meditationString", meditationVal);
                    intent.putExtra("attentionString", attentionVal);

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
