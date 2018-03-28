package com.example.sagi.imagesearch.data.local;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;

import javax.inject.Inject;

/**
 * Created by sagiyemini on 28/03/2018.
 */

public class DbCallback extends SupportSQLiteOpenHelper.Callback {

    private static final int VERSION = 1;

    @Inject
    public DbCallback() {
        super(VERSION);
    }

    @Override
    public void onCreate(SupportSQLiteDatabase db) {

        createTables(db);

    }

    @Override
    public void onUpgrade(SupportSQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createTables(SupportSQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(Db.ImageTable.CREATE);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

}
