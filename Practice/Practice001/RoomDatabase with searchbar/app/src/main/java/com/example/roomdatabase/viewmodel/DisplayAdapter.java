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
        holder.firstName.setText(searchData.get(position).getSfirstName());
    }

    @Override
    public int getItemCount() {
        return searchData.size();
    }



    class DisplayHolder extends RecyclerView.ViewHolder{

        private TextView firstName;

        public DisplayHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.firstNameId);
        }
    }
    public Filter getFilter(){

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence  charSequence ) {
               String key = charSequence.toString();
               if(key.isEmpty()){
                   searchData = displays;
               }
               else{
                   List<Student> searchbar = new ArrayList<>();
                   for(Student row: displays){
                       if (row.getSfirstName().toLowerCase().contains(key.toLowerCase())){
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
