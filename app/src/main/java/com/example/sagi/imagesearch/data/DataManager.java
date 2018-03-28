package com.example.sagi.imagesearch.data;

import android.util.Log;

import com.example.sagi.imagesearch.data.remote.GoogleImageSearchService;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by sagiyemini on 28/03/2018.
 */

@Singleton
public class DataManager {

    private final GoogleImageSearchService mGoogleImageSearchService;

    @Inject
    public DataManager(GoogleImageSearchService googleImageSearchService) {
        this.mGoogleImageSearchService = googleImageSearchService;
    }

    public Observable<Boolean> syncImageSearch() {
        Log.d("DataManager", "syncImageSearch");
        return mGoogleImageSearchService.getImages()
                .map(images -> true);
    }

}
