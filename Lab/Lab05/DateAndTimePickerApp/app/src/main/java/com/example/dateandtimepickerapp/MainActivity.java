package com.example.dateandtimepickerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView dateTextView;
    private TextView timeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateTextView = findViewById(R.id.dateTextId);
        timeTextView = findViewById(R.id.timeTextId);
    }

    public void datePickerResult(int y, int m, int d) {

       String year = Integer.toString(y);
       String month = Integer.toString(m+1);
       String date = Integer.toString(d);
       String dmessage = (d + "/" + m + "/" + y );

       String datemessage = ("Your Setting Date is     " + dmessage);
       dateTextView.setText(datemessage);


    }

    public void DatePicker(View view) {

        DialogFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "datePicker");

    }


    public void TimePicker(View view) {

        DialogFragment fragment = new TimePickerFragment();
        fragment.show(getSupportFragmentManager(), "timePicker");

    }

    public void timePickerResult(int h, int m) {

        String hour = Integer.toString(h);
        String minute = Integer.toString(m);
        String tmessage = (hour + " : " +minute);

        String timeMessage = ("Your Setting Time is     " + tmessage);
        timeTextView.setText(timeMessage);

    }
}