package com.example.sagi.imagesearch.data.local;

import com.example.sagi.imagesearch.data.model.GoogleImage;
import com.squareup.sqlbrite3.BriteDatabase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_NONE;

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

    public void setGoogleImages(List<GoogleImage> images) {
        // TODO: Extract to util bulk insert
        BriteDatabase.Transaction transaction = mDb.newTransaction();
        try {
            for (GoogleImage image : images) {
                mDb.insert(Db.GoogleImageTable.TABLE_NAME, CONFLICT_NONE, image.toContentValues());
            }
            transaction.markSuccessful();
        } finally {
            transaction.end();
        }
    }

}
