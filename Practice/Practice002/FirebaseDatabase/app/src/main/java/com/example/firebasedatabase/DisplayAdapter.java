package com.example.firebasedatabase;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.DisplayViewHolder> {

    Context context;
    ArrayList<Student> list;

    public DisplayAdapter(Context context, ArrayList<Student> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DisplayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new DisplayViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayViewHolder holder, int position) {

        Student student = list.get(position);
        holder.fName.setText(student.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class DisplayViewHolder extends RecyclerView.ViewHolder{

        TextView fName;

        public DisplayViewHolder(@NonNull View itemView) {
            super(itemView);

            fName = itemView.findViewById(R.id.firstNameId);
        }
    }
}
