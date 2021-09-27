package com.md.covidtracker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.md.covidtracker.R;
import com.md.covidtracker.adapters.newsRecyclerViewAdapter;
import com.md.covidtracker.adapters.vaccineActivityAdapter;
import com.md.covidtracker.api.retorfitInterface;
import com.md.covidtracker.databinding.ActivityVaccineBinding;
import com.md.covidtracker.models.vaccineModel;
import com.md.covidtracker.models.vaccineModel2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class vaccineActivity extends AppCompatActivity {

    ArrayList<vaccineModel> vaccineCentreList;
    ActivityVaccineBinding binding;
    vaccineActivityAdapter adapter;
    int month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVaccineBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        vaccineCentreList = new ArrayList<>();

        try {
            Calendar calendar = Calendar.getInstance();
            month = calendar.get(Calendar.MONTH);
            binding.month.setText(String.valueOf(month + 1));
            binding.year.setText(String.valueOf(calendar.get(Calendar.YEAR)));
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "month" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        binding.vaccineRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.vaccineRecyclerView.setNestedScrollingEnabled(false);
        binding.vaccineRecyclerView.setHasFixedSize(false);

        adapter = new vaccineActivityAdapter(this, vaccineCentreList);
        binding.vaccineRecyclerView.setAdapter(adapter);

        binding.vaccinationLearnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.who.int/emergencies/diseases/novel-coronavirus-2019/covid-19-vaccines";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        binding.getDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCentres();
            }
        });

    }

    private void getCentres() {

        if (binding.pincodeInput.getText().toString().length() != 6) {
            binding.pincodeInput.setError("Invalid pincode");
            return;
        }

//        if(Integer.parseInt(binding.date.getText().toString()) < Calendar.getInstance().get(Calendar.DATE)){
//            binding.date.setError("Invalid date");
//            return;
//        }

        if (Integer.parseInt(binding.month.getText().toString()) >= 13) {
            binding.month.setError("Invalid month");
            return;
        }

        if (!binding.date.getText().toString().equals("")) {
            if (Integer.parseInt(binding.date.getText().toString()) >= 32 || Integer.parseInt(binding.date.getText().toString()) <= 0
                    || Integer.parseInt(binding.date.getText().toString()) < Calendar.getInstance().get(Calendar.DATE)) {
                binding.date.setError("Invalid date");
                return;
            }
        } else if (binding.date.getText().toString().equals("")) {
            binding.date.setError("Invalid date");
            return;
        }


        String date = binding.date.getText().toString() + "-" + String.valueOf(month + 1) + "-" + String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        //gettting list of vaccination centers
        // https://cdn-api.co-vin.in/api/

        String url = "https://cdn-api.co-vin.in/api/";
        String filterUrl = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode=" + binding.pincodeInput.getText().toString() + "&date=" + date;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retorfitInterface retrofitRetorfitInterface = retrofit.create(retorfitInterface.class);
        Call<vaccineModel2> call = retrofitRetorfitInterface.getVaccineCentres(filterUrl);

        call.enqueue(new Callback<vaccineModel2>() {
            @Override
            public void onResponse(Call<vaccineModel2> call, Response<vaccineModel2> response) {
                if (response.body() != null) {
                    Toast.makeText(getApplicationContext(), "Updating information...", Toast.LENGTH_SHORT).show();
                    vaccineCentreList.clear();
                    vaccineModel2 vaccineModel2 = response.body();
                    if (vaccineModel2.getSessions().size() == 0) {
                        Toast.makeText(getApplicationContext(), "No data available at the current moment", Toast.LENGTH_LONG).show();
                    }
                    vaccineCentreList.addAll(vaccineModel2.getSessions());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<vaccineModel2> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "fail to get vaccination centre list", Toast.LENGTH_SHORT).show();
            }
        });

    }
}