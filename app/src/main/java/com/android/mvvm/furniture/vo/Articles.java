package com.android.mvvm.furniture.vo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Articles {

    public List<Media> getmList() {
        return mList;
    }

    public void setmList(List<Media> mList) {
        this.mList = mList;
    }

    @SerializedName("media")
    @Expose
    private List<Media> mList;

    public String getmImageTitle() {
        return mImageTitle;
    }

    public void setmImageTitle(String mImageTitle) {
        this.mImageTitle = mImageTitle;
    }

    @SerializedName("title")
    @Expose
    private String mImageTitle;

}
