package com.android.mvvm.furniture.ui.common;

import com.android.mvvm.furniture.MainActivity;
import com.android.mvvm.furniture.R;
import com.android.mvvm.furniture.ui.SplashFragment;
import com.android.mvvm.furniture.ui.furnitures.FurnitureFragment;
import com.android.mvvm.furniture.ui.furnitures.FurnitureFragment;


import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

/**
 * A utility class that handles navigation in {@link MainActivity}.
 */
public class NavigationController {
    private final int containerId;
    private final FragmentManager fragmentManager;

    @Inject
    public NavigationController(MainActivity mainActivity) {
        this.containerId = R.id.container;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }


    public void navigateToFurniture(String color, String name) {
        FurnitureFragment fragment = FurnitureFragment.create(color, name);
        String tag = "furniture" + "/" + color + "/" + name;
        fragmentManager.beginTransaction()
                .replace(containerId, fragment, tag)
                .commitAllowingStateLoss();
    }

    public void navigateToSplash() {
        SplashFragment fragment = new SplashFragment();
        String tag = "splash";
        fragmentManager.beginTransaction()
                .replace(containerId, fragment, tag)
                .commitAllowingStateLoss();
    }
}
