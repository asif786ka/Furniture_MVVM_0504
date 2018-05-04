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

package com.android.mvvm.furniture.util;

import com.android.mvvm.furniture.vo.FurnitureEntity;

public class TestUtil {

    /*public static Furniture createShirts(String owner, String name, String price) {
        return createShirts(FurnituresRepo.UNKNOWN_ID, owner, name, price);
    }*/



    /*public static Furniture createShirts(int id, String owner, String name, String price) {
        return new Furniture(id,owner,name,price);
    }*/

    public static FurnitureEntity createShirtsEntity(int id, String posterPath, String title, String overview) {
        return new FurnitureEntity(id,posterPath,title,overview);
    }

}
