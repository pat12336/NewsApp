package com.example.rkjc.news_app_2;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NewsItemDao {

    @Query("SELECT * FROM news_item")
    LiveData<List<NewsItem>> loadAllNewsItems();

    @Insert
    void insert(NewsItem items);

    @Query ("DELETE FROM news_item")
    void clearAll();


}
