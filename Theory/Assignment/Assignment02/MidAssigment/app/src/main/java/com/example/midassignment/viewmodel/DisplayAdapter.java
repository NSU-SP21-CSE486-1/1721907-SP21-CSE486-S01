package com.example.midassignment.viewmodel;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

        List<Student> searchData;

        public DisplayAdapter(List<Student> displays, Context context) {
            this.displays = displays;
            this.context = context;
            this.searchData = displays;
        }

        @NonNull
        @Override
        public DisplayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new DisplayHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull DisplayHolder holder, int position) {
            holder.studentId.setText(String.valueOf(searchData.get(position).getStudentId()));

        }

        @Override
        public int getItemCount() {
            return searchData.size();
        }

        class DisplayHolder extends RecyclerView.ViewHolder{

            private TextView studentId;

            public DisplayHolder(@NonNull View itemView) {
                super(itemView);
                studentId = itemView.findViewById(R.id.studentId);
            }
        }
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String key = charSequence.toString();
                if (key.isEmpty()) {
                    searchData = displays;
                } else {
                    List<Student> searchbar = new ArrayList<>();
                    for (Student row : displays) {
                        if (String.valueOf(row.getStudentId()).toLowerCase().contains(key.toLowerCase())) {
                            searchbar.add(row);
                        }
                    }
                    searchData = searchbar;
                }
                FilterResults searchResult = new FilterResults();
                searchResult.values = searchData;
                return searchResult;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults searchResults) {

                searchData = (List<Student>) searchResults.values;
                notifyDataSetChanged();

            }
        };
    }
}
