package com.android.mvvm.furniture.ui.review;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.android.mvvm.furniture.R;
import com.android.mvvm.furniture.vo.Review;

import java.util.ArrayList;


public class ReviewActivity extends AppCompatActivity {

    private final String LOG_TAG = ReviewActivity.class.getSimpleName();

    // Titles of the individual pages (displayed in tabs)
    private final String[] PAGE_TITLES = new String[] {
            "GRID",
            "LIST",
    };

    // The fragments that are used as the individual pages

    private Fragment[] PAGES;

    public Fragment[] createPager(ArrayList<Review> reviewList) {
        final Fragment[] PAGES = new Fragment[] {
                GridFragment.newInstance(reviewList),
                ListFragment.newInstance(reviewList),
        };
        return PAGES;
    }


    // The ViewPager is responsible for sliding pages (fragments) in and out upon user input
    private ViewPager mViewPager;

    private ArrayList<Review> mReviewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_main);

        mReviewList = getExtrasFromIntent();

        PAGES = createPager(getExtrasFromIntent());
        // Connect the ViewPager to our custom PagerAdapter. The PagerAdapter supplies the pages
        // (fragments) to the ViewPager, which the ViewPager needs to display.
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new ReviewPagerAdapter(getSupportFragmentManager()));

        // Connect the tabs with the ViewPager (the setupWithViewPager method does this for us in
        // both directions, i.e. when a new tab is selected, the ViewPager switches to this page,
        // and when the ViewPager switches to a new page, the corresponding tab is selected)
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);

        getExtrasFromIntent();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mReviewList.clear();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    /* PagerAdapter for supplying the ViewPager with the pages (fragments) to display. */
    public class ReviewPagerAdapter extends FragmentPagerAdapter {

        public ReviewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return PAGES[position];
        }

        @Override
        public int getCount() {
            return PAGES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return PAGE_TITLES[position];
        }

    }

    public static Intent launchDetail(Context context, ArrayList<Review> reviewList) {
        Intent intent = new Intent(context, ReviewActivity.class);
        intent.putParcelableArrayListExtra("REVIEWLIST", reviewList);
        return intent;
    }

    private ArrayList<Review> getExtrasFromIntent() {
        ArrayList<Review> reviewList = getIntent().getParcelableArrayListExtra("REVIEWLIST");
        return reviewList;
    }
}
