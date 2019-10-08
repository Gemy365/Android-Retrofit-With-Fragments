package com.example.android.retrofitwithfragments.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Class.
public class RetrofitClient {

    //Init value of retrofit.
    private static Retrofit retrofit = null;

    // getRetrofit Method Return Val As Retrofit.
    public static Retrofit getRetrofit() {

        // Check if it null.
        if (retrofit == null){

            // Prepare retrofit.
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        // return.
        return retrofit;
    }

}
