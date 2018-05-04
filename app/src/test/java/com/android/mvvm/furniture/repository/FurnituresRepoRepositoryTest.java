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

package com.android.mvvm.furniture.repository;

import com.android.mvvm.furniture.api.FurnitureService;
import com.android.mvvm.furniture.db.FurnitureDao;
import com.android.mvvm.furniture.db.FurnitureDb;
import com.android.mvvm.furniture.util.InstantAppExecutors;
import com.android.mvvm.furniture.vo.FurnitureEntity;
import com.android.mvvm.furniture.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
@RunWith(JUnit4.class)
public class FurnituresRepoRepositoryTest {
    private FurnitureRepository repository;
    private FurnitureDao dao;
    private FurnitureService service;
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
    @Before
    public void init() {
        dao = mock(FurnitureDao.class);
        service = mock(FurnitureService.class);
        FurnitureDb db = mock(FurnitureDb.class);
        when(db.furnitureDao()).thenReturn(dao);
        repository = new FurnitureRepository(new InstantAppExecutors(), db, dao, service);
    }

    @Test
    public void loadShirtsFromNetwork() throws IOException {
       MutableLiveData<List<FurnitureEntity>> dbData = new MutableLiveData<>();
        when(dao.loadFurnitures()).thenReturn(dbData);

        /*Furniture furniture1 = TestUtil.createShirts(1,"TEST2a","TEST2a",24);
        Furniture furniture2 = TestUtil.createShirts(2,"TEST2b","TEST2b",25);
        List<Furniture> furnitures = new ArrayList<Furniture>();
        furnitures.add(furniture1);
        furnitures.add(furniture2);

        List<FurnitureEntity> shirtsEntities = new ArrayList<FurnitureEntity>();
        FurnitureEntity shirtEntity;
        for(int i = 0; i < furnitures.size(); i++ )
        {
            shirtEntity = new FurnitureEntity(i, furnitures.get(i).get_embedded().getmArticles().get(i).getmImageTitle(), furnitures.get(i).get_embedded().getmArticles().get(i).getmList().get(i).getPicture(), furnitures.get(i).get_embedded().getmArticles().get(i).getmList().get(i).getPicture());
            shirtsEntities.add(shirtEntity);
        }*/

        //LiveData<ApiResponse<Furniture>> call = successCall(furnitures);
        //when(service.loadFurnitures()).thenReturn(call);

        LiveData<Resource<List<FurnitureEntity>>> data = repository.loadShirts("test1","test2");
        verify(dao).loadFurnitures();
        verifyNoMoreInteractions(service);

        Observer observer = mock(Observer.class);
        data.observeForever(observer);
        verifyNoMoreInteractions(service);
        verify(observer).onChanged(Resource.loading(null));
        MutableLiveData<List<FurnitureEntity>> updatedDbData = new MutableLiveData<>();
        when(dao.loadFurnitures()).thenReturn(updatedDbData);

        dbData.postValue(null);
        verify(service).loadFurnitures();
        //verify(dao).insertFurnitures(furnitures);

        //updatedDbData.postValue(shirtsEntities);
        //verify(observer).onChanged(Resource.success(shirtsEntities));
    }

    @Test
    public void loadShirts() throws IOException {
        MutableLiveData<List<FurnitureEntity>> dbData = new MutableLiveData<>();
        when(dao.loadFurnitures()).thenReturn(dbData);

        LiveData<Resource<List<FurnitureEntity>>> data = repository.loadShirts("foo",
                "bar");
        verify(dao).loadFurnitures();

        Observer<Resource<List<FurnitureEntity>>> observer = mock(Observer.class);
        data.observeForever(observer);

        verify(observer).onChanged(Resource.loading( null));

        MutableLiveData<List<FurnitureEntity>> updatedDbData = new MutableLiveData<>();
        when(dao.loadFurnitures()).thenReturn(updatedDbData);
        dbData.setValue(Collections.emptyList());

        verify(service).loadFurnitures().getValue().body.get_embedded().getmArticles().get(0).setmImageTitle("Test");
        ArgumentCaptor<List<FurnitureEntity>> inserted = ArgumentCaptor.forClass((Class) List.class);
        verify(dao).insertFurnitures(inserted.capture());


        assertThat(inserted.getValue().size(), is(1));
        FurnitureEntity first = inserted.getValue().get(0);
        assertThat(first.getTitle(), is("Test"));

    }

}