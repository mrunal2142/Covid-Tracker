package com.md.covidtracker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.md.covidtracker.adapters.newsRecyclerViewAdapter;
import com.md.covidtracker.databinding.ActivityMainBinding;
import com.md.covidtracker.models.newsmodel;
import com.md.covidtracker.models.newsmodel2;
import com.md.covidtracker.api.retorfitInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    newsRecyclerViewAdapter adapter;
    ArrayList<newsmodel> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        newsList = new ArrayList<>();

        binding.newsRecyclerViewMain.setLayoutManager(new LinearLayoutManager(this));
        binding.newsRecyclerViewMain.setNestedScrollingEnabled(false);
        binding.newsRecyclerViewMain.setHasFixedSize(false);

        //setting adapter
        adapter = new newsRecyclerViewAdapter(newsList, this);
        binding.newsRecyclerViewMain.setAdapter(adapter);

        //getting data from api
        String BASE_URL = "https://newsapi.org/";
        String healthUrl = "https://newsapi.org/v2/top-headlines?country=in&apiKey=51cb1f4c2860477fb9b2d4cec2b18d64&category=health";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retorfitInterface retrofitRetorfitInterface = retrofit.create(retorfitInterface.class);
        Call<newsmodel2> call = retrofitRetorfitInterface.getAllNews(healthUrl);

        call.enqueue(new Callback<newsmodel2>() {
            @Override
            public void onResponse(Call<newsmodel2> call, Response<newsmodel2> response) {
                newsmodel2 newsmodel2 = response.body();
                ArrayList<newsmodel> articles = newsmodel2.getArticles();
                newsList.addAll(articles);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<newsmodel2> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "failed to get news", Toast.LENGTH_SHORT).show();
            }
        });

        binding.healthInfoLearnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.who.int/health-topics/coronavirus#tab=tab_1";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        binding.preventionLearnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.who.int/news-room/q-a-detail/coronavirus-disease-covid-19-how-is-it-transmitted";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        binding.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Feature under build", Toast.LENGTH_SHORT).show();
            }
        });

        binding.vaccineActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), vaccineActivity.class);
                startActivity(intent);
            }
        });

        binding.statsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), statsActivity.class);
                startActivity(intent);
            }
        });

        try {
            searchResult();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Failed to search", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    private void searchResult() {
        binding.searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.mainSearch.getText().toString() == null || binding.mainSearch.getText().toString().length() == 0) {
                    binding.mainSearch.setError("Required");
                    return;
                }
                Intent i = new Intent(Intent.ACTION_WEB_SEARCH);
                i.putExtra(SearchManager.QUERY, binding.mainSearch.getText().toString());
                binding.mainSearch.setText("");
                startActivity(i);
            }
        });
    }
}