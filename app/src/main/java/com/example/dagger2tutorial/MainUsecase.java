package com.example.dagger2tutorial;

import androidx.lifecycle.MutableLiveData;

import com.example.dagger2tutorial.network.RetrofitAPI;

import javax.inject.Inject;

import okhttp3.ResponseBody;

public class MainUsecase {

    MainRepo repo;

    /**
     * @param mainRepo is injected as a Constructor injection in ApplicationModule
     */
    @Inject
    public MainUsecase(MainRepo mainRepo) {
        repo = mainRepo;
    }

    public MutableLiveData<ResponseBody> getUpcomingMovies(RetrofitAPI retrofitInstance) {
        return repo.getUpcomingMovies(retrofitInstance);
    }
}
