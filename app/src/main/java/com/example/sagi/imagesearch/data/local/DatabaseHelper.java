package com.example.sagi.imagesearch.data.local;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.example.sagi.imagesearch.data.model.GoogleImage;
import com.squareup.sqlbrite3.BriteDatabase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE;

/**
 * Created by sagiyemini on 28/03/2018.
 */

@Singleton
public class DatabaseHelper {

    private final BriteDatabase mDb;

    @Inject
    public DatabaseHelper(BriteDatabase briteDatabase) {
        this.mDb = briteDatabase;
    }

    public void setGoogleImages(List<GoogleImage> images, @NonNull String searchTerm, int pageNumber) {
        // TODO: Extract to util bulk insert
        ContentValues extraContentValues = new ContentValues();
        extraContentValues.put(Db.GoogleImageTable.COLUMN_SEARCH_TERM, searchTerm);
        extraContentValues.put(Db.GoogleImageTable.COLUMN_PAGE_NUMBER, pageNumber);
        BriteDatabase.Transaction transaction = mDb.newTransaction();
        try {
            for (GoogleImage image : images) {
                ContentValues contentValues = image.toContentValues();
                // Append search term and page number
                contentValues.putAll(extraContentValues);
                mDb.insert(Db.GoogleImageTable.TABLE_NAME, CONFLICT_REPLACE, contentValues);
            }
            transaction.markSuccessful();
        } finally {
            transaction.end();
        }
    }

    public Observable<List<GoogleImage>> getGoogleImages(@NonNull String searchTerm) {
        return mDb.createQuery(Db.GoogleImageTable.TABLE_NAME,
                "SELECT * FROM " + Db.GoogleImageTable.TABLE_NAME
                        + " WHERE " + Db.GoogleImageTable.COLUMN_SEARCH_TERM + " =? ", searchTerm)
                .mapToList(GoogleImage::create);
    }

}
