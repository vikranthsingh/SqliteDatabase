package com.example.sqlitedatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {
    private Context context;
    ArrayList id, title, author, pages;

    public Myadapter(Context context, ArrayList id, ArrayList title, ArrayList author, ArrayList pages) {
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
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtId, txtTitle, txtAuthor, txtPages;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtPages = itemView.findViewById(R.id.txtPages);
        }
    }
}
