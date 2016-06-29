package com.example.mbxt_edu.okhttprest.webservice;

import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by johncarlofranco.com on 2/14/2016.
 */
public class WebClient {
    private WebServiceListiner webServiceListiner;
    // you can change dynamically  it depends to your library
    private OkHttpClient client = new OkHttpClient();
    public void execute(WebRequest webRequest){
        client.newCall(webRequest.getRequest()).enqueue(new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                if(webServiceListiner!=null)webServiceListiner.onFailure(request.toString());
            }
            @Override
            public void onResponse(final Response response) {
                try {
                   if(webServiceListiner!=null)webServiceListiner.onResponse(response.code(),response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void setWebServiceListiner(WebServiceListiner webServiceListiner) {
        this.webServiceListiner = webServiceListiner;
    }
}
