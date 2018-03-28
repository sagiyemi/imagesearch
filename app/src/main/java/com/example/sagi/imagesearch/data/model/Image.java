package com.example.sagi.imagesearch.data.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by sagiyemini on 28/03/2018.
 */

@AutoValue
public abstract class Image implements Parcelable {

    public abstract String contextLink();

    public abstract Integer height();

    public abstract Integer width();

    public static Image create(String contextLink, Integer height, Integer width) {
        return new AutoValue_Image(contextLink, height, width);
    }

    public static TypeAdapter<Image> typeAdapter(Gson gson) {
        return new AutoValue_Image.GsonTypeAdapter(gson);
    }

}
