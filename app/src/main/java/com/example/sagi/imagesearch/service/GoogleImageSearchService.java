package com.example.sagi.imagesearch.service;

import android.util.Log;

import com.example.sagi.imagesearch.BuildConfig;
import com.example.sagi.imagesearch.model.GoogleImage;
import com.example.sagi.imagesearch.model.ImageSearchResponse;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sagiyemini on 24/03/2018.
 */

public class GoogleImageSearchService {

    public static final String API_URL = "https://www.googleapis.com";

    private final GoogleImageSearch mImageSearch;

    public GoogleImageSearchService() {

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create());


        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(logging);
        }

        retrofitBuilder.client(httpClientBuilder.build());

        this.mImageSearch = retrofitBuilder.build().create(GoogleImageSearch.class);
    }

    public Observable<List<GoogleImage>> getImages() {
        Call<ImageSearchResponse> example = mImageSearch.getImages(10, 1, "example");
        ImageSearchResponse imageSearchResponse = null;
        try {
            imageSearchResponse = example.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (GoogleImage image : imageSearchResponse.images) {
            Log.d("ImageSearch", "Image " + image);
        }
        return Observable.just(imageSearchResponse.images);
    }


}
