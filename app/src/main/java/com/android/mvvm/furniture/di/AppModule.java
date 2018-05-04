/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.mvvm.furniture.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.android.mvvm.furniture.api.FurnitureService;
import com.android.mvvm.furniture.db.FurnitureDao;
import com.android.mvvm.furniture.db.FurnitureDb;
import com.android.mvvm.furniture.util.FurnitureSharedPreferences;
import com.android.mvvm.furniture.util.LiveDataCallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
class AppModule {
    @Singleton @Provides
    FurnitureService provideShirtService() {
        return new Retrofit.Builder()
                .baseUrl("https://api-mobile.home24.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(FurnitureService.class);
    }

    @Singleton @Provides
    FurnitureDb provideDb(Application app) {
        return Room.databaseBuilder(app, FurnitureDb.class,"shirts.db").build();
    }


    @Singleton @Provides
    FurnitureDao provideShirtsDao(FurnitureDb db) {
        return db.furnitureDao();
    }

}
