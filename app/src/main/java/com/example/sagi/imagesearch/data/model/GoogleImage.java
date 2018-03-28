package com.example.sagi.imagesearch.data.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by sagiyemini on 24/03/2018.
 */

@AutoValue
public abstract class GoogleImage implements Parcelable {

    public abstract String title();

    public static GoogleImage create(String title) {
        return new AutoValue_GoogleImage(title);
    }

    public static TypeAdapter<GoogleImage> typeAdapter(Gson gson) {
        return new AutoValue_GoogleImage.GsonTypeAdapter(gson);
    }

}
