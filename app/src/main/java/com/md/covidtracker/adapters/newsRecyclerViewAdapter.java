package com.md.covidtracker.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.md.covidtracker.R;
import com.md.covidtracker.models.newsmodel;

import java.util.ArrayList;

public class newsRecyclerViewAdapter extends RecyclerView.Adapter<newsRecyclerViewAdapter.viewHolder> {

    private ArrayList<newsmodel> newsList;
    private Context context;

    public newsRecyclerViewAdapter(ArrayList<newsmodel> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate's layout xml -> java
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerviewitems, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        //sets the data in views and shows to users
        newsmodel news = newsList.get(position);
        if (!newsList.isEmpty()) {
            try {

                if (newsList.get(position).getAuthor() != null) {
                    holder.author.setText(news.getAuthor());
                }
                if (newsList.get(position).getTitle() != null) {
                    holder.title.setText(news.getTitle());
                }
                if (newsList.get(position).getDescription() != null) {
                    holder.description.setText(news.getDescription());
                }
            } catch (Exception e) {
                Toast.makeText(context.getApplicationContext(), "adapter class error" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(news.getUrl()));
                    context.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView title,author,description,dateAndTime;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            description = itemView.findViewById(R.id.description);
            author = itemView.findViewById(R.id.datetime);
        }
    }
}
