package com.example.dagger2tutorial.dagger;

import com.example.dagger2tutorial.MainRepo;
import com.example.dagger2tutorial.MainUsecase;
import com.example.dagger2tutorial.MainViewModel;

import dagger.Module;
import dagger.Provides;


/**
 * This module will provide ViewModel, useCases and Repository
 * We need to annotate with @Module for it to be binded to the component
 * In this Module we do Constructor Injection
 */
@Module
public class ApplicationModule {


    /**
     * @Provides will declare this as a injection
     * @param mainUsecase will inject MainUseCase to MainViewModel Class
     */
    @Provides
    MainViewModel bindViewModel(MainUsecase mainUsecase) {
        return new MainViewModel(mainUsecase);
    }


    /**
     * @param mainRepo will inject MainRepo to MainUsecase class
     */
    @Provides
    MainUsecase bindMainUseCase(MainRepo mainRepo) {
        return new MainUsecase(mainRepo);
    }

}
