package com.example.dagger2tutorial;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dagger2tutorial.network.RetrofitAPI;

import javax.inject.Inject;

import okhttp3.ResponseBody;

public class MainViewModel extends ViewModel {


    MainUsecase useCase;

    /**
     * @param mainUsecase is injected via constructor injection in ApplicationModule
     */
    @Inject
    public MainViewModel(MainUsecase mainUsecase) {
        useCase = mainUsecase;
    }

    public MutableLiveData<ResponseBody> getUpcomingMovies(RetrofitAPI retrofitInstance) {
        return useCase.getUpcomingMovies(retrofitInstance);
    }

}
