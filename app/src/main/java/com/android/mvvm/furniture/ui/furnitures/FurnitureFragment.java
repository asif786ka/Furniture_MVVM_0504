package com.android.mvvm.furniture.ui.furnitures;

import com.android.mvvm.furniture.R;
import com.android.mvvm.furniture.binding.FragmentDataBindingComponent;
import com.android.mvvm.furniture.databinding.ShirtsFragmentBinding;
import com.android.mvvm.furniture.di.Injectable;
import com.android.mvvm.furniture.ui.common.NavigationController;
import com.android.mvvm.furniture.ui.detail.SelectionDetailActivity;
import com.android.mvvm.furniture.ui.review.ReviewActivity;
import com.android.mvvm.furniture.util.AutoClearedValue;
import com.android.mvvm.furniture.vo.FurnitureEntity;
import com.android.mvvm.furniture.vo.FurnitureItem;
import com.android.mvvm.furniture.vo.Review;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import javax.inject.Inject;

/**
 * The UI Controller for displaying a FurnituresRepo's information with its shirts.
 */
public class FurnitureFragment extends Fragment implements LifecycleRegistryOwner, Injectable, FurnitureAdapter.ShirtClickCallback {

    private static final String FURNITURE_COLOR_KEY = "furniture_color";

    private static final String FURNITURE_NAME_KEY = "furniture_name";

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private FurnitureViewModel furnitureViewModel;

    @Inject
    NavigationController navigationController;

    DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);
    AutoClearedValue<ShirtsFragmentBinding> binding;
    AutoClearedValue<FurnitureAdapter> adapter;

    ArrayList<Review> reviewList = new ArrayList<Review>();

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        furnitureViewModel = ViewModelProviders.of(this, viewModelFactory).get(FurnitureViewModel.class);
        Bundle args = getArguments();
        if (args != null && args.containsKey(FURNITURE_COLOR_KEY) &&
                args.containsKey(FURNITURE_NAME_KEY)) {
            furnitureViewModel.setId(args.getString(FURNITURE_COLOR_KEY),
                    args.getString(FURNITURE_NAME_KEY));
        } else {
            furnitureViewModel.setId(null, null);
        }

        FurnitureAdapter adapter = new FurnitureAdapter(dataBindingComponent,
                this);
        this.adapter = new AutoClearedValue<>(this, adapter);
        binding.get().contributorList.setAdapter(adapter);
        initShirtsList(furnitureViewModel);
    }

    private void initShirtsList(FurnitureViewModel viewModel) {
        viewModel.getShirts().observe(this, listResource -> {
            // we don't need any null checks here for the adapter since LiveData guarantees that
            // it won't call us if fragment is stopped or not started.
            if (listResource != null && listResource.data != null) {
                adapter.get().replace(listResource.data);
            } else {
                //noinspection ConstantConditions
                adapter.get().replace(Collections.emptyList());
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ShirtsFragmentBinding dataBinding = DataBindingUtil
                .inflate(inflater, R.layout.shirts_fragment, container, false);
        dataBinding.setRetryCallback(() -> furnitureViewModel.retry());
        binding = new AutoClearedValue<>(this, dataBinding);
        binding.get().counterReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < furnitureViewModel.getShirts().getValue().data.size(); i++ ){
                    Review review = new Review(furnitureViewModel.getShirts().getValue().data.get(i).getPosterPath(), furnitureViewModel.getShirts().getValue().data.get(i).getTitle());
                    reviewList.add(review);
                }
                getActivity().startActivity(ReviewActivity.launchDetail(getContext(),reviewList));
            }
        });
        return dataBinding.getRoot();
    }

    public static FurnitureFragment create(String owner, String name) {
        FurnitureFragment shirtFragment = new FurnitureFragment();
        Bundle args = new Bundle();
        args.putString(FURNITURE_COLOR_KEY, owner);
        args.putString(FURNITURE_NAME_KEY, name);
        shirtFragment.setArguments(args);
        return shirtFragment;
    }

    @Override
    public void onClick(FurnitureEntity shirt) {
        FurnitureItem furnitureItem = new FurnitureItem();
        furnitureItem.setFullName(shirt.getTitle());
        furnitureItem.setGender(shirt.getPosterPath());
        getActivity().startActivity(SelectionDetailActivity.launchDetail(getContext(), furnitureItem));
        Toast.makeText(getContext(),"Toast Clicked",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(int position, int count) {
        binding.get().contributorList.scrollToPosition(position+1);
        binding.get().counterReview.setCount(count);
    }
}