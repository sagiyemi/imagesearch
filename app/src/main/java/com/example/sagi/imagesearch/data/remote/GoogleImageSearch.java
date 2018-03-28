package com.example.sagi.imagesearch.data.remote;

import com.example.sagi.imagesearch.BuildConfig;
import com.example.sagi.imagesearch.data.model.ImageSearchResponse;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sagiyemini on 24/03/2018.
 */

public interface GoogleImageSearch {

    String API_URL = "https://www.googleapis.com";

    @GET("/customsearch/v1?key=AIzaSyCzREl07Bc_bk2hm65RtiaJ0hJ48R_nMfg&cx=005800383728131713214:5jocmduwqum&searchType=image")
    Observable<ImageSearchResponse> getImages(
            @Query("num") Integer numberOfItems,
            @Query("start") Integer offset,
            @Query("q") String query);


    class Factory {

        public static GoogleImageSearch makeGoogleImageSearch() {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                    : HttpLoggingInterceptor.Level.NONE);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(
                    new GsonBuilder().registerTypeAdapterFactory(AutoValueGsonFactory.create())
                            .create());

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .client(okHttpClient)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            return retrofit.create(GoogleImageSearch.class);
        }
    }
}
