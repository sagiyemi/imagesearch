package com.example.sagi.imagesearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.sagi.imagesearch.service.GoogleImageSearchService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ImageSearchActivity extends AppCompatActivity {

    GoogleImageSearchService mGoogleImageSearchService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_search);
        if (mGoogleImageSearchService == null) {
            mGoogleImageSearchService = new GoogleImageSearchService();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        mGoogleImageSearchService.getImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
