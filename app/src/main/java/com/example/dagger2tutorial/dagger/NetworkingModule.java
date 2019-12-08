package com.example.dagger2tutorial.dagger;

import com.example.dagger2tutorial.network.RetrofitAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This module will provide Retrofit related injections
 * We need to annotate with @Module for it to be binded to the component
 * In this Module we do Method Injection
 */
@Module
public class NetworkingModule {

    private final String BASE_URL = "https://api.themoviedb.org";

    /**
     * Here we declare @Singleton to prevent duplicate instances
     */
    @Singleton
    @Provides
    Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     *
     * @param retrofit will be injected to this method with the help of getRetrofitInstance()
     * Now we can inject RetrofitAPI directly in our classes with the help of below provider
     */
    @Singleton
    @Provides
    RetrofitAPI getRetrofitApi(Retrofit retrofit) {
        return retrofit.create(RetrofitAPI.class);
    }

}
