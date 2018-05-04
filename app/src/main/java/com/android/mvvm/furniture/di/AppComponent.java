package com.android.mvvm.furniture.di;

import com.android.mvvm.furniture.FurnitureMVVMApp;

import android.app.Application;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        MainActivityModule.class,
})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(Application application);
        AppComponent build();
    }
    void inject(FurnitureMVVMApp furnitureMVVMApp);
}
