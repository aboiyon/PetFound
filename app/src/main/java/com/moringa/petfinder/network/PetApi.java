package com.moringa.petfinder.network;

import com.moringa.petfinder.models.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PetApi {
//    @Headers({"Accept:application/vnd.\"Bearer bKYfebFDtZTVNWAbv7BMsmQYIgRAlYW717TqhFQWW4ZgEW3gQe\".v1.full+json",
//    "User-Agent:PetFinder"})
    @GET("Animals/types")
    Call<SearchResponse> getPets(
            @Query("type") String type
    );
}
