package com.task.blogs;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.Call;

public interface ApiInterface {

    @GET("/posts")
    Call<List<posts>> getposts();

}