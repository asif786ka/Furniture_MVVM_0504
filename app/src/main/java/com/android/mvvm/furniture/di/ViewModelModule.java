package com.android.mvvm.furniture.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.android.mvvm.furniture.ui.furnitures.FurnitureViewModel;

import com.android.mvvm.furniture.viewmodel.FurnitureMVVMViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FurnitureViewModel.class)
    abstract ViewModel bindFurnitureViewModel(FurnitureViewModel furnitureViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FurnitureMVVMViewModelFactory factory);
}
