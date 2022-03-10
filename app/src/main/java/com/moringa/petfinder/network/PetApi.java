package com.moringa.petfinder.network;

import com.moringa.petfinder.models.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface PetApi {
    @Headers({"Authorization: "})
    @GET("animals")
    Call<SearchResponse> getPets(
//            @Query("type") String type
    );
}
