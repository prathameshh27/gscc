package com.example.spydey.prototypeone;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText editTextFirstName, editTextLastName, editTextEmail,
                    editTextPassword, editTextPhone, editTextBirth;
    private String genderstring;
    private Spinner genderSpinner;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private FirebaseDatabase realdb;

    public void onSuccessfulSignup()
    {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextFirstName =(EditText) findViewById(R.id.firstname_field);
        editTextLastName =(EditText) findViewById(R.id.lastname_field);
        editTextEmail = (EditText)findViewById(R.id.email2_field);
        editTextPassword = (EditText)findViewById(R.id.password2_field);
        editTextPhone = (EditText)findViewById(R.id.phone_field);
        editTextBirth = (EditText)findViewById(R.id.birthdate_field);

        auth = FirebaseAuth.getInstance();
        realdb = FirebaseDatabase.getInstance();
        //findViewById(R.id.signup_button).setOnClickListener(this);
        progressBar = findViewById(R.id.progressBarSignUp);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(auth.getCurrentUser()!=null)
        {
            //go to new activity
        }

        genderSpinner = findViewById(R.id.gender_spinner);
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,R.array.gender, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);
        genderSpinner.setPrompt("Gender");
        genderSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        genderstring= parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public  void onClickSignup(View view)
    {
        final String firstnamestring = editTextFirstName.getText().toString().trim();
        final String lastnamestring = editTextLastName.getText().toString().trim();
        final String emailstring = editTextEmail.getText().toString().trim();
        String passwordstring = editTextPassword.getText().toString().trim();
        final String phonestring = editTextPhone.getText().toString().trim();
        final String birthstring = editTextBirth.getText().toString().trim();

        if (firstnamestring.isEmpty() || lastnamestring.isEmpty() || emailstring.isEmpty() ||
                passwordstring.isEmpty() || phonestring.isEmpty() || birthstring.isEmpty()) {
            //editTextFirstName.setError("Field cannot be Blank");
            //editTextFirstName.requestFocus();
            Toast.makeText(this, "Text Field cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(emailstring,passwordstring).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Log.v("CHECK","CREATING OBJ");
                            User userObject = new User(firstnamestring,lastnamestring,emailstring,phonestring,birthstring,genderstring);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(auth.getCurrentUser().getUid()).setValue(userObject)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(SignupActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                        onSuccessfulSignup();
                                    }
                                    else
                                        Toast.makeText(SignupActivity.this, "Error occurred while adding user data", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        else
                            Toast.makeText(SignupActivity.this, "Error Occurred while adding user", Toast.LENGTH_SHORT).show();

                        progressBar.setVisibility(View.GONE);
                    }
                });


    }
}
