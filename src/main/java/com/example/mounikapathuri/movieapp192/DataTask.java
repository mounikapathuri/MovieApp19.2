package com.example.mounikapathuri.movieapp192;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by mounikapathuri on 24-02-2018.
 */

public class DataTask extends AsyncTask<Void,Void,String> {
    private Context mContext;
    private String mUrl;
    DataListener dataListener;

    public DataTask(Context context, String url, DataListener listener) {
        mContext=context;
        mUrl=url;
        dataListener=listener;
    }

    @Override
    protected String doInBackground(Void... params) {
        OkHttpClient okHttpClient = new OkHttpClient();
        //setting up connction timout to 2 min
        okHttpClient.setConnectTimeout(120, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(120, TimeUnit.SECONDS);

        Request request = new Request.Builder().url(mUrl).build();
        String responseData=null;

        try {
            Response response =okHttpClient.newCall(request).execute();
            if (response.isSuccessful()){
                responseData=response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseData;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        dataListener.updateList(s);
    }
}
