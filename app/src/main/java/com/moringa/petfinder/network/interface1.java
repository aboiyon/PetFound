package com.moringa.petfinder.network;

public interface interface1 {
    @GET("animals")
    Call<SearchResponse> getPets(
            @Query("type") String type
    );
}
