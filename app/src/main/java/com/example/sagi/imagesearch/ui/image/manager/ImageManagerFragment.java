package com.example.sagi.imagesearch.ui.image.manager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sagi.imagesearch.R;
import com.example.sagi.imagesearch.ui.base.BackPressedHandler;
import com.example.sagi.imagesearch.ui.base.BaseFragment;
import com.example.sagi.imagesearch.ui.image.details.ImageDetailsFragment;
import com.example.sagi.imagesearch.ui.image.list.ImageListFragment;

import javax.inject.Inject;

/**
 * Created by sagiyemini on 30/03/2018.
 */

public class ImageManagerFragment extends BaseFragment implements ImageManagerMvpView, BackPressedHandler {

    @Inject ImageManagerPresenter mImageManagerPresenter;

    public static ImageManagerFragment newInstance() {
        return new ImageManagerFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent().inject(this);
        mImageManagerPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        mImageManagerPresenter.detachView();
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.image_manager_fragment, container, false);
    }

    @Override
    public void displayImageList() {
        // Display image list fragment
        if (getChildFragmentManager().findFragmentById(R.id.image_list_container) == null) {
            getChildFragmentManager().beginTransaction()
                    .add(R.id.image_list_container, ImageListFragment.newInstance())
                    .commit();
        }

        // Remove image details fragment
        Fragment imageDetailsFragment = getChildFragmentManager().findFragmentById(R.id.image_details_container);
        if (imageDetailsFragment != null) {
            getChildFragmentManager().beginTransaction()
                    .remove(imageDetailsFragment)
                    .commit();
        }
    }

    @Override
    public void displayImageDetails() {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.image_details_container, ImageDetailsFragment.newInstance())
                .commit();
    }

    @Override
    public boolean onBackPressed() {
        return mImageManagerPresenter.onBackPressed();
    }
}
