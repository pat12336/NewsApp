package com.example.rkjc.news_app_2;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;

public class NewsQueryTask extends AsyncTask {
    @Override
    protected String doInBackground(Object[] objects) {

        try {
            return NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
