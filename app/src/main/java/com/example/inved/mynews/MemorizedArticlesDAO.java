package com.example.inved.mynews;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class MemorizedArticlesDAO {

    private static final String KEY_URL_LIST = "url_clicked_articles_list";
    private static final String KEY_PREFERENCES = "PREFERENCES";
    private SharedPreferences preferences;



    public MemorizedArticlesDAO(Context context) {
        preferences = context.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE);

    }

    public boolean findMemorizedArticle(String url) {
        Set<String> setString = preferences.getStringSet(KEY_URL_LIST, new HashSet<String>());
      //  Log.d("DEBAGO", "articles mémorisés dans la DAO " + setString);

        if (setString != null) {
            return setString.contains(url);
        }

        return false;
    }

    public void insertUrl(String url) {
        Set<String> setString = preferences.getStringSet(KEY_URL_LIST, new HashSet<String>());
        if (setString != null) {
            setString.add(url);
        }
        //  Log.d("DEBAGO", "articles insérés dans la DAO " + setString);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        prefsEditor.clear(); //use to debug the shared preferences which didn't saved my preferences before
        prefsEditor.putStringSet(KEY_URL_LIST, setString);
        prefsEditor.apply();
    }

    public void deleteLast15Url() {
        Set<String> setString = preferences.getStringSet(KEY_URL_LIST, new HashSet<String>());
        if (setString != null) {

            if(setString.size()>30){
                
            }



           //00 setString.add(url);
        }



        //  Log.d("DEBAGO", "articles supprimés dans la DAO " + setString);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        prefsEditor.clear();
        prefsEditor.putStringSet(KEY_URL_LIST, setString);
        prefsEditor.apply();
    }

}
