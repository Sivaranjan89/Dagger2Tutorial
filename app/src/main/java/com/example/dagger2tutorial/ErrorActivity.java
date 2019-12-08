package com.example.dagger2tutorial;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dagger2tutorial.network.RetrofitAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;

/**
 * This activity is created to explain the importance of inject() declared in ApplicationComponent
 */
public class ErrorActivity extends BaseActivity {

    RecyclerView list;

    @Inject
    MainViewModel model;

    @Inject
    RetrofitAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * If you use this Activity, You will notice that the application crashes because the below line to inject has been commented.
         * Thus our viewmodel and retrofitapi will be null
         * If we uncomment the below line, We will get a comppile time error because we have not created a inject method for ErrorActivity
         */
        //getApplicationComponent().inject(this);

        init();

        model.getUpcomingMovies(api).observe(this, new Observer<ResponseBody>() {
            @Override
            public void onChanged(ResponseBody responseBody) {
                try {
                    JSONObject response = new JSONObject(responseBody.string());
                    JSONArray results = response.getJSONArray("results");

                    List<String> titles = new ArrayList<>();
                    List<String> images = new ArrayList<>();

                    for (int i=0; i<results.length(); i++) {
                        JSONObject obj = results.getJSONObject(i);
                        titles.add(obj.getString("original_title"));
                        images.add("https://image.tmdb.org/t/p/w200/" + obj.getString("poster_path"));
                    }

                    list.setAdapter(new MyAdapter(ErrorActivity.this, titles, images));

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void init() {
        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(
                        ErrorActivity.this,
                        RecyclerView.VERTICAL,
                        false
                )
        );
    }
}
