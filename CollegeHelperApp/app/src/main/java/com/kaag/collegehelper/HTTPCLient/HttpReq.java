package com.kaag.collegehelper.HTTPCLient;

import android.os.AsyncTask;
import android.os.Build;
import android.util.ArrayMap;
import android.util.Base64;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpReq extends AsyncTask<HttpReqPkg, String, String> {

    private int connectTimeout;

    private String contentType;

    private ArrayMap<String, String> headerMap = new ArrayMap<>();

    private OptimusHTTP.ResponseListener listener;

    private int readTimeout;

    private int resCode;

    private String resMsg;

    /**
     * Instantiates a new Http req.
     *
     * @param connectTimeout the connect timeout
     * @param readTimeout    the read timeout
     * @param contentType    the content type
     */
    public HttpReq(int connectTimeout, int readTimeout, String contentType,
                   ArrayMap<String, String> headerMap) {
        resCode = 0;
        resMsg = "na";
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.contentType = contentType;

        this.headerMap = headerMap;
    }

    /**
     * Sets on results listener.
     *
     * @param listener the listener
     */
    public void setOnResultsListener(OptimusHTTP.ResponseListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(HttpReqPkg... params) {

        URL url;
        BufferedReader reader = null;

        String username = params[0].getUsername();
        String password = params[0].getPassword();
        String authStringEnc = null;

        if (username != null && password != null) {
            String authString = username + ":" + password;

            byte[] authEncBytes;
            authEncBytes = Base64.encode(authString.getBytes(), Base64.DEFAULT);
            authStringEnc = new String(authEncBytes);
        }

        String uri = params[0].getUri();

        if (params[0].getMethod().equals("GET")) {
            uri += "?" + params[0].getEncodedParams();
        }

        try {
            StringBuilder sb;
            // create the HttpURLConnection
            url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (authStringEnc != null) {
                connection.setRequestProperty("Authorization", "Basic " + authStringEnc);
            }

            if (params[0].getMethod().equals("POST")
                    || params[0].getMethod().equals("PUT")
                    || params[0].getMethod().equals("DELETE")) {
                // enable writing output to this url
                connection.setDoOutput(true);
            }

            switch (params[0].getMethod()) {
                case "POST":
                    connection.setRequestMethod("POST");
                    break;
                case "GET":
                    connection.setRequestMethod("GET");
                    break;
                case "PUT":
                    connection.setRequestMethod("PUT");
                    break;
                case "DELETE":
                    connection.setRequestMethod("DELETE");
                    break;
            }

            // give it x seconds to respond
            connection.setConnectTimeout(connectTimeout);
            connection.setReadTimeout(readTimeout);
            connection.setRequestProperty("Content-Type", contentType);

            for (int i = 0; i < headerMap.size(); i++) {
                connection.setRequestProperty(headerMap.keyAt(i), headerMap.valueAt(i));
            }

            connection.setRequestProperty("Content-Length",
                    "" + params[0].getEncodedParams().getBytes().length);

            connection.connect();
            if (params[0].getMethod().equals("POST") || params[0].getMethod().equals("PUT")) {
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(params[0].getEncodedParams());
                writer.flush();
                writer.close();
            }

            // read the output from the server
            InputStream in;
            resCode = connection.getResponseCode();
            resMsg = connection.getResponseMessage();
            if (resCode != HttpURLConnection.HTTP_OK) {
                in = connection.getErrorStream();
            } else {
                in = connection.getInputStream();
            }
            reader = new BufferedReader(new InputStreamReader(in));
            sb = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            sb.append(resCode).append(" : ").append(resMsg);
            return sb.toString();
        } catch (Exception e) {
            listener.onFailure(Integer.toString(resCode) + " : " + resMsg);
            e.printStackTrace();
        } finally {
            // close the reader; this can throw an exception too, so
            // wrap it in another try/catch block.
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {

        if (listener != null && result != null) {
            try {
                listener.onSuccess(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPreExecute() {
        disableConnectionReuseIfNecessary();
    }

    private void disableConnectionReuseIfNecessary() {
        // Work around pre-Froyo bugs in HTTP connection reuse.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }
}