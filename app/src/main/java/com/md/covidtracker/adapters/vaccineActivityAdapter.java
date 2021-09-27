package com.md.covidtracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.md.covidtracker.R;
import com.md.covidtracker.models.vaccineModel;

import java.util.ArrayList;

public class vaccineActivityAdapter extends RecyclerView.Adapter<vaccineActivityAdapter.viewholder> {

    Context context;
    ArrayList<vaccineModel> list;

    public vaccineActivityAdapter(Context context, ArrayList<vaccineModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vaccinecentreitem, parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        vaccineModel vaccineModel = list.get(position);
        try {
            holder.location.setText(vaccineModel.getAddress());
            holder.vaccineName.setText(vaccineModel.getVaccine());
            holder.hospital.setText(vaccineModel.getName());
            holder.time.setText("From " + vaccineModel.getFrom() + " to " + vaccineModel.getTo());
            holder.dose1.setText("Dose 1: " +  Integer.parseInt(vaccineModel.getAvailable_capacity_dose1()));
            holder.dose2.setText("Dose 2: " +  Integer.parseInt(vaccineModel.getAvailable_capacity_dose2()));
        }catch (Exception e) {

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{

        TextView hospital , location ,time , vaccineName, dose1, dose2;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            hospital = itemView.findViewById(R.id.vac_hosp);
            location = itemView.findViewById(R.id.vac_loc);
            time = itemView.findViewById(R.id.vac_time);
            vaccineName = itemView.findViewById(R.id.vac_name);
            dose1 = itemView.findViewById(R.id.dose1);
            dose2 = itemView.findViewById(R.id.dose2);
        }
    }
}
