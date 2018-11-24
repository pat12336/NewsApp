package com.example.rkjc.news_app_2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class JsonUtils {



    public static ArrayList<NewsItem> parseNews(String JSONString)  {
        ArrayList<NewsItem> articleList = new ArrayList<NewsItem>();
        try {
            JSONObject jsonObject = new JSONObject(JSONString);

            JSONArray articles = jsonObject.getJSONArray("articles");

            for(int i =0; i < articles.length(); i++){
                JSONObject article = articles.getJSONObject(i);
                String author = article.getString("author");
                String title = article.getString("title");
                String description = article.getString("description");
                String url = article.getString("url");
                String urlToImage = article.getString("urlToImage");
                String publishedAt = article.getString("publishedAt");

                articleList.add(new NewsItem(title, author, description, url, urlToImage, publishedAt));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return articleList;

    }

}


