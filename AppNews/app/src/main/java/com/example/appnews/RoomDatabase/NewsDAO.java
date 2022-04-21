package com.example.appnews.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.appnews.News;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NewsDAO {
    // them vao database
    @Insert
    void insertNews(News news);

    // lay ra toan bo db
    @Query("SELECT * FROM News")
    List<News> getListNews();

    // xoa toan bo db
    @Query("DELETE FROM News")
    void deleteAllDatabase();

    // reset primary key ve bang 1
    @Query("UPDATE sqlite_sequence SET seq = 1 WHERE name = 'News'")
    void clearPrimaryKey();

    @Query("SELECT * FROM News ORDER BY id DESC LIMIT 1")
    News getLastNews();

    // update mot hang (News) db
    @Update
    void updateNewsDatabase(News news);

    // lay ra toan bo News co save = 1
    @Query("SELECT * FROM News WHERE save = 1")
    List<News> getSavedNews();

    // lay ra News co id = id da cho
    @Query("SELECT * FROM News WHERE id = :newsId")
    News getNewsById(int newsId);

    // dem tong so dong db (tong so news)
    @Query("SELECT COUNT(id) FROM News")
    int getCount();

}
