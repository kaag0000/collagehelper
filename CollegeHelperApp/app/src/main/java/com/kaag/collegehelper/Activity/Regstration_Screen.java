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
import com.kaag.collegehelper.HTTPCLient.HttpReq;
import com.kaag.collegehelper.HTTPCLient.OptimusHTTP;
import com.kaag.collegehelper.Helpers.ServerUrlManager;
import com.kaag.collegehelper.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.GenericArrayType;

public class Regstration_Screen<m_params> extends AppCompatActivity {


   // private EditText UID;
    private EditText rollno;
    private EditText name;
    private EditText colname;
    private EditText branch;
    private EditText group;
    private EditText type;
    private EditText email;
    private EditText phoneno;
    //private EditText ID;
    private EditText pass;

    private Button Register;
    private Button Login;
    ArrayMap<String, Object> m_params;

    private void OpenLogin(){
        Intent intent=new Intent(this,LoginScreen.class);
        startActivity(intent);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regstration__screen);



        Button btn0 = (Button)findViewById(R.id.Login);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenLogin();
            }
        });



       // UID = (EditText) findViewById(R.id.UID);
        rollno=(EditText)findViewById(R.id.ROLL_NO);
        name=(EditText)findViewById(R.id.NAME);
        colname=(EditText)findViewById(R.id.COLLEGE_NAME);
        branch=(EditText)findViewById(R.id.BRANCH);
        group=(EditText)findViewById(R.id.GROUP_);
        type=(EditText)findViewById(R.id.TYPE);
        email=(EditText)findViewById(R.id.EMAIL);
        phoneno=(EditText)findViewById(R.id.PHONE_NO);
        // ID=(EditText)findViewById(R.id.ID);
        pass=(EditText)findViewById(R.id.PASS);
        Button btn1=(Button)findViewById(R.id.Register);

        m_params = new ArrayMap<String, Object>();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // m_params.put("UID", UID.getText());po
                m_params.put("rollno", rollno.getText());
                m_params.put("name", name.getText());
                m_params.put("colname", colname.getText());
                m_params.put("branch", branch.getText());
                m_params.put("group", group.getText());
                m_params.put("type", type.getText());
                m_params.put("email", email.getText());
                m_params.put("phoneno", phoneno.getText());
              //  m_params.put("ID", ID.getText());
                m_params.put("pass", pass.getText());





                OptimusHTTP optimus = new OptimusHTTP(getApplicationContext());
                optimus.setMethod(OptimusHTTP.METHOD_POST);
                HttpReq httpReq = optimus.makeRequest(ServerUrlManager.getRegistrationURL(), m_params, new OptimusHTTP.ResponseListener() {
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







    }
}
