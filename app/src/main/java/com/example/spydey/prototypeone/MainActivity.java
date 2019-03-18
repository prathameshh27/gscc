package com.example.spydey.prototypeone;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private FirebaseAuth auth;
    private ProgressBar signinProgressBar;

    private void transitToNewActivity()
    {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //will not go to login page onclick backbutton
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = (EditText) findViewById(R.id.email_field);
        editTextPassword = (EditText) findViewById(R.id.password_field);
        signinProgressBar = findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser()!=null)
        {
            transitToNewActivity();
        }
    }

    public void onClickLogin(View view)
    {
        final String emailstring = editTextEmail.getText().toString().trim();
        final  String passwordstring = editTextPassword.getText().toString().trim();

        if(emailstring.isEmpty()||passwordstring.isEmpty())
        {
            Toast.makeText(this, "Text Field cannot be Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        signinProgressBar.setVisibility(View.VISIBLE);
        auth.signInWithEmailAndPassword(emailstring, passwordstring).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    signinProgressBar.setVisibility(View.GONE);
                    transitToNewActivity();
                }

                else
                {
                    signinProgressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }

    void onClickRegister(View view)
    {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

}
