package com.example.sagi.imagesearch.ui.imagelist;

import com.example.sagi.imagesearch.data.model.ImageEntity;
import com.example.sagi.imagesearch.ui.base.MvpView;

import java.util.List;

/**
 * Created by sagiyemini on 28/03/2018.
 */

public interface ImageListMvpView extends MvpView {

    void displayImages(List<ImageEntity> images);

}
