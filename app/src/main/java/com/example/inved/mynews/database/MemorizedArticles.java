package com.example.inved.mynews.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "MemorizedArticles")
public class MemorizedArticles {

    public MemorizedArticles(@NonNull String url) {
        this.url = url;
    }

    @NonNull
    @PrimaryKey
    private String url;

    @NonNull
    public String getUrl() {
        return url;
    }

    @NonNull
    public void setUrl(String url) {
        this.url = url;
    }

}
