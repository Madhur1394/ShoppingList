package com.example.dell.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by dell on 4/26/17.
 */

public class LoginChooseActivity extends AppCompatActivity{

    private Button btnFacebook,btnGoogle,btnEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginchooseactivity);

        //Get View Instance
        btnFacebook = (Button) findViewById(R.id.btnFacebook);
        btnGoogle = (Button) findViewById(R.id.btnGoogle);
        btnEmail = (Button) findViewById(R.id.btnMail);

        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //For Facebook Login
            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //For Google Login
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginChooseActivity.this,LoginActivity.class));
                finish();
            }
        });
    }
}
