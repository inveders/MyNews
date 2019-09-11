package com.example.inved.mynews.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.inved.mynews.database.MemorizedArticles;
import com.example.inved.mynews.database.MemorizedArticlesDao;
import com.example.inved.mynews.database.MemorizedArticlesDatabase;

import java.util.List;

public class MemorizedArticlesViewModel extends AndroidViewModel {

    private MemorizedArticlesDao memorizedArticlesDao;
    private LiveData<List<MemorizedArticles>> memorizedArticlesLiveData;

    public MemorizedArticlesViewModel(@NonNull Application application) {
        super(application);
        memorizedArticlesDao = MemorizedArticlesDatabase.getDatabase(application).memorizedArticlesDao();
        memorizedArticlesLiveData = memorizedArticlesDao.getAllMemorizedArticles();
    }

    public LiveData<List<MemorizedArticles>> getMemorizedArticlesList() {
        return memorizedArticlesLiveData;
    }

    public void insert(MemorizedArticles memorizedArticles) {
        memorizedArticlesDao.insertNewArticle(memorizedArticles);
    }

    public int getSizeDatabase() {
        return memorizedArticlesDao.getRowCount();
    }

    public void deleteArticle(int id) {
        memorizedArticlesDao.deleteMemorizedArticle(id);
    }

    public int getId() {
       return memorizedArticlesDao.getIdNumber();
    }
}
