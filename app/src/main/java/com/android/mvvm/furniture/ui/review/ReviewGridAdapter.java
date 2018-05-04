package com.android.mvvm.furniture.ui.review;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.mvvm.furniture.R;
import com.android.mvvm.furniture.vo.Review;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ReviewGridAdapter extends RecyclerView.Adapter<ReviewGridAdapter.ViewHolder> {

    private ArrayList<Review> mData;
    private LayoutInflater mInflater;
    private Context mContext;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    ReviewGridAdapter(Context context, ArrayList<Review> reviewList) {
        this.mContext =  context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = reviewList;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.grid_review_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String reviewPoster = mData.get(position).getmPoster();
        Glide.with(mContext).load(reviewPoster).into(holder.mImageView);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView;

        ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.info_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }


    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
