package com.moringa.petfinder.network;

import com.moringa.petfinder.models.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface interface1 {
    @GET("animals")
    Call<SearchResponse> getPets(
            @Query("type") String type,
            @Query("breed") String breed
    );
}
