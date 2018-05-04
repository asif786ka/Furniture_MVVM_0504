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

import com.android.mvvm.furniture.repository.FurnitureRepository;
import com.android.mvvm.furniture.vo.Resource;
import com.android.mvvm.furniture.vo.FurnitureEntity;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(JUnit4.class)
public class FurnituresRepoViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private FurnitureRepository repository;
    private FurnitureViewModel furnitureViewModel;

    @Before
    public void setup() {
        repository = mock(FurnitureRepository.class);
        furnitureViewModel = new FurnitureViewModel(repository);
    }


    @Test
    public void testNull() {
        assertThat(furnitureViewModel.getShirts(), notNullValue());
    }

    @Test
    public void dontFetchWithoutObservers() {
        furnitureViewModel.setId("a", "b");
        verify(repository, never()).loadShirts(anyString(), anyString());
    }


    @Test
    public void shirts() {
        Observer<Resource<List<FurnitureEntity>>> observer = mock(Observer.class);
        furnitureViewModel.getShirts().observeForever(observer);
        verifyNoMoreInteractions(observer);
        verifyNoMoreInteractions(repository);
        furnitureViewModel.setId("foo", "bar");
        verify(repository).loadShirts("foo", "bar");
    }

    @Test
    public void resetId() {
        Observer<FurnitureViewModel.ShirtId> observer = mock(Observer.class);
        furnitureViewModel.shirtId.observeForever(observer);
        verifyNoMoreInteractions(observer);
        furnitureViewModel.setId("foo", "bar");
        verify(observer).onChanged(new FurnitureViewModel.ShirtId("foo", "bar"));
        reset(observer);
        furnitureViewModel.setId("foo", "bar");
        verifyNoMoreInteractions(observer);
        furnitureViewModel.setId("a", "b");
        verify(observer).onChanged(new FurnitureViewModel.ShirtId("a", "b"));
    }

}