package com.example.dagger2tutorial;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.dagger2tutorial.network.RetrofitAPI;

import javax.inject.Inject;

import okhttp3.ResponseBody;

public class MainUsecase {

    private MainRepo repo;

    private Context mContext;

    /**
     * @param mainRepo is injected as a Constructor injection in ApplicationModule
     * @param context is injected similarly
     */
    @Inject
    public MainUsecase(MainRepo mainRepo, Context context) {
        repo = mainRepo;
        mContext = context;
    }

    public MutableLiveData<ResponseBody> getUpcomingMovies(RetrofitAPI retrofitInstance) {
        return repo.getUpcomingMovies(retrofitInstance);
    }
}
