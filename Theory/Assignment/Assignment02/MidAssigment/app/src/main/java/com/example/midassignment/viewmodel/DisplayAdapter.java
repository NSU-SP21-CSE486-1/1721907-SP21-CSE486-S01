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
            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.popup);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            holder.studentId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView name = dialog.findViewById(R.id.nameid);
                    TextView deptName = dialog.findViewById(R.id.deptId) ;

                    name.setText(searchData.get(holder.getAdapterPosition()).getFullName());
                    deptName.setText(searchData.get(holder.getAdapterPosition()).getDept());

                    dialog.show();


                }
            });


//            Dialog myDialog = new Dialog(context);
//            myDialog.setContentView(R.layout.popup_screen);
//            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//
//
//            holder.sNsuID.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    TextView dialog_Name = myDialog.findViewById(R.id.show_Name);
//                    TextView dialog_depName = myDialog.findViewById(R.id.show_dep);
//
//                    dialog_Name.setText(getSearchItem.get(holder.getAdapterPosition()).getFullname());
//                    dialog_depName.setText(getSearchItem.get(holder.getAdapterPosition()).getDepartmentName());
//
//                    myDialog.show();
//                }
//            });





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
