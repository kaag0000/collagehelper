package com.kaag.collegehelper.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.kaag.collegehelper.R;

import org.json.JSONObject;

public class SplashScreen extends AppCompatActivity {


    private Button login;
    private Button registration;
    JSONObject responseinobj = null;


    public void Openlogin() {
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
    }


    public void OpenRegistration() {
        Intent intent = new Intent(this, Regstration_Screen.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        /*Intent i = new Intent(getApplicationContext(),LoginScreen.class);
        startActivity(i);
        */
        login = (Button) findViewById(R.id.Login);
        Button login = (Button) findViewById(R.id.Login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Openlogin();
            }
        });


        registration = (Button) findViewById(R.id.REGISTRATION);
        Button registation = (Button) findViewById(R.id.REGISTRATION);
        registation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenRegistration();
            }
        });


    }

}


