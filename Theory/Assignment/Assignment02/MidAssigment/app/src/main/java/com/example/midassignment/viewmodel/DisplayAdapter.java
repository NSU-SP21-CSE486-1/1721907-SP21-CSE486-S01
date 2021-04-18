package com.example.midassignment.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.midassignment.R;
import com.example.midassignment.Room.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
        holder.studentId.setText(displays.get(position).getStudentId());
    }

    @Override
    public int getItemCount() {
        return displays.size();
    }


    class DisplayHolder extends RecyclerView.ViewHolder{

        private TextView studentId;

        public DisplayHolder(@NonNull View itemView) {
            super(itemView);
            studentId = itemView.findViewById(R.id.studentId);
        }
    }
}
