package com.example.dagger2tutorial;

import android.app.Application;

import com.example.dagger2tutorial.dagger.ApplicationComponent;
import com.example.dagger2tutorial.dagger.ApplicationModule;
import com.example.dagger2tutorial.dagger.DaggerApplicationComponent;
import com.example.dagger2tutorial.dagger.NetworkingModule;

public class MyApplication extends Application {

    private ApplicationComponent applicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * Once we create our application component and modules, We should build our porject once to get the DaggerApplicationComponent class.
         * Invoke our application component here using
         * DaggerApplicationComponent.builder().applicationModule(module).build()
         */
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule())
                .networkingModule(new NetworkingModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
