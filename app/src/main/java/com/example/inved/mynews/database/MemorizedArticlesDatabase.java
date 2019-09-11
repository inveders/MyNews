package com.example.inved.mynews.database;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {MemorizedArticles.class}, version = 1, exportSchema = false)
public abstract class MemorizedArticlesDatabase extends RoomDatabase {

    // --- SINGLETON ---
    private static MemorizedArticlesDatabase INSTANCE; //there was volatile before static

    // --- DB NAME ---
    private static final String DB_NAME = "myDatabase.db";

    // --- DAO ---
    public abstract MemorizedArticlesDao memorizedArticlesDao();

    // --- INSTANCE ---
    public static MemorizedArticlesDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (MemorizedArticlesDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MemorizedArticlesDatabase.class, DB_NAME)
                            .allowMainThreadQueries() // SHOULD NOT BE USED IN PRODUCTION !!!
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
                contentValues.put("url", "https://www.nytimes.com/2019/09/10/world/europe/uk-brexit-parliament.html?action=click&module=Top%20Stories&pgtype=Homepage");

                db.insert("memorized_articles", OnConflictStrategy.IGNORE, contentValues);
            }
        };
    }

}
