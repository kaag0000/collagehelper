package com.prod.prodProject.HTTPCLient;

import android.support.v4.util.ArrayMap;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class HttpReqPkg {

    private String method = "GET";

    private ArrayMap<String, Object> params = new ArrayMap<>();

    private String password = null;

    private String uri;

    private String username = null;

    /**
     * Gets encoded params.
     *
     * @return the encoded params
     */
    public String getEncodedParams() {
        StringBuilder sb = new StringBuilder();
        String value = null;
        for (String key : params.keySet()) {
            try {
                value = URLEncoder.encode(String.valueOf(params.get(key)), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(key).append("=").append(value);
        }
        return sb.toString();
    }

    /**
     * Gets method.
     *
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * Sets method.
     *
     * @param method the method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Gets params.
     *
     * @return the params
     */
    public ArrayMap<String, Object> getParams() {
        return params;
    }

    /**
     * Sets params.
     *
     * @param params the params
     */
    public void setParams(ArrayMap<String, Object> params) {
        this.params = params;
    }

    /**
     * Gets uri.
     *
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * Sets uri.
     *
     * @param uri the uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets param.
     *
     * @param key   the key
     * @param value the value
     */
    public void setParam(String key, String value) {
        params.put(key, value);
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    String getPassword() {
        return uri;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

