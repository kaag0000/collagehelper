package com.kaag.collegehelper.Activity;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kaag.collegehelper.CommonClasses.ApiCallerService;
import com.kaag.collegehelper.CommonClasses.JSONParserConvertor;
import com.kaag.collegehelper.Helpers.ServerUrlManager;
import com.kaag.collegehelper.R;

import org.json.JSONException;
import org.json.JSONObject;

public class SplashScreen extends AppCompatActivity {

    Button button;
    ArrayMap<String, Object> m_params;
    JSONObject responseinobj = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiCallerService.getInstance().post(ServerUrlManager.getRegistrationURL(), m_params, new ApiCallerService.RestClientListener() {
                    @Override
                    public void onSuccess(String response) {
                        responseinobj = JSONParserConvertor.makeStringToJsonObject(response);
                        try {
                            Log.e("jsonResponse", responseinobj.getString("success"));
                            Log.e("jsonResponse", responseinobj.getString("message"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(String error) {
                        Log.e("errresponse", "" + error);
                    }
                });
            }
        });
    }

}
