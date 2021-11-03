package com.example.sqlitedatabase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {
    Activity activity;
    private Context context;
    ArrayList id, title, author, pages;

    public Myadapter(Activity activity, Context context, ArrayList id, ArrayList title, ArrayList author, ArrayList pages) {
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtId.setText(String.valueOf(id.get(position)));
        holder.txtTitle.setText(String.valueOf(title.get(position)));
        holder.txtAuthor.setText(String.valueOf(author.get(position)));
        holder.txtPages.setText(String.valueOf(pages.get(position)));
        holder.updateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UpdateActivity.class);
                intent.putExtra("id",String.valueOf(id.get(position)));
                intent.putExtra("title",String.valueOf(title.get(position)));
                intent.putExtra("author",String.valueOf(author.get(position)));
                intent.putExtra("pages",String.valueOf(pages.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtId, txtTitle, txtAuthor, txtPages;
        ConstraintLayout updateLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtPages = itemView.findViewById(R.id.txtPages);
            updateLayout = itemView.findViewById(R.id.updateLayout);
        }
    }
}
