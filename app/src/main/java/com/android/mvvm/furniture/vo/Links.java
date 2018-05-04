package com.android.mvvm.furniture.vo;

import com.google.gson.annotations.SerializedName;


public class Links {

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    @SerializedName("self")
    private Self self;

    public Articles getArticles() {
        return articles;
    }

    public void setArticles(Articles articles) {
        this.articles = articles;
    }

    @SerializedName("articles")
    private Articles articles;

    public Next getNext() {
        return next;
    }

    public void setNext(Next next) {
        this.next = next;
    }

    @SerializedName("next")
    private Next next;


}
