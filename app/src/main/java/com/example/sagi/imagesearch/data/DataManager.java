package com.example.sagi.imagesearch.data;

import android.support.annotation.NonNull;

import com.example.sagi.imagesearch.data.local.DatabaseHelper;
import com.example.sagi.imagesearch.data.model.ImageEntity;
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

    public static final int INITIAL_IMAGE_SEARCH_PAGE_NUMBER = 1;
    private static final int PAGE_SIZE = 10;

    private final GoogleImageSearchService mGoogleImageSearchService;
    private final DatabaseHelper mDatabaseHelper;

    @Inject
    public DataManager(GoogleImageSearchService googleImageSearchService, DatabaseHelper databaseHelper) {
        this.mGoogleImageSearchService = googleImageSearchService;
        this.mDatabaseHelper = databaseHelper;
    }

    public Observable<Boolean> syncImagesPage(String searchTerm, int pageNumber) {
        int offset = (pageNumber - 1) * PAGE_SIZE + 1;
        boolean deletePreviousData = pageNumber == INITIAL_IMAGE_SEARCH_PAGE_NUMBER;
        return mGoogleImageSearchService.getImages(searchTerm, offset, PAGE_SIZE)
                .map(images -> {
                    mDatabaseHelper.setImages(images, searchTerm, pageNumber, deletePreviousData);
                    return true;
                });
    }

    public Observable<List<ImageEntity>> getImages(@NonNull String searchTerm) {
        return mDatabaseHelper.getImages(searchTerm);
    }

    public Observable<ImageEntity> getImage(@NonNull String imageId) {
        return mDatabaseHelper.getImage(imageId);
    }

}
