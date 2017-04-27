package com.example.dell.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by dell on 4/26/17.
 */

public class RegistrationActivity extends AppCompatActivity {

    private Button btnRegister,btnLinkToLogin;
    private EditText editTextEmail,editTextName,editTextPassword;
    private ProgressBar progressBar;
    private String email,password,name;
    private UserInfo userInfo;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrationactivity);

        //Get View Instance
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextName = (EditText) findViewById(R.id.name);
        editTextPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);

        //Get Firebase Instance
        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = editTextEmail.getText().toString().trim();
                password = editTextPassword.getText().toString().trim();
                name = editTextName.getText().toString().trim();
                userInfo = new UserInfo(name,null,password,email);

                //Set progressbar visible
                progressBar.setVisibility(View.VISIBLE);

                if(email.equals("")){
                    editTextEmail.setError("Please Enter Email");
                    return;
                }
                else if(name.equals("")){
                    editTextName.setError("Please Enter Name");
                    return;
                }
                else if(password.equals("") || password.length()<6){
                    editTextPassword.setError("Please Enter Valid Password");
                    return;
                }
                else{
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(),"Registration Failed",Toast.LENGTH_LONG).show();
                            }
                            else{
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(),"Registration Complete",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                                finish();
                            }
                        }
                    });
                }
            }
        });

        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                finish();
            }
        });
    }
}
