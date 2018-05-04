package com.android.mvvm.furniture.ui;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.mvvm.furniture.R;
import com.android.mvvm.furniture.di.Injectable;
import com.android.mvvm.furniture.ui.common.NavigationController;
import com.android.mvvm.furniture.ui.review.ReviewListAdapter;
import com.android.mvvm.furniture.vo.Review;

import java.util.ArrayList;

import javax.inject.Inject;

/* Fragment used as page 2 */
public class SplashFragment extends Fragment implements LifecycleRegistryOwner, Injectable {

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Inject
    NavigationController navigationController;

    private Button splashButton;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.splash_activity, container, false);

        // set up the RecyclerView
        splashButton = (Button) rootView.findViewById(R.id.button);

        splashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationController.navigateToFurniture("LineageOS","android");
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
