package com.example.dagger2tutorial;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dagger2tutorial.dagger.ApplicationComponent;

public class BaseActivity extends AppCompatActivity {

    /**
     * It is convenient to create a BaseActivity and get the Application component via inheritance to avoid redundant codes
     */
    ApplicationComponent getApplicationComponent() {
        return ((MyApplication)getApplication()).getApplicationComponent();
    }
}
