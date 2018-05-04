package com.android.mvvm.furniture.vo;

import com.google.gson.annotations.SerializedName;


public class Next {

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @SerializedName("href")
    private String href;


}
