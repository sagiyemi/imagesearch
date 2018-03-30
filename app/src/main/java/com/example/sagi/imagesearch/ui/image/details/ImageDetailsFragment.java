package com.example.sagi.imagesearch.ui.image.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sagi.imagesearch.R;
import com.example.sagi.imagesearch.data.model.ImageEntity;
import com.example.sagi.imagesearch.ui.base.BaseFragment;

import javax.inject.Inject;

/**
 * Created by sagiyemini on 30/03/2018.
 */

public class ImageDetailsFragment extends BaseFragment implements ImageDetailsMvpView {

    @Inject ImageDetailsPresenter mImageDetailsPresenter;

    private ImageView mImageView;

    public static ImageDetailsFragment newInstance() {
        return new ImageDetailsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent().inject(this);
        mImageDetailsPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        mImageDetailsPresenter.detachView();
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_details_fragment, container, false);

        mImageView = view.findViewById(R.id.image_view);

        return view;
    }

    @Override
    public void displayImage(@NonNull ImageEntity imageEntity) {
        Glide.with(this)
                .load(imageEntity.image().thumbnailLink())
                .into(mImageView);

    }
}
