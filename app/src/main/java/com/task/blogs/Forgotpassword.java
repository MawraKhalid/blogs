package com.task.blogs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.nio.channels.AcceptPendingException;

public class Forgotpassword extends AppCompatActivity {

    EditText email;
    Button recoverpassword;
    FirebaseAuth auth;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        email = findViewById(R.id.forgetEmail);
        recoverpassword = findViewById(R.id.forgotbtn);
        pd = new ProgressDialog(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Forgot Password");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();

        recoverpassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String etEmail = email.getText().toString();
                if (TextUtils.isEmpty(etEmail)){
                    email.setError("Email is required");
                }else
                    recoverpassword();
            }
        });
    }

    private void recoverpassword() {
        pd.setMessage("Please wait");
        pd.show();
        auth.sendPasswordResetEmail(String.valueOf(email)).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    pd.dismiss();
                    Toast.makeText(Forgotpassword.this, "Please check email link is sent", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Forgotpassword.this, MainActivity.class));
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(Forgotpassword.this, ""+e, Toast.LENGTH_SHORT).show();
            }
        });
    }
}