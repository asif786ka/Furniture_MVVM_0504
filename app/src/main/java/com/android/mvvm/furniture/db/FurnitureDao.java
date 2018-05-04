package com.android.mvvm.furniture.db;

import com.android.mvvm.furniture.vo.FurnitureEntity;
import com.android.mvvm.furniture.vo.FurnituresRepo;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Interface for database access on FurnituresRepo related operations.
 */
@Dao
public abstract class FurnitureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(FurnitureEntity... shirtses);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertFurnitures(List<FurnitureEntity> furnitures);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long createFurnituresIfNotExists(FurnitureEntity furnitures);

    @Query("SELECT * FROM furnitures")
    public abstract LiveData<List<FurnitureEntity>> loadFurnitures();

    @Query("SELECT * FROM FurnituresRepo WHERE id in (:repoIds)")
    protected abstract LiveData<List<FurnituresRepo>> loadById(List<Integer> repoIds);

}
