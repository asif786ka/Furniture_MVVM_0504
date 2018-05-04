package com.android.mvvm.furniture.vo;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class LinkArticles {

    public List<Href> getMlist() {
        return mlist;
    }

    public void setMlist(List<Href> mlist) {
        this.mlist = mlist;
    }

    List<Href> mlist;


}
