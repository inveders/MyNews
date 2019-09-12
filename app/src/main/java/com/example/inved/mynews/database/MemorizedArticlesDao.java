package com.example.inved.mynews.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MemorizedArticlesDao {

    @Query("SELECT * FROM memorized_articles")
    LiveData<List<MemorizedArticles>>  getAllMemorizedArticles();


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNewArticle(MemorizedArticles memorizedArticles);

    @Query("DELETE FROM memorized_articles WHERE did=:id")
    void deleteMemorizedArticle(int id);


    @Query("SELECT COUNT(url) FROM memorized_articles")
    int getRowCount();

    @Query("SELECT * FROM memorized_articles ORDER BY did DESC LIMIT 1")
    int getIdNumber();
}