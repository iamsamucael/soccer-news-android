package com.samucael.soccernews.domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class News {

    @PrimaryKey
    public Integer id;
    public String tittle;
    public String body;
    public String image;
    public String link;
    public boolean favorite;


}
