package com.example.dagger2tutorial.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {

    @GET("/3/movie/upcoming?api_key=a795dc1db1e1c0428bf7a24656f09bfa")
    Call<ResponseBody> getUpcomingMovies();
}
