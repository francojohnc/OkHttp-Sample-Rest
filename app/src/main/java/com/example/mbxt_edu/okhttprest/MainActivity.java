package com.example.mbxt_edu.okhttprest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mbxt_edu.okhttprest.webservice.WebClient;
import com.example.mbxt_edu.okhttprest.webservice.WebRequest;
import com.example.mbxt_edu.okhttprest.webservice.WebServiceListiner;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;



public class MainActivity extends AppCompatActivity {
    private TextView txtresponse;
    public static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cast();
    }

    private void cast() {
        txtresponse = (TextView)findViewById(R.id.txtresponse);
    }

    public void get(View v) {
        //make request to the server
        WebRequest webRequest = new WebRequest();
        webRequest.setUrl(Endpoint.url + Endpoint.user);
        //create client
        WebClient webClient = new WebClient();
        webClient.execute(webRequest);
        webClient.setWebServiceListiner(new WebServiceListiner() {
            @Override
            public void onFailure(String message) {
                Log.e(TAG,"response code: "+ message);
            }
            @Override
            public void onResponse(int responseCode, String response) {
                Log.e(TAG, "response code: " + response);
            }
        });

    }
    public void getuser(View v) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(Endpoint.url+Endpoint.user+"?id=18").addHeader("X-API-KEY", "123456").addHeader("Content-Type","application/json").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                Log.e(TAG,"response code: "+ request.toString());
            }
            @Override
            public void onResponse(final Response response) {
                Log.e(TAG, "response json: "+response.toString());
                try {
                    Log.e(TAG, response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void postp(View v) {
        OkHttpClient client = new OkHttpClient();
        //make param
        RequestBody requestBody = new FormEncodingBuilder().add("username", "Jurassic Park").add("user_password", "Jurassic Park").add("user_email", "Jurassic Park").build();


        Request request = new Request.Builder().url(Endpoint.url + Endpoint.user)
                .addHeader("X-API-KEY", "123456")
                .addHeader("Content-Type", "application/json")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                Log.e(TAG, "response code: " + request.toString());
            }

            @Override
            public void onResponse(final Response response) {
                Log.e(TAG, "response json: " + response.toString());
                try {
                    Log.e(TAG, response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void put(View v) throws JSONException {
        OkHttpClient client = new OkHttpClient();
        //make json param
        MediaType JSON= MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, jsonPut());
        Request request = new Request.Builder().url(Endpoint.url+Endpoint.user)
                .addHeader("X-API-KEY", "123456")
                .addHeader("Content-Type","application/json")
                .put(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                Log.e(TAG,"response code: "+ request.toString());
            }
            @Override
            public void onResponse(final Response response) {
                Log.e(TAG, "response json: "+response.toString());
                try {
                    Log.e(TAG, response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void upload(View  v){
        String serverUrl ="";
        String photoPath="";
        String paramNameString = "";
        OkHttpClient client = new OkHttpClient();
        File file = new File(photoPath);
        RequestBody requestBody = new MultipartBuilder().type(MultipartBuilder.FORM).addFormDataPart(paramNameString,file.getName(),RequestBody.create(MediaType.parse("image/png"), file) ).build();
        Request request = new Request.Builder()
                .url(serverUrl)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException exc) {
                exc.printStackTrace();
            }

            @Override
            public void onResponse(com.squareup.okhttp.Response response) throws IOException {
                Log.d(TAG, response.body().string());
            }
        });
    }
    public void postj(View v) throws JSONException {
        OkHttpClient client = new OkHttpClient();
        //make json param
        MediaType JSON= MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, json());
        Request request = new Request.Builder().url(Endpoint.url+Endpoint.user)
                .addHeader("X-API-KEY", "123456")
                .addHeader("Content-Type","application/json")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                Log.e(TAG,"response code: "+ request.toString());
            }
            @Override
            public void onResponse(final Response response) {
                Log.e(TAG, "response json: "+response.toString());
                try {
                    Log.e(TAG, response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //create json
    private String json() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","new user");
        jsonObject.put("user_password","new password");
        jsonObject.put("user_email", "new email");
        return jsonObject.toString();
    }
    //create json
    private String jsonPut() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id","18");
        jsonObject.put("username","new user edited");
        jsonObject.put("user_password","new password edited");
        jsonObject.put("user_email","new email edited");
        return jsonObject.toString();
    }
}
