package com.example.sagi.imagesearch.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sagi.imagesearch.R;
import com.example.sagi.imagesearch.data.model.ImageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagiyemini on 28/03/2018.
 */

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageViewHolder> {

    private List<ImageEntity> mItems;

    public ImageListAdapter() {
        mItems = new ArrayList<>();
    }

    public void setImages(List<ImageEntity> images) {
        mItems = images;
        notifyDataSetChanged();
        // TODO: Change notifyDataSetChanged
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ImageViewHolder(inflater.inflate(R.layout.image_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        ImageEntity imageEntity = mItems.get(position);
        holder.mImageTitle.setText(imageEntity.title());
        // TODO: Bind image
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        TextView mImageTitle;
        ImageView mImageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            mImageTitle = itemView.findViewById(R.id.image_title);
            mImageView = itemView.findViewById(R.id.image_image_view);
        }
    }
}
