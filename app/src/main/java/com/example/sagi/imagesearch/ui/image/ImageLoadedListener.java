package com.example.sagi.imagesearch.ui.image;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.sagi.imagesearch.util.Util;

/**
 * Created by sagiyemini on 31/03/2018.
 */

public class ImageLoadedListener implements RequestListener<Drawable> {

    private final View mProgressBar;

    public ImageLoadedListener(View progressBar) {
        this.mProgressBar = progressBar;
    }

    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
        Util.goneView(mProgressBar);
        return false;
    }

    @Override
    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
        Util.goneView(mProgressBar);
        return false;
    }
}
