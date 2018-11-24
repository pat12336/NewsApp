package com.example.rkjc.news_app_2;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    private static final String scheme = "https";
    private static final String base_url = "newsapi.org";
    private static final String path1 = "v1";
    private static final String path2 = "articles";
    private static final String qp1 = "source";
    private static final String source = "the-next-web";
    private static final String qp2 = "sortBy";
    private static final String sortBy = "latest";
    private static final String qp3 = "apiKey";
    private static final String apiKey = "fac8cb4af9d74b73b749d6a5effc037f";


    public static URL buildURL(){

        Uri.Builder builder = new Uri.Builder();
        builder.scheme(scheme)
                .authority(base_url)
                .appendPath(path1)
                .appendPath(path2)
                .appendQueryParameter(qp1, source)
                .appendQueryParameter(qp2, sortBy)
                .appendQueryParameter(qp3, apiKey);

        URL url = null;
        try {
            url = new URL(builder.build().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;


    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    }
