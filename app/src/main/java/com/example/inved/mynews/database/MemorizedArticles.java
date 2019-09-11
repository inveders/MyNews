package com.example.inved.mynews.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "memorized_articles",
        indices = {@Index(value = "url", unique = true)})
public class MemorizedArticles {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "did")
    public int id;

    @ColumnInfo(name = "url")
    @NonNull
    public String url;

    @NonNull
    public String getUrl() {
        return url;
    }


    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    public MemorizedArticles(@NonNull String url) {
        this.url = url;
    }


}
