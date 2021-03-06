package com.example.sagi.imagesearch.data.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcelable;

import com.example.sagi.imagesearch.data.local.Db;
import com.gabrielittner.auto.value.cursor.ColumnName;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by sagiyemini on 28/03/2018.
 */

@AutoValue
public abstract class Image implements Parcelable {

    @ColumnName(Db.ImageTable.COLUMN_CONTEXT_LINK)
    public abstract String contextLink();

    public abstract Integer height();

    public abstract Integer width();

    @ColumnName(Db.ImageTable.COLUMN_THUMBNAIL_LINK)
    public abstract String thumbnailLink();

    @ColumnName(Db.ImageTable.COLUMN_THUMBNAIL_HEIGHT)
    public abstract Integer thumbnailHeight();

    @ColumnName(Db.ImageTable.COLUMN_THUMBNAIL_WIDTH)
    public abstract Integer thumbnailWidth();


    public static Image create(String contextLink, Integer height, Integer width,
                               String thumbnailLink, Integer thumbnailHeight, Integer thumbnailWidth) {
        return new AutoValue_Image(contextLink, height, width, thumbnailLink, thumbnailHeight, thumbnailWidth);
    }

    public static Image create(Cursor cursor) {
        return AutoValue_Image.createFromCursor(cursor);
    }

    public static TypeAdapter<Image> typeAdapter(Gson gson) {
        return new AutoValue_Image.GsonTypeAdapter(gson);
    }

    public abstract ContentValues toContentValues();

}
