package com.example.sagi.imagesearch.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.sagi.imagesearch.data.local.DatabaseHelper;
import com.example.sagi.imagesearch.data.model.GoogleImage;
import com.example.sagi.imagesearch.data.remote.GoogleImageSearchService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by sagiyemini on 28/03/2018.
 */

@Singleton
public class DataManager {

    private static final int PAGE_SIZE = 10;

    private final GoogleImageSearchService mGoogleImageSearchService;
    private final DatabaseHelper mDatabaseHelper;

    @Inject
    public DataManager(GoogleImageSearchService googleImageSearchService, DatabaseHelper databaseHelper) {
        this.mGoogleImageSearchService = googleImageSearchService;
        this.mDatabaseHelper = databaseHelper;
    }

    public Observable<Boolean> syncImagesPage(String searchTerm, int pageNumber) {
        Log.d("DataManager", "syncImagesPage");
        int offset = (pageNumber - 1) * PAGE_SIZE + 1;
        return mGoogleImageSearchService.getImages(searchTerm, offset, PAGE_SIZE)
                .map(images -> {
                    mDatabaseHelper.setGoogleImages(images, searchTerm, pageNumber);
                    return true;
                });
    }

    public Observable<List<GoogleImage>> getImages(@NonNull String searchTerm) {
        return mDatabaseHelper.getGoogleImages(searchTerm);
    }

}
