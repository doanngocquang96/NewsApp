package com.example.appnews;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "News") // bảng batabase News
public class News {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int save;

    private String title;
    private String link;
    private String thumbnail;
    private String info;
    private String date;

//    public News() {
//    }

    public News(String title, String link, String thumbnail, String info, String date) {
        this.title = title;
        this.link = link;
        this.thumbnail = thumbnail;
        this.info = info;
        this.date = date;

        this.save = 0;
    }

    public int getSave() {
        return save;
    }

    public void setSave(int save) {
        this.save = save;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
