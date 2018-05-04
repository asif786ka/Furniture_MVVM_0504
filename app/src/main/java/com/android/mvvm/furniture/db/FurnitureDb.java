package com.android.mvvm.furniture.db;


import com.android.mvvm.furniture.vo.FurnituresRepo;
import com.android.mvvm.furniture.vo.FurnitureEntity;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Main database description.
 */
@Database(entities = {FurnituresRepo.class,
        FurnitureEntity.class}, version = 3)
public abstract class FurnitureDb extends RoomDatabase {

    abstract public FurnitureDao furnitureDao();
}
