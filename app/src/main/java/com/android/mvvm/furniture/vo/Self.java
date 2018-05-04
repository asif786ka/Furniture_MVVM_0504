package com.android.mvvm.furniture.vo;

import com.google.gson.annotations.SerializedName;


public class Self {

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @SerializedName("href")
    private String href;


}
