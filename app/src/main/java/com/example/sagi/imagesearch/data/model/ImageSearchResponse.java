package com.example.sagi.imagesearch.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sagiyemini on 24/03/2018.
 */

public class ImageSearchResponse {

    @SerializedName("items")
    public final List<GoogleImage> images;

    public ImageSearchResponse(List<GoogleImage> images) {
        this.images = images;
    }

}
