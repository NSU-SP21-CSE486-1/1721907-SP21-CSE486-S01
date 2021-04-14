package com.example.roomdatabase.viewmodel;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabase.R;
import com.example.roomdatabase.Room.Student;

import java.util.ArrayList;
import java.util.List;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.DisplayHolder> {

    List<Student> displays;
    Context context;
    

    public DisplayAdapter(List<Student> displays, Context context) {
        this.displays = displays;
        this.context = context;
    }

    @NonNull
    @Override
    public DisplayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new DisplayHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayHolder holder, int position) {
        holder.firstName.setText(displays.get(position).getSfirstName());
    }

    @Override
    public int getItemCount() {
        return displays.size();
    }



    class DisplayHolder extends RecyclerView.ViewHolder{

        private TextView firstName;

        public DisplayHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.firstNameId);
        }
    }

}

