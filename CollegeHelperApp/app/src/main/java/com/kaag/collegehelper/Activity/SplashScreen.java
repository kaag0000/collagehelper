package com.kaag.collegehelper.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.kaag.collegehelper.R;

import org.json.JSONObject;

public class SplashScreen extends AppCompatActivity {

    Button button;
    JSONObject responseinobj = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Intent i = new Intent(getApplicationContext(),LoginScreen.class);
        startActivity(i);
    }

}
