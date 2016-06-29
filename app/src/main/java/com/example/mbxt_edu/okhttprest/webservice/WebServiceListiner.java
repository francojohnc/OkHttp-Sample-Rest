package com.example.mbxt_edu.okhttprest.webservice;

/**
 * Created by johncarlofranco.com on 2/14/2016.
 */
public interface WebServiceListiner {
    void onFailure(String message);
    void onResponse(int responseCode,String response);
}
