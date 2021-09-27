package com.md.covidtracker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.md.covidtracker.R;
import com.md.covidtracker.api.apiUtilities;
import com.md.covidtracker.databinding.ActivityStatsBinding;
import com.md.covidtracker.models.countryData;

import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class statsActivity extends AppCompatActivity {

    ActivityStatsBinding binding;
    String country = "India";
    private List<countryData> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.countryName.setText(country.toUpperCase());

        list = new ArrayList<>();

        settingData(country);

        searchingData();

    }

    private void searchingData() {

        binding.searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.countrySearch.getText().toString().length() ==0 ||
                            binding.countrySearch.getText().toString() == null) {
                    binding.countrySearch.setError("Field cannot be null!");
                    return;
                }

                Toast.makeText(getApplicationContext(), "Loading data...", Toast.LENGTH_SHORT).show();

                apiUtilities.getApiInterface().getCountryData().enqueue(new Callback<List<countryData>>() {
                    @Override
                    public void onResponse(Call<List<countryData>> call, Response<List<countryData>> response) {
                        list.clear();
                        list.addAll(response.body());

                        Editable countryNameET = binding.countrySearch.getText();
                        String searcCountryName = countryNameET.toString().toLowerCase().replaceAll(" ","");

                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getCountry().toLowerCase().equals(searcCountryName)) {

                                binding.countryName.setText(binding.countrySearch.getText().toString().toUpperCase());

                                int confirm = Integer.parseInt(list.get(i).getCases());
                                int active = Integer.parseInt(list.get(i).getActive());
                                int recovered = Integer.parseInt(list.get(i).getRecovered());
                                int death = Integer.parseInt(list.get(i).getDeaths());

                                binding.totalActive.setText("Total: " + NumberFormat.getInstance().format(active));
                                binding.totalCnfrm.setText("Total: " + NumberFormat.getInstance().format(confirm));
                                binding.totalRecovered.setText("Total: " + NumberFormat.getInstance().format(recovered));
                                binding.totalDeath.setText("Total: " + NumberFormat.getInstance().format(death));

                                binding.todayCnfrm.setText("Today: +" + NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayCases())));
                                binding.todaydeath.setText("Today: +" + NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayDeaths())));
                                binding.todayRecovered.setText("Today: +" + NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayRecovered())));

                                setdate(list.get(i).getUpdated());

                                binding.pieChart.clearChart();

                                binding.pieChart.addPieSlice(new PieModel("Confirm", confirm, Color.parseColor("#FBC233")));
                                binding.pieChart.addPieSlice(new PieModel("Active", active, Color.parseColor("#007afe")));
                                binding.pieChart.addPieSlice(new PieModel("Recovered", recovered, Color.parseColor("#08a045")));
                                binding.pieChart.addPieSlice(new PieModel("Deaths", death, Color.parseColor("#F6101F")));
                                binding.pieChart.startAnimation();

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<countryData>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Cannot find country: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    private void settingData(String country) {

        apiUtilities.getApiInterface().getCountryData().enqueue(new Callback<List<countryData>>() {
            @Override
            public void onResponse(Call<List<countryData>> call, Response<List<countryData>> response) {
                list.clear();
                list.addAll(response.body());

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getCountry().equals(country)) {
                        int confirm = Integer.parseInt(list.get(i).getCases());
                        int active = Integer.parseInt(list.get(i).getActive());
                        int recovered = Integer.parseInt(list.get(i).getRecovered());
                        int death = Integer.parseInt(list.get(i).getDeaths());

                        binding.totalActive.setText("Total: " + NumberFormat.getInstance().format(active));
                        binding.totalCnfrm.setText("Total: " + NumberFormat.getInstance().format(confirm));
                        binding.totalRecovered.setText("Total: " + NumberFormat.getInstance().format(recovered));
                        binding.totalDeath.setText("Total: " + NumberFormat.getInstance().format(death));

                        binding.todayCnfrm.setText("Today: +" + NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayCases())));
                        binding.todaydeath.setText("Today: +" + NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayDeaths())));
                        binding.todayRecovered.setText("Today: +" + NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayRecovered())));

                        setdate(list.get(i).getUpdated());

                        binding.pieChart.clearChart();

                        binding.pieChart.addPieSlice(new PieModel("Confirm", confirm, Color.parseColor("#FBC233")));
                        binding.pieChart.addPieSlice(new PieModel("Active", active, Color.parseColor("#007afe")));
                        binding.pieChart.addPieSlice(new PieModel("Recovered", recovered, Color.parseColor("#08a045")));
                        binding.pieChart.addPieSlice(new PieModel("Deaths", death, Color.parseColor("#F6101F")));
                        binding.pieChart.startAnimation();

                    }
                }
            }

            @Override
            public void onFailure(Call<List<countryData>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setdate(String updated) {
        DateFormat format = new SimpleDateFormat("dd MM yyyy");
        long ms = Long.parseLong(updated);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);

        binding.updateTime.setText("Information Updated :" + format.format(calendar.getTime()));
    }
}