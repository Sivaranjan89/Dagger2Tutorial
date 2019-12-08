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
 * MainActivity will extend baseActivity to retrieve application component whenever necessary
 */
public class MainActivity extends BaseActivity {

    RecyclerView list;

    /**
     * The MainViewModel can now be Injected by simply using @Inject annotation.
     * We need not create any new instance of MainviewModel here
     * as we had already provided MainviewModel as a provider with MainuseCase binded to its constructor
     * in ApplicationModule
     */
    @Inject
    MainViewModel model;

    /**
     * We can inject RetrofitAPI directly without having to get Retrofit instance since it is being provided from NetworkingModule
     */
    @Inject
    RetrofitAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * if we dont add this line, Then no Injection will take place
         * We had declared in ApplicationComponent that the modules will be injected to mainactivity via inject method
         */
        getApplicationComponent().inject(this);

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

                    list.setAdapter(new MyAdapter(MainActivity.this, titles, images));

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
                        MainActivity.this,
                        RecyclerView.VERTICAL,
                        false
                )
        );
    }
}
