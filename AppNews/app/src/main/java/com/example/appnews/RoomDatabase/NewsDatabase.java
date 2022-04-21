package com.example.appnews.RoomDatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.appnews.News;

@Database(entities = {News.class}, version = 2)
public abstract class NewsDatabase extends RoomDatabase {
    // update database v1 to v2
    static Migration migration_from1to2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE News ADD COLUMN save TEXT");
        }
    };

    private static final String DATABASE_NAME = "news.db";
    private static NewsDatabase instance;

    public static synchronized NewsDatabase getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), NewsDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .addMigrations(migration_from1to2) //migrate
                    .build();
        }
        return instance;
    }

    public abstract NewsDAO newsDAO ();
}
