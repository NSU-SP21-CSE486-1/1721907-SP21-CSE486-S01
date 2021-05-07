package com.example.memoryprofilertools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private ArrayList<Item> itemArrayList;
    // hold drawables arrays defined in strings.xml
    private TypedArray itemsThumbnailsTypedArray;
    private TypedArray itemsImagesTypedArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // initialize array list
        itemArrayList = new ArrayList<>();
        itemAdapter = new ItemAdapter(this, itemArrayList);
        recyclerView.setAdapter(itemAdapter);
        // populate recyclerView
        initializeData();
    }

    public void initializeData(){
        // arrays and typed arrays for names, prices, thumbnails and large images
        String[] itemNames = getResources().getStringArray(R.array.items_names);
        int[] itemsPrices = getResources().getIntArray(R.array.items_prices);
        itemsThumbnailsTypedArray = getResources().obtainTypedArray(R.array.items_thumbnails);
        itemsImagesTypedArray = getResources().obtainTypedArray(R.array.items_images);
        itemArrayList.clear();

        // put items into array list
        for (int i = 0; i < itemNames.length; i++){
            itemArrayList.add(new Item(itemNames[i], itemsPrices[i], itemsThumbnailsTypedArray.getResourceId(i, 0), itemsImagesTypedArray.getResourceId(i, 0)));
        }
        // recycle typed arrays and notify adapter for changes
        itemsThumbnailsTypedArray.recycle();
        itemsImagesTypedArray.recycle();
        itemAdapter.notifyDataSetChanged();
    }
}