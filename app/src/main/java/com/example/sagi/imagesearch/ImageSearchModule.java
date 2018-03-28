package com.example.sagi.imagesearch;

import android.app.Application;

import com.example.sagi.imagesearch.data.local.DbModule;
import com.example.sagi.imagesearch.data.remote.GoogleImageSearch;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sagiyemini on 28/03/2018.
 */

@Module(
        includes = {
                DbModule.class,
        }
)
public final class ImageSearchModule {

    private final Application mApplication;

    ImageSearchModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    GoogleImageSearch provideGoogleImageSearch() {
        return GoogleImageSearch.Factory.makeGoogleImageSearch(mApplication);
    }

}
