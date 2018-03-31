package com.example.sagi.imagesearch.ui.image.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sagi.imagesearch.R;
import com.example.sagi.imagesearch.data.model.ImageEntity;
import com.example.sagi.imagesearch.ui.image.ImageLoadedListener;
import com.example.sagi.imagesearch.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagiyemini on 28/03/2018.
 */

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageViewHolder> {

    public interface OnImageClickedListener {
        void onImageClicked(ImageEntity imageEntity);
    }

    private List<ImageEntity> mItems;
    private OnImageClickedListener mListener;

    public ImageListAdapter() {
        mItems = new ArrayList<>();
    }

    public void setImages(List<ImageEntity> images) {
        mItems = images;
        notifyDataSetChanged();
        // TODO: Change notifyDataSetChanged
    }

    public void setOnImageClickedListener(OnImageClickedListener listener) {
        mListener = listener;
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

        holder.itemView.setOnClickListener(view -> {
            if (mListener != null) {
                mListener.onImageClicked(imageEntity);
            }
        });

        Context context = holder.itemView.getContext();
        final View progressBar = holder.mProgressBar;

        holder.mImageTitle.setText(imageEntity.title());

        // Set thumbnail height to avoid jumping UI
        int thumbnailHeightPixelSize = Util.convertDpToPixel(imageEntity.image().thumbnailHeight(), context);

        ViewGroup.LayoutParams layoutParams = holder.mImageView.getLayoutParams();
        layoutParams.height = thumbnailHeightPixelSize;
        holder.mImageView.setLayoutParams(layoutParams);

        Util.showView(progressBar);
        // Display thumbnail for speed, full quality image will be displayed in a dedicated screen
        Glide.with(context)
                .load(imageEntity.image().thumbnailLink())
                .listener(new ImageLoadedListener(progressBar))
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        TextView mImageTitle;
        ImageView mImageView;
        View mProgressBar;

        public ImageViewHolder(View itemView) {
            super(itemView);
            mImageTitle = itemView.findViewById(R.id.image_title);
            mImageView = itemView.findViewById(R.id.image_image_view);
            mProgressBar = itemView.findViewById(R.id.image_progress_bar);
        }
    }
}
