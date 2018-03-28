package com.example.sagi.imagesearch.data.remote;

import com.example.sagi.imagesearch.data.model.ImageSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sagiyemini on 24/03/2018.
 */

public interface GoogleImageSearch {

    @GET("/customsearch/v1?key=AIzaSyCzREl07Bc_bk2hm65RtiaJ0hJ48R_nMfg&cx=005800383728131713214:5jocmduwqum&searchType=image")
    Call<ImageSearchResponse> getImages(
            @Query("num") Integer numberOfItems,
            @Query("start") Integer offset,
            @Query("q") String query);

}
