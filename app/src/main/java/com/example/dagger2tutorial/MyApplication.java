package com.example.dagger2tutorial;

import android.app.Application;

import com.example.dagger2tutorial.dagger.ApplicationComponent;
import com.example.dagger2tutorial.dagger.ApplicationModule;
import com.example.dagger2tutorial.dagger.DaggerApplicationComponent;

public class MyApplication extends Application {

    private ApplicationComponent applicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * Once we create our application component and modules, We should build our porject once to get the DaggerApplicationComponent class.
         * Invoke our application component here using
         * DaggerApplicationComponent.builder().build()
         * .applicationModule(new ApplicationModule(this)) is called because application module needs context for injection
         */
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
