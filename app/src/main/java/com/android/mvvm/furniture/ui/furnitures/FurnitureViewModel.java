package com.android.mvvm.furniture.ui.furnitures;

import com.android.mvvm.furniture.repository.FurnitureRepository;
import com.android.mvvm.furniture.util.AbsentLiveData;
import com.android.mvvm.furniture.util.Objects;
import com.android.mvvm.furniture.vo.FurnitureEntity;
import com.android.mvvm.furniture.vo.FurnituresRepo;
import com.android.mvvm.furniture.vo.Resource;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;

import java.util.List;

import javax.inject.Inject;

public class FurnitureViewModel extends ViewModel {
    @VisibleForTesting
    final MutableLiveData<ShirtId> shirtId;
    private final LiveData<Resource<FurnituresRepo>> repo =null;
    private final LiveData<Resource<List<FurnitureEntity>>> shirts;

    private FurnitureRepository repository;

    @Inject
    public FurnitureViewModel(FurnitureRepository repository) {

        this.repository = repository;

        this.shirtId = new MutableLiveData<>();

        shirts = Transformations.switchMap(shirtId, input -> {
            if (input.isEmpty()) {
                return AbsentLiveData.create();
            } else {
                return repository.loadShirts(input.shirtColor, input.name);
            }

        });

    }

    public LiveData<Resource<FurnituresRepo>> getRepo() {
        return repo;
    }

    public LiveData<Resource<List<FurnitureEntity>>> getShirts() {
        return shirts;
    }

    public void retry() {
        ShirtId current = shirtId.getValue();
        if (current != null && !current.isEmpty()) {
            shirtId.setValue(current);
        }
    }

    void setId(String color, String name) {
        ShirtId update = new ShirtId(color, name);
        if (Objects.equals(shirtId.getValue(), update)) {
            return;
        }
        shirtId.setValue(update);
    }

    @VisibleForTesting
    static class ShirtId {
        public final String shirtColor;
        public final String name;

        ShirtId(String shirtColor, String name) {
            this.shirtColor = shirtColor == null ? null : shirtColor.trim();
            this.name = name == null ? null : name.trim();
        }

        boolean isEmpty() {
            return shirtColor == null || name == null || shirtColor.length() == 0 || name.length() == 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            ShirtId shirtId = (ShirtId) o;

            if (shirtColor != null ? !shirtColor.equals(shirtId.shirtColor) : shirtId.shirtColor != null) {
                return false;
            }
            return name != null ? name.equals(shirtId.name) : shirtId.name == null;
        }

        @Override
        public int hashCode() {
            int result = shirtColor != null ? shirtColor.hashCode() : 0;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }
    }

    public void saveFilteredShirts(List<FurnitureEntity> shirtEntities){
        repository.saveFilteredShirts(shirtEntities);
    }

    public LiveData<List<FurnitureEntity>> getFilteredShirts(){
        return repository.getFilteredShirts();
    }
}
