package com.example.midassignment.UIController;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.midassignment.R;
import com.example.midassignment.Room.Models.Student;

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
        holder.mStudentId.setText(String.valueOf(student.getStudentId()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class DisplayViewHolder extends RecyclerView.ViewHolder{

        TextView mStudentId;

        public DisplayViewHolder(@NonNull View itemView) {
            super(itemView);

            mStudentId = itemView.findViewById(R.id.studentId);
        }
    }
}

