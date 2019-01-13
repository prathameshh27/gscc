package com.example.spydey.prototypeone;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class AnalyseFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private EditText bloodpressureEditText, heightEditText, weightEditText;
    private String dibeticString, bloodpressureVal, heightVal, weightVal, bmiVal, authUid;
    private Integer age;
    private FirebaseAuth auth;
    private Button anlayseButton;

    public AnalyseFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_analyse, container, false);

        Spinner dibeticSpinner = view.findViewById(R.id.analyseDiabetesSpinner);
        ArrayAdapter<CharSequence> dibeticAdapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.dibetic, android.R.layout.simple_spinner_item);
        dibeticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dibeticSpinner.setAdapter(dibeticAdapter);
        dibeticSpinner.setOnItemSelectedListener(this);

        auth = FirebaseAuth.getInstance();
        authUid = auth.getUid();
        bloodpressureEditText=(EditText)view.findViewById(R.id.analyseBloodPressureEditText);
        heightEditText=(EditText)view.findViewById(R.id.analyseHeightEditText);
        weightEditText=(EditText)view.findViewById(R.id.analyseWeightEditText);

        anlayseButton = view.findViewById(R.id.analyseFragmentButton);
        anlayseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Activity under construction", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    void onClickAnalyse(View view){
        Toast.makeText(this.getActivity(), "Activity under construction", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        dibeticString = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
