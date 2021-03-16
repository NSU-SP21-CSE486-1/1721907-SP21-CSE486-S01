package com.example.shoppinglist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_ITEMS = "com.example.shoppinglist.extra.ITEMS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        Intent intent = getIntent();
    }

    @SuppressLint("NonConstantResourceId")
    public void selectItem(View view) {
        Intent return_items_intent = new Intent();

        switch (view.getId()){
            case R.id.button_Chocolate:
                return_items_intent.putExtra(EXTRA_ITEMS, "CHOCOLATE"); setResult(RESULT_OK, return_items_intent); finish();
                break;
            case R.id.button_oil:
                return_items_intent.putExtra(EXTRA_ITEMS, "OIL"); setResult(RESULT_OK, return_items_intent); finish();
                break;
            case R.id.button_milk:
                return_items_intent.putExtra(EXTRA_ITEMS, "MILK"); setResult(RESULT_OK, return_items_intent); finish();
                break;
            case R.id.button_eggs:
                return_items_intent.putExtra(EXTRA_ITEMS, "EGGS"); setResult(RESULT_OK, return_items_intent); finish();
                break;
            case R.id.button_cheese:
                return_items_intent.putExtra(EXTRA_ITEMS, "CHEESE"); setResult(RESULT_OK, return_items_intent); finish();
                break;
            case R.id.button_rice:
                return_items_intent.putExtra(EXTRA_ITEMS, "RICE"); setResult(RESULT_OK, return_items_intent); finish();
                break;
            case R.id.button_wheat:
                return_items_intent.putExtra(EXTRA_ITEMS, "WHEAT FLOUR"); setResult(RESULT_OK, return_items_intent); finish();
                break;
            case R.id.button_bread:
                return_items_intent.putExtra(EXTRA_ITEMS, "BREAD"); setResult(RESULT_OK, return_items_intent); finish();
                break;
            case R.id.button_sugar:
                return_items_intent.putExtra(EXTRA_ITEMS, "SUGAR"); setResult(RESULT_OK, return_items_intent); finish();
                break;
            case R.id.button_salt:
                return_items_intent.putExtra(EXTRA_ITEMS, "SALT"); setResult(RESULT_OK, return_items_intent); finish();
                break;
            default:
                Toast.makeText(SecondActivity.this, "THERE IS AN ERROR", Toast.LENGTH_LONG).show();
                break;
        }

    }
}

