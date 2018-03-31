package com.example.sagi.imagesearch.ui.image.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sagi.imagesearch.R;
import com.example.sagi.imagesearch.data.model.ImageEntity;
import com.example.sagi.imagesearch.ui.base.BaseFragment;
import com.example.sagi.imagesearch.ui.base.ListScrollListener;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by sagiyemini on 28/03/2018.
 */

public class ImageListFragment extends BaseFragment implements ImageListMvpView, ImageListAdapter.OnImageClickedListener {

    @Inject ImageListPresenter mImageListPresenter;

    private ImageListAdapter mImageListAdapter;

    public static Fragment newInstance() {
        return new ImageListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent().inject(this);
        mImageListPresenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_list_fragment, container, false);

        if (mImageListAdapter == null) {
            mImageListAdapter = new ImageListAdapter();
        }

        mImageListAdapter.setOnImageClickedListener(this);

        RecyclerView recyclerView = view.findViewById(R.id.image_list_recycler_view);
        recyclerView.setAdapter(mImageListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.addOnScrollListener(new ListScrollListener(mImageListPresenter));

        return view;
    }

    @Override
    public void onDestroy() {
        mImageListPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void displayImages(List<ImageEntity> images) {
        mImageListAdapter.setImages(images);
    }

    @Override
    public void displayError() {
        Toast.makeText(getContext(), R.string.failed_to_get_more_results, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onImageClicked(ImageEntity imageEntity) {
        mImageListPresenter.onImageClicked(imageEntity);
    }
}
