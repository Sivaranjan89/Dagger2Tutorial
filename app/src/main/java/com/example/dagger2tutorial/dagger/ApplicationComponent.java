package com.example.dagger2tutorial.dagger;

import com.example.dagger2tutorial.MainActivity;

import javax.inject.Singleton;

import dagger.Component;


/**
 * We call @Singleton here because he networking Module has a Singleton Provider
 * @Component will be used to bind the modules
 * Create inject(MainActivity) method to allow injection of the mentioned modules to MainActivity
 * To inject these modules to other activities, Create inject(classname) accordingly
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetworkingModule.class})
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);
}
