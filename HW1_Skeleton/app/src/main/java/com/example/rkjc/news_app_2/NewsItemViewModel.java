package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {

    private NewsItemRepository repo;
    private LiveData<List<NewsItem>> allNewsItems;
    public NewsItemViewModel(Application application){
        super(application);
        repo = new NewsItemRepository(application);
        allNewsItems = repo.getAllNewsItems();
    }

    public LiveData<List<NewsItem>> getAllNewsItems() {
        return allNewsItems;
    }

    public void fromDatabase(NewsItem item){repo.fromDatabase(item);}

    public void fromAPI(){repo.fromAPI();}
}
