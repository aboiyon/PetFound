package com.moringa.petfinder.network;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface interface1 {
    @GET("animals/search")
    Call<SearchResponse> getPets(
            @Query("type") String type
    );
}
