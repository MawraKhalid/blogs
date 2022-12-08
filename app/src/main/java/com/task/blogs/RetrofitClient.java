package com.task.blogs;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String base_url = "https://jsonplaceholder.typicode.com/";
    private static Retrofit retrofit = null;

    public static ApiInterface getRetrofitClient(){

        if(retrofit == null ){
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }
}
