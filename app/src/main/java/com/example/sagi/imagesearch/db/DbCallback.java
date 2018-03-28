package com.example.sagi.imagesearch.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;

/**
 * Created by sagiyemini on 28/03/2018.
 */

public class DbCallback extends SupportSQLiteOpenHelper.Callback {

    private static final int VERSION = 1;

    public DbCallback() {
        super(VERSION);
    }

    @Override
    public void onCreate(SupportSQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SupportSQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
