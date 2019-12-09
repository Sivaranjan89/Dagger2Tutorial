package com.example.dagger2tutorial;

import androidx.lifecycle.MutableLiveData;

import com.example.dagger2tutorial.network.RetrofitAPI;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepo {

    private MutableLiveData<ResponseBody> response = new MutableLiveData();

    public MutableLiveData<ResponseBody> getUpcomingMovies(RetrofitAPI retrofitInstance) {
        Call<ResponseBody> upcoming = retrofitInstance.getUpcomingMovies();
        upcoming.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> res) {
                response.postValue(res.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //TODO
            }
        });

        return response;
    }
}
