
/*
package backup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.spydey.prototypeone.R;
import com.google.firebase.auth.FirebaseAuth;

public class AnalyseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText bloodpressureEditText, heightEditText, weightEditText;
    private String dibeticString, bloodpressureVal, heightVal, weightVal, bmiVal, authUid;
    private Integer age;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyse);

        Spinner dibeticSpinner = findViewById(R.id.analyseDiabetesSpinner);
        ArrayAdapter<CharSequence> dibeticAdapter = ArrayAdapter.createFromResource(this,R.array.dibetic, android.R.layout.simple_spinner_item);
        dibeticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dibeticSpinner.setAdapter(dibeticAdapter);
        dibeticSpinner.setOnItemSelectedListener(this);

        auth = FirebaseAuth.getInstance();
        authUid = auth.getUid();
        bloodpressureEditText=(EditText)findViewById(R.id.analyseBloodPressureEditText);
        heightEditText=(EditText)findViewById(R.id.analyseHeightEditText);
        weightEditText=(EditText)findViewById(R.id.analyseWeightEditText);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        dibeticString = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onClickAnalyse(View view) {
    }
}
*/