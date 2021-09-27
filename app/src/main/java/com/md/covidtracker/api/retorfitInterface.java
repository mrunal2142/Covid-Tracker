package com.md.covidtracker.api;

import com.md.covidtracker.models.newsmodel2;
import com.md.covidtracker.models.vaccineModel;
import com.md.covidtracker.models.vaccineModel2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface retorfitInterface {

    @GET
    Call<newsmodel2> getAllNews(@Url String url);

    @GET
    Call<vaccineModel2> getVaccineCentres(@Url String url);
}
