package com.kaag.collegehelper.CommonClasses;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Callable;

public class ApiCaller extends AsyncTask<ArrayList, String, JSONObject> {

    private final JSONParser jsonParser = new JSONParser();

    private String METHOD = "";
    private String URL = "";
    private JSONObject callResult = null;

    public JSONObject makeApiCall(String url, String method, Map<String, String> map) {
        final ArrayList params = new ArrayList();

        if (!url.isEmpty()) {
            URL = url;
        } else {
            Log.e("ApiCaller", "URl cant be empty");
            return null;
        }
        if (!method.isEmpty()) {
            METHOD = method;
        } else {
            Log.e("ApiCaller", "Method not spacified");
            return null;
        }
        if (map.size() == 0) {
            Log.e("ApiCaller", "No parameters");
            return null;
        }
        for (Map.Entry data : map.entrySet()) {
            params.add(new BasicNameValuePair(data.getKey().toString(), data.getValue().toString()));
        }
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                return execute(params);
            }
        };
        try {
            callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return callResult;

    }

    @Override
    protected JSONObject doInBackground(ArrayList... arrayLists) {

        ArrayList data = arrayLists[0];
        JSONObject json = jsonParser.makeHttpRequest(URL, METHOD, data);
        return json;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected void onPostExecute(JSONObject result) {
        callResult = result;
    }


}
