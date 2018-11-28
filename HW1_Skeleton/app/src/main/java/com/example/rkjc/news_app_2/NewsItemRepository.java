package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class NewsItemRepository {
    private NewsItemDao newsItemDao;
    private LiveData<List<NewsItem>> AllNewsItems;

    public NewsItemRepository(Application application) {
        NewsItemRoomDatabase db =NewsItemRoomDatabase.getDatabase(application.getApplicationContext());
        newsItemDao = db.newsItemDao();
        AllNewsItems = newsItemDao.loadAllNewsItems();
    }

    public LiveData<List<NewsItem>> getAllNewsItems() {
        return AllNewsItems;
    }

    public void fromDatabase (NewsItem item) {
        new fromDatabaseAsyncTask(newsItemDao).execute(item);
    }

    public void fromAPI(){ new fromAPIAsyncTask(newsItemDao).execute();
    }

    private static class fromDatabaseAsyncTask extends AsyncTask<NewsItem, Void, Void> {
        private NewsItemDao mAsyncTaskDao;
        fromDatabaseAsyncTask(NewsItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final NewsItem... params) {
            mAsyncTaskDao.loadAllNewsItems();
            return null;
        }
    }

    private static class fromAPIAsyncTask extends AsyncTask<Void, Void, Void> {
        private NewsItemDao mAsyncTaskDao;
        fromAPIAsyncTask(NewsItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String results = "";
            if (mAsyncTaskDao != null)
                mAsyncTaskDao.clearAll();
            try {
                results = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (results != null || !results.equals("")) {
                List<NewsItem> items = new ArrayList<>(JsonUtils.parseNews(results));
                for (int i = 0; i < items.size(); i++) {
                    mAsyncTaskDao.insert(items.get(i));
                }
                mAsyncTaskDao.loadAllNewsItems();
            }
            return null;
        }

    }
}
