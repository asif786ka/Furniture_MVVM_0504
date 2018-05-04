package com.android.mvvm.furniture.ui.review;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.mvvm.furniture.R;
import com.android.mvvm.furniture.vo.Review;

import java.util.ArrayList;

/* Fragment used as page 2 */
public class ListFragment extends Fragment {

    private ReviewListAdapter adapter;
    private ArrayList<Review> reviewList;
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static ListFragment newInstance(ArrayList<Review> reviewList) {

        Bundle args = new Bundle();
        args.putParcelableArrayList("REVIEW", reviewList);
        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_grid, container, false);

        // set up the RecyclerView
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rvNumbers);

        Bundle bundle = getArguments();

        try {
            reviewList =  getArguments().getParcelableArrayList("REVIEW");
        } catch (Exception e) {
            e.printStackTrace();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ReviewListAdapter(getContext(), reviewList);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onPause() {
        super.onPause();
        reviewList.clear();
    }

    @Override
    public void onStop() {
        super.onStop();
        reviewList.clear();
    }

}
