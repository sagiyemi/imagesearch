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

        if (mMainComponent == null) {
            mMainComponent = DaggerImageSearchComponent.builder()
                    .imageSearchModule(new ImageSearchModule(this))
                    .build();
        }
    }

    public static ImageSearchComponent getComponent(Context context) {
        return ((ImageSearchApplication) context.getApplicationContext()).mMainComponent;
    }
}
