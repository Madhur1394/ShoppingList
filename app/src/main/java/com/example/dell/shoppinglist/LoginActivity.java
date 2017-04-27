package com.example.dell.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by dell on 4/26/17.
 */

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Button btnLogin,btnLinkToRegister;
    private EditText editTextEmail,editTextPassword;
    private ProgressBar progressBar;
    private String email,password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);

        //Get View Instance
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        editTextEmail =(EditText) findViewById(R.id.email_1);
        editTextPassword = (EditText) findViewById(R.id.password_1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar3);

        //Get Firebase Auth Instance
        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = editTextEmail.getText().toString().trim();
                password = editTextPassword.getText().toString().trim();
                progressBar.setVisibility(View.VISIBLE);

                if(email.equals("")){
                    editTextEmail.setError("Please Enter the Email ID");
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if(password.equals("") || password.length()<=6){
                    editTextPassword.setError("Please Enter valid Password");
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                else
                {
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           if(!task.isSuccessful()) {
                               Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_LONG).show();
                               progressBar.setVisibility(View.GONE);
                           }
                           else{
                               startActivity(new Intent(LoginActivity.this,MainActivity.class));
                               Toast.makeText(getApplicationContext(),"Login is Successful",Toast.LENGTH_LONG).show();
                               progressBar.setVisibility(View.GONE);
                               finish();
                           }
                        }
                    });
                }

            }
        });

        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
                finish();
            }
        });

    }
}
