package com.example.sagi.imagesearch.data.remote;

import android.util.Log;

import com.example.sagi.imagesearch.data.model.GoogleImage;

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

    public Observable<List<GoogleImage>> getImages() {
        return mImageSearch.getImages(10, 1, "example")
                .map(imageSearchResponse -> {
                    List<GoogleImage> images = imageSearchResponse.images;
                    for (GoogleImage image : images) {
                        Log.d("Service", "Image" + image);
                    }
                    Log.d("Service", "Got " + images.size());
                    return images;

                });
    }


}
