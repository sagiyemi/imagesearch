package com.example.sagi.imagesearch.data.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcelable;

import com.gabrielittner.auto.value.cursor.ColumnAdapter;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by sagiyemini on 24/03/2018.
 */

@AutoValue
public abstract class ImageEntity implements Parcelable {

    public abstract String title();

    public abstract String link();

    @ColumnAdapter(ImageAdapter.class)
    public abstract Image image();

    public static ImageEntity create(String title, String link, Image image) {
        return new AutoValue_ImageEntity(title, link, image);
    }

    public static ImageEntity create(Cursor cursor) {
        return AutoValue_ImageEntity.createFromCursor(cursor);
    }

    public static TypeAdapter<ImageEntity> typeAdapter(Gson gson) {
        return new AutoValue_ImageEntity.GsonTypeAdapter(gson);
    }

    public abstract ContentValues toContentValues();

}
