package com.example.inved.mynews;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class MemorizedArticlesDAO {

    public static final String KEY_URL_LIST = "url_click_articles_list";
    public static final String KEY_PREFERENCES = "PREFERENCES";
    private SharedPreferences preferences;


    public MemorizedArticlesDAO(Context context) {
        preferences = context.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE);
    }


    public boolean findMemorizedArticle(String url) {
        Set<String> setString = preferences.getStringSet(KEY_URL_LIST, new HashSet<String>());

        return setString.contains(url);

    }

    public void insertUrl(String url) {
        Set<String> setString = preferences.getStringSet(KEY_URL_LIST, new HashSet<String>());
        setString.add(url);
        preferences.edit().putStringSet(KEY_URL_LIST, setString).apply();

    }


}
