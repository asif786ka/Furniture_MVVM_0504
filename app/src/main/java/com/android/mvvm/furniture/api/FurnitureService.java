package com.android.mvvm.furniture.api;

import com.android.mvvm.furniture.vo.Furniture;

import android.arch.lifecycle.LiveData;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * REST API access points
 */
public interface FurnitureService {

    @GET("articles?appDomain=1&locale=de_DE?")
    LiveData<ApiResponse<Furniture>> loadFurnitures(@Query("limit") int limit);

}
