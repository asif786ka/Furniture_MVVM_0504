

package com.android.mvvm.furniture.repository;

import com.android.mvvm.furniture.AppExecutors;
import com.android.mvvm.furniture.api.ApiResponse;
import com.android.mvvm.furniture.api.FurnitureService;
import com.android.mvvm.furniture.db.FurnitureDao;
import com.android.mvvm.furniture.db.FurnitureDb;
import com.android.mvvm.furniture.util.Config;
import com.android.mvvm.furniture.util.RateLimiter;
import com.android.mvvm.furniture.vo.Furniture;
import com.android.mvvm.furniture.vo.FurnitureEntity;
import com.android.mvvm.furniture.vo.Media;
import com.android.mvvm.furniture.vo.Resource;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * Repository that handles FurnituresRepo instances.
 *
 * unfortunate naming :/ .
 * FurnituresRepo - value object name
 * Repository - type of this class.
 */
@Singleton
public class FurnitureRepository {

    private final FurnitureDb db;

    private final FurnitureDao furnitureDao;

    private final FurnitureService furnitureService;

    private final AppExecutors appExecutors;

    private RateLimiter<String> repoListRateLimit = new RateLimiter<>(10, TimeUnit.MINUTES);

    @Inject
    public FurnitureRepository(AppExecutors appExecutors, FurnitureDb db, FurnitureDao furnitureDao,
                               FurnitureService furnitureService) {
        this.db = db;
        this.furnitureDao = furnitureDao;
        this.furnitureService = furnitureService;
        this.appExecutors = appExecutors;
    }

    public LiveData<Resource<List<FurnitureEntity>>> loadShirts(String color, String name) {
        return new NetworkBoundResource<List<FurnitureEntity>, Furniture>(appExecutors) {
            @Override
            protected void saveCallResult(Furniture shirts) {


                List<FurnitureEntity> shirtEntities = new ArrayList<FurnitureEntity>();

                FurnitureEntity FurnitureEntity;
                    for (int i = 0; i < shirts.get_embedded().getmArticles().size(); i++) {
                        List<Media> shirt = shirts.get_embedded().getmArticles().get(i).getmList();
                        String shirtTitle = shirts.get_embedded().getmArticles().get(i).getmImageTitle();
                        FurnitureEntity = new FurnitureEntity(i, shirt.get(0).getPicture(),shirtTitle, shirt.get(0).getPicture());
                        shirtEntities.add(FurnitureEntity);
                    }


                    furnitureDao.insertFurnitures(shirtEntities);

                Timber.d("rece saved contributors to db");
            }

            @Override
            protected boolean shouldFetch(@Nullable List<FurnitureEntity> data) {
                Timber.d("rece contributor list from db: %s", data);
                return data == null || data.isEmpty();
            }


            @NonNull
            @Override
            protected LiveData<List<FurnitureEntity>> loadFromDb() {
                return furnitureDao.loadFurnitures();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Furniture>> createCall() {
                return furnitureService.loadFurnitures(Config.API_LIMIT);
            }
        }.asLiveData();
    }

    public void saveFilteredShirts(List<FurnitureEntity> shirtEntities){
        furnitureDao.insertFurnitures(shirtEntities);
    }

    public LiveData<List<FurnitureEntity>> getFilteredShirts(){
        return furnitureDao.loadFurnitures();
    }

}
