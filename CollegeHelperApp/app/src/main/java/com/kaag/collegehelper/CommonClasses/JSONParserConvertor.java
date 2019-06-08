package com.kaag.collegehelper.CommonClasses;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONParserConvertor {


    public static JSONObject makeStringToJsonObject(String josnString) {
        JSONObject responseObj = null;
        try {
            responseObj = new JSONObject(josnString);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return responseObj;
    }
}
