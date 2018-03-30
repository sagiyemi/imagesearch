package com.example.sagi.imagesearch.ui.image.details;

import android.support.annotation.NonNull;

import com.example.sagi.imagesearch.data.model.ImageEntity;
import com.example.sagi.imagesearch.ui.base.MvpView;

/**
 * Created by sagiyemini on 30/03/2018.
 */

public interface ImageDetailsMvpView extends MvpView {

    void displayImage(@NonNull ImageEntity imageEntity);

}
