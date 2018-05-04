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

package com.android.mvvm.furniture.ui.furnitures;

import com.android.mvvm.furniture.R;
import com.android.mvvm.furniture.databinding.ShirtItemBinding;
import com.android.mvvm.furniture.ui.common.DataBoundListAdapter;
import com.android.mvvm.furniture.util.Objects;
import com.android.mvvm.furniture.vo.FurnitureEntity;
import com.android.mvvm.furniture.vo.FurnitureItem;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FurnitureAdapter
        extends DataBoundListAdapter<FurnitureEntity, ShirtItemBinding> {

    private final DataBindingComponent dataBindingComponent;
    private final ShirtClickCallback callback;
    private int count = 0;

    public FurnitureAdapter(DataBindingComponent dataBindingComponent,
                            ShirtClickCallback callback) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
    }

    @Override
    protected ShirtItemBinding createBinding(ViewGroup parent) {
        ShirtItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.shirt_item, parent, false,
                        dataBindingComponent);
        binding.getRoot().setOnClickListener(v -> {
            FurnitureEntity furniture = binding.getFurnitureEntity();

            FurnitureItem furnitureItem = new FurnitureItem();
            furnitureItem.setFullName(furniture.getTitle());
            //furnitureItem.picture.thumbnail = shirt.getPosterPath();
            furnitureItem.mail = furniture.getTitle();
            if (furniture != null && callback != null) {
                callback.onClick(furniture);
            }
        });
        binding.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FurnitureEntity shirt = binding.getFurnitureEntity();
                count++;
                callback.onClick(shirt.getId(), count);
            }
        });
        binding.dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FurnitureEntity shirt = binding.getFurnitureEntity();
                count--;
                callback.onClick(shirt.getId(), count);
            }
        });
        return binding;
    }

    @Override
    protected void bind(ShirtItemBinding binding, FurnitureEntity item) {
        binding.setFurnitureEntity(item);
    }

    @Override
    protected boolean areItemsTheSame(FurnitureEntity oldItem, FurnitureEntity newItem) {
        return Objects.equals(oldItem.getTitle(), newItem.getTitle());
    }

    @Override
    protected boolean areContentsTheSame(FurnitureEntity oldItem, FurnitureEntity newItem) {
        return Objects.equals(oldItem.getOriginalTitle(), newItem.getOriginalTitle())
                && oldItem.getTitle() == newItem.getTitle();
    }

    public interface ShirtClickCallback {
        void onClick(FurnitureEntity contributor);
        void onClick(int position, int count);
    }
}
