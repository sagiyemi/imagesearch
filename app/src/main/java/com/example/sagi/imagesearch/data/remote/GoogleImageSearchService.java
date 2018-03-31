package com.example.sagi.imagesearch.data.remote;

import android.support.annotation.NonNull;

import com.example.sagi.imagesearch.data.model.ImageEntity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by sagiyemini on 24/03/2018.
 */

@Singleton
public class GoogleImageSearchService {

    private final GoogleImageSearch mImageSearch;

    @Inject
    public GoogleImageSearchService(GoogleImageSearch googleImageSearch) {
        this.mImageSearch = googleImageSearch;
    }

    public Observable<List<ImageEntity>> getImages(@NonNull String searchTerm, int offset, int numberOfItems) {
        return mImageSearch.getImages(numberOfItems, offset, searchTerm)
                .map(imageSearchResponse -> imageSearchResponse.images);
    }

}
