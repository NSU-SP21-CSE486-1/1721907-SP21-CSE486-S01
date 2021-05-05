package com.example.midassignment.UIController;

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
import com.example.midassignment.Firebase.Models.Student;

import java.util.ArrayList;
import java.util.List;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.DisplayViewHolder> {

    Context context;
    ArrayList<Student> list;

    ArrayList<Student> searchData;

    public DisplayAdapter(Context context, ArrayList<Student> list) {
        this.context = context;
        this.list = list;
        this.searchData = list;
    }

    @NonNull
    @Override
    public DisplayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new DisplayViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayViewHolder holder, int position) {

        Student student = searchData.get(position);
        holder.mStudentId.setText(String.valueOf(student.getStudentId()));

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.popup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        holder.mStudentId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView name = dialog.findViewById(R.id.nameid);
                TextView deptName = dialog.findViewById(R.id.deptId) ;

                name.setText(searchData.get(holder.getAdapterPosition()).getFullName());
                deptName.setText(searchData.get(holder.getAdapterPosition()).getDept());

                dialog.show();


            }
        });

    }

    @Override
    public int getItemCount() {
        return searchData.size();
    }

    public static class DisplayViewHolder extends RecyclerView.ViewHolder{

        TextView mStudentId;

        public DisplayViewHolder(@NonNull View itemView) {
            super(itemView);

            mStudentId = itemView.findViewById(R.id.studentId);
        }
    }


    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String key = charSequence.toString();
                if (key.isEmpty()) {
                    searchData = list;
                } else {
                    ArrayList<Student> searchbar = new ArrayList<>();
                    for (Student row : list) {
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

                searchData = (ArrayList<Student>) searchResults.values;
                notifyDataSetChanged();

            }
        };
    }

}

