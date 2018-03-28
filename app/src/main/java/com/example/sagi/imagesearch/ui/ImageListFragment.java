package com.example.sagi.imagesearch.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.sagi.imagesearch.ui.base.BaseFragment;

import javax.inject.Inject;

/**
 * Created by sagiyemini on 28/03/2018.
 */

public class ImageListFragment extends BaseFragment implements ImageListMvpView {

    @Inject ImageListPresenter mImageListPresenter;

    public static Fragment newInstance() {
        return new ImageListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent().inject(this);
        mImageListPresenter.attachView(this);


    }

    @Override
    public void onDestroy() {
        mImageListPresenter.detachView();
        super.onDestroy();
    }

}
