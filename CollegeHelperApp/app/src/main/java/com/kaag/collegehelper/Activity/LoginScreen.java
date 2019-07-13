package com.kaag.collegehelper.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.kaag.collegehelper.CommonClasses.JSONParserConvertor;
import com.kaag.collegehelper.HTTPCLient.OptimusHTTP;
import com.kaag.collegehelper.Helpers.ServerUrlManager;
import com.kaag.collegehelper.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginScreen extends AppCompatActivity {

    //private EditText email;
    private EditText rollno;
    private EditText pass;
    private Button btn;
    ArrayMap<String, Object> m_params;

    public void OpenReg(){
        Intent intent = new Intent(this, Regstration_Screen.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
      //  email = (EditText) findViewById(R.id.email);
        rollno=(EditText) findViewById(R.id.rollno);
        pass = (EditText) findViewById(R.id.pass);
        btn = (Button) findViewById(R.id.btn);
        m_params = new ArrayMap<String, Object>();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   m_params.put("rollno", email.getText());
                m_params.put("rollno",rollno.getText());
                m_params.put("pass", pass.getText());
                OptimusHTTP optimus = new OptimusHTTP(getApplicationContext());
                optimus.setMethod(OptimusHTTP.METHOD_POST);
                optimus.makeRequest(ServerUrlManager.getLoginURL(), m_params, new OptimusHTTP.ResponseListener() {
                    @Override
                    public void onFailure(String msg) {
                        Log.e("Fail", msg);
                    }

                    @Override
                    public void onSuccess(String msg) throws JSONException {
                        Log.e("Fail", msg);
                        JSONObject jsonObj = JSONParserConvertor.makeStringToJsonObject(msg);
                        int code = (int) jsonObj.get("success");
                        if (code == 1) {
                            Toast.makeText(getApplicationContext(), jsonObj.getString("message"), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), jsonObj.getString("message"), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });





        Button btn = (Button)findViewById(R.id.REGISTRATION);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenReg();
            }
        });


    }
}
