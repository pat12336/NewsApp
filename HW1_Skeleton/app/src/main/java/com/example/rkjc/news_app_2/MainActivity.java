package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements NewsRecyclerViewAdapter.ListItemClickListener {
    private RecyclerView mRecyclerView;
    private NewsRecyclerViewAdapter mAdapter;
    private ArrayList<NewsItem> repos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.repos = new ArrayList<NewsItem>();
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.news_recyclerview);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new NewsRecyclerViewAdapter(this, this.repos);
        mRecyclerView.setAdapter(mAdapter);
        makeSearchUrl();
    }

    private void makeSearchUrl() {
        URL newsSearchUrl = NetworkUtils.buildURL();
        System.out.println(newsSearchUrl);
        new NewsQueryTask().execute(newsSearchUrl);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            makeSearchUrl();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public void onListItemClick(int index){
        String url = repos.get(index).getUrl();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }

    }



    public class NewsQueryTask extends AsyncTask<URL, Void, String> {


        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(URL... urls) {
            URL newsSearch = urls[0];
            String results = null;
            try{
                results = NetworkUtils.getResponseFromHttpUrl(newsSearch);
            }catch (IOException e){
                e.printStackTrace();
            }
            return results;
        }

        @Override
        protected void onPostExecute(String news){
            if(news != null || !news.equals("")){
                System.out.println(news);
                repos.clear();
                repos.addAll(JsonUtils.parseNews(news));
                mAdapter.notifyDataSetChanged();
            }

        }
    }

}
