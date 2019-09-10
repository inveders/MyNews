package com.example.inved.mynews.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MemorizedArticlesDao {

    @Query("SELECT * FROM MemorizedArticles WHERE url = :searchUrl")
    String getMemorizedArticles(String searchUrl);

    @Insert
    void insertNewArticles(MemorizedArticles newArticle);


    @Delete
    void deleteMemorizedArticles(MemorizedArticles memorizedArticle);

    @Query("SELECT COUNT(url) FROM MemorizedArticles")
    int getRowCount();
}