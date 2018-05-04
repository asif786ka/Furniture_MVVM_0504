package com.android.mvvm.furniture.vo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Embedded {

    public List<Articles> getmArticles() {
        return mArticles;
    }

    public void setmArticles(List<Articles> mArticles) {
        this.mArticles = mArticles;
    }

    @SerializedName("articles")
    @Expose
    private List<Articles> mArticles = null;

}
