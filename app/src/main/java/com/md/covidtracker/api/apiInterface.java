package com.md.covidtracker.api;

import com.md.covidtracker.models.countryData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface apiInterface {

    static final String BASE_URL="https://corona.lmao.ninja/v2/";

    @GET("countries")
    Call<List<countryData>> getCountryData();
    
}
