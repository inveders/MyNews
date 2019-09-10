package com.example.inved.mynews.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.HashSet;
import java.util.Set;

@Database(entities = {MemorizedArticles.class}, version = 1, exportSchema = false)
public abstract class MemorizedArticlesDatabase extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile MemorizedArticlesDatabase INSTANCE;

    // --- DAO ---
    public abstract MemorizedArticlesDao memorizedArticlesDao();

    // --- INSTANCE ---
    public static MemorizedArticlesDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (MemorizedArticlesDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MemorizedArticlesDatabase.class, "MyDatabase.db")
                            .addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // ---

    private static Callback prepopulateDatabase(){
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                ContentValues contentValues = new ContentValues();
                contentValues.put("id", 1);
                contentValues.put("url", "https://www.nytimes.com/2019/09/10/world/europe/uk-brexit-parliament.html?action=click&module=Top%20Stories&pgtype=Homepage");

                db.insert("MemorizedArticles", OnConflictStrategy.IGNORE, contentValues);
            }
        };
    }

}
