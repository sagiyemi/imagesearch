package com.example.sagi.imagesearch.data.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.gabrielittner.auto.value.cursor.ColumnTypeAdapter;

/**
 * Created by sagiyemini on 28/03/2018.
 */

public class ImageAdapter implements ColumnTypeAdapter<Image> {

    @Override
    public Image fromCursor(Cursor cursor, String columnName) {
        return Image.create(cursor);
    }

    @Override
    public void toContentValues(ContentValues values, String columnName, Image value) {
        values.putAll(value.toContentValues());
    }
}
