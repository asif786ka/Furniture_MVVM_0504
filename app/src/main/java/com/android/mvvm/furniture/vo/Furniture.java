package com.android.mvvm.furniture.vo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Furniture {

    @SerializedName("resourceType")
    @Expose
    private String resourceType;

    @SerializedName("articlesCount")
    @Expose
    private int articlesCount;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    @SerializedName("links")
    @Expose
    private Links links;

    @SerializedName("_embedded")
    @Expose
    private Embedded _embedded;


    public Furniture(String resourceType, int articlesCount, Embedded _embedded, Links links) {
        this.resourceType = resourceType;
        this.articlesCount = articlesCount;
        this._embedded = _embedded;
        this.links = links;

    }

    public Embedded get_embedded() {
        return _embedded;
    }

    public void set_embedded(Embedded _embedded) {
        this._embedded = _embedded;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public int getArticlesCount() {
        return articlesCount;
    }

    public void setArticlesCount(int articlesCount) {
        this.articlesCount = articlesCount;
    }

}

