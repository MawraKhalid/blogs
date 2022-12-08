package com.task.blogs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {

    private EditText RgEmail, RgPassword;
    Button Rg_button;
    TextView already;

    FirebaseAuth mAuths;
    ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        RgEmail = findViewById(R.id.RgEmail);
        RgPassword = findViewById(R.id.RgPassword);
        Rg_button = findViewById(R.id.Rg_button);
        already = findViewById(R.id.already);

        mAuths = FirebaseAuth.getInstance();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Register");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        progressDialog = new ProgressDialog(this);

        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, MainActivity.class));
            }
        });

        Rg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = RgEmail.getText().toString().trim();
                String password = RgPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    RgEmail.setError("Email is required");
                }else if (TextUtils.isEmpty(password)){
                    RgPassword.setError("Password is required");
                }else if (password.length() <6 ){
                    Toast.makeText(Registration.this, "Password leng must b greater than 6", Toast.LENGTH_SHORT).show();
                }else {
                    Rg_buttonUser(email , password);
                }


            }
        });
    }

    private void Rg_buttonUser(String email, String password) {
        progressDialog.setTitle("Please wait.. ");

        mAuths.createUserWithEmailAndPassword(email , password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            progressDialog.dismiss();
                            startActivity(new Intent(Registration.this, Homepage.class));
                            Toast.makeText(Registration.this,"Login Successfull",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Registration.this,"Login failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Registration.this,""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}