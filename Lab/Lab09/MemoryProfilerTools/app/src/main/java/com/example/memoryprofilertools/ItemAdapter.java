package com.example.memoryprofilertools;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private ArrayList<Item> itemArrayList;
    private Context context;

    ItemAdapter(Context context, ArrayList<Item> itemArrayList) {
        this.context = context;
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        Item currentItem = itemArrayList.get(position);
        holder.bindTo(currentItem);

    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mItemNameTextView;
        private TextView mItemPriceTextView;
        private ImageView imageLarge;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            mItemNameTextView = itemView.findViewById(R.id.itemNameTextView);
            mItemPriceTextView = itemView.findViewById(R.id.itemPriceTextView);
            imageLarge = itemView.findViewById(R.id.itemThumbnail);
            imageLarge.setOnClickListener(this);
        }

        void bindTo(Item currentItem){

            mItemNameTextView.setText(currentItem.getItemName());
            mItemPriceTextView.setText(context.getResources().getString(R.string.price_format, currentItem.getItemPrice()));
            Glide.with(context).load(currentItem.getImageLarge()).into(imageLarge);
        }

        @Override
        public void onClick(View v) {

            Item currentItem = itemArrayList.get(getAdapterPosition());
            final Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_DialogWhenLarge);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            dialog.setContentView(R.layout.dialog_layout);
            ImageView largeImage = dialog.findViewById(R.id.largeImageView);
            largeImage.setImageResource(currentItem.getImageLarge());
            Button button = dialog.findViewById(R.id.okButton);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }
}
