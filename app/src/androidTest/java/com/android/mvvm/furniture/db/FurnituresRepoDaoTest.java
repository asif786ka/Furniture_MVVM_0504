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

package com.android.mvvm.furniture.db;

import com.android.mvvm.furniture.util.TestUtil;
import com.android.mvvm.furniture.vo.FurnitureEntity;

import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.runner.AndroidJUnit4;

import java.util.List;

import static com.android.mvvm.furniture.util.LiveDataTestUtil.getValue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
public class FurnituresRepoDaoTest extends DbTest {


    @Test
    public void testInsertShirts() throws InterruptedException {
        FurnitureEntity shirts = TestUtil.createShirtsEntity(1,"https://unsplash.it/128/128","Southview Clarke","brown");
        FurnitureEntity shirts2 = TestUtil.createShirtsEntity(2,"https://unsplash.it/128/128","Barronett Higgins","green");

        db.beginTransaction();
        try {
            db.furnitureDao().insert(shirts);
            db.furnitureDao().insert(shirts2);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        List<FurnitureEntity> list = getValue(db.furnitureDao().loadFurnitures());
        assertThat(list.size(), is(2));
        FurnitureEntity first = list.get(0);

        assertThat(first.getId(), is(1));
        assertThat(first.getPosterPath(), is("https://unsplash.it/128/128"));

        FurnitureEntity second = list.get(1);
        assertThat(second.getId(), is(2));
        assertThat(first.getPosterPath(), is("https://unsplash.it/128/128"));
    }

    @Test
    public void testCreateIfNotExists_exists() throws InterruptedException {
        FurnitureEntity shirts = TestUtil.createShirtsEntity(1,"https://unsplash.it/128/128","Southview Clarke","brown");;
        db.furnitureDao().insert(shirts);
        assertThat(db.furnitureDao().createFurnituresIfNotExists(shirts), is(-1L));
    }

    @Test
    public void testCreateIfNotExists_doesNotExist() {
        FurnitureEntity shirts = TestUtil.createShirtsEntity(2,"https://unsplash.it/128/128","Barronett Higgins","green");

        assertThat(db.furnitureDao().createFurnituresIfNotExists(shirts), is(1L));
    }


}
