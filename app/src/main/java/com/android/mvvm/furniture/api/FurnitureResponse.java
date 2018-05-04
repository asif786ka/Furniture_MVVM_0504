

package com.android.mvvm.furniture.api;


import com.android.mvvm.furniture.vo.Furniture;
import com.google.gson.annotations.SerializedName;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class FurnitureResponse {
    @SerializedName("total_count")
    private int total;
    @SerializedName("items")
    private List<Furniture> items;
    private Integer nextPage;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Furniture> getItems() {
        return items;
    }

    public void setItems(List<Furniture> items) {
        this.items = items;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    @NonNull
    public List<Integer> getRepoIds() {
        List<Integer> repoIds = new ArrayList<>();
        for (Furniture shirts : items) {
            //repoIds.add(shirts.getId());
        }
        return repoIds;
    }
}
