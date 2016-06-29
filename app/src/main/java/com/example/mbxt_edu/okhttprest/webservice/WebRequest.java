package com.example.mbxt_edu.okhttprest.webservice;

import com.squareup.okhttp.Request;

/**
 * Created by johncarlofranco.com on 2/14/2016.
 */
public class WebRequest {
    private String url;
    public void setUrl(String url) {
        this.url = url;
    }
    public Request getRequest(){
        // you can change dynamically  it depends to your library
        Request request = new Request.Builder().url(url).addHeader("X-API-KEY", "123456").addHeader("Content-Type","application/json").build();
        return request;
    }
}
