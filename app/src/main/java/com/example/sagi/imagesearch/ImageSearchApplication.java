package com.example.sagi.imagesearch;

import android.app.Application;
import android.content.Context;

/**
 * Created by sagiyemini on 28/03/2018.
 */

public final class ImageSearchApplication extends Application {

    private ImageSearchComponent mMainComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mMainComponent = DaggerImageSearchComponent.create();
    }

    public static ImageSearchComponent getComponent(Context context) {
        return ((ImageSearchApplication) context.getApplicationContext()).mMainComponent;
    }
}
