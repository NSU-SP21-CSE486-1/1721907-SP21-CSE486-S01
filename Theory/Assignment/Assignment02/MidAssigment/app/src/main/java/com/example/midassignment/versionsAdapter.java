package com.example.midassignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class versionsAdapter extends RecyclerView.Adapter<versionsAdapter.VersionVH>{

    List<versions> versionsList;

    public versionsAdapter(List<versions> versionsList) {
        this.versionsList = versionsList;
    }

    @NonNull
    @Override
    public VersionVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new VersionVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VersionVH holder, int position) {

        versions versions =versionsList.get(position);
        holder.presentAddress.setText(versions.getPresentAddress());
        holder.country.setText(versions.getCountry());
        holder.district.setText(versions.getDistrict());
        holder.postOffice.setText(versions.getPostOffice());
        holder.policeStation.setText(versions.getPoliceStation());
        holder.house.setText(versions.getHouse());
        holder.road.setText(versions.getRoad());

        boolean isExpandable = versionsList.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
       return  versionsList.size();
    }

    public class VersionVH extends RecyclerView.ViewHolder {

        EditText country, district, postOffice, policeStation, postalCode, house, road;
        TextView presentAddress;

        LinearLayout linearLayout;
        RelativeLayout expandableLayout;


        public VersionVH(@NonNull View itemView) {
            super(itemView);

            presentAddress = itemView.findViewById(R.id.presentAddress_Id1);
            country = itemView.findViewById(R.id.country_Id);
            district = itemView.findViewById(R.id.district_Id);
            postOffice = itemView.findViewById(R.id.postOffice_Id);
            policeStation = itemView.findViewById(R.id.policeStation_Id);
            house = itemView.findViewById(R.id.house_Id);
            road = itemView.findViewById(R.id.Road_Id);


            linearLayout = itemView.findViewById(R.id.linear_layout);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);


            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    versions versions = versionsList.get(getAdapterPosition());
                    versions.setExpandable(!versions.isExpandable());
                    notifyItemChanged(getAdapterPosition());

                }
            });
        }
    }

}
