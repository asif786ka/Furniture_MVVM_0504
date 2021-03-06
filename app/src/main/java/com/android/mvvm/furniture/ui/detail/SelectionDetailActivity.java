/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.mvvm.furniture.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.android.mvvm.furniture.R;
import com.android.mvvm.furniture.databinding.SelectionDetailActivityBinding;
import com.android.mvvm.furniture.vo.FurnitureItem;


public class SelectionDetailActivity extends AppCompatActivity {

  private static final String EXTRA_PEOPLE = "EXTRA_PEOPLE";

  private SelectionDetailActivityBinding peopleDetailActivityBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    peopleDetailActivityBinding =
        DataBindingUtil.setContentView(this, R.layout.selection_detail_activity);
    //setSupportActionBar(peopleDetailActivityBinding.toolbar);
    displayHomeAsUpEnabled();
    getExtrasFromIntent();
  }

  public static Intent launchDetail(Context context, FurnitureItem furnitureItem) {
    Intent intent = new Intent(context, SelectionDetailActivity.class);
    intent.putExtra(EXTRA_PEOPLE, furnitureItem);
    return intent;
  }

  private void displayHomeAsUpEnabled() {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

  private void getExtrasFromIntent() {
    FurnitureItem furnitureItem = (FurnitureItem) getIntent().getSerializableExtra(EXTRA_PEOPLE);
    SelectionDetailViewModel selectionDetailViewModel = new SelectionDetailViewModel(furnitureItem);
    peopleDetailActivityBinding.setSelectionDetailViewModel(selectionDetailViewModel);
    setTitle(furnitureItem.fullName);
  }
}
