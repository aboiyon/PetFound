package com.moringa.petfinder.network;

import static com.moringa.petfinder.Constants.BASE_URL;
import static com.moringa.petfinder.Constants.access_token;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PetClient {
    private static Retrofit retrofit = null;

    public static PetApi getClient(){
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
//                            Request original = chain.request();
                        Request newRequest = chain.request().newBuilder()
                                .addHeader("Authorization", access_token)
//                                    .header("User-Agent", "PetFinder")
//                                    .header("Accept", "application/vnd.\"Bearer bKYfebFDtZTVNWAbv7BMsmQYIgRAlYW717TqhFQWW4ZgEW3gQe\".v1.full+json")
//                                    .method(original.method(), original.body())
                                .build();
                        return chain.proceed(newRequest);
                    })

                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(PetApi.class);
    }
}
