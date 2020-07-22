package com.example.satyam.stayinlimits;


import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;


import com.example.satyam.stayinlimits.infiniteloader.ProcessRegistration;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;


import java.lang.*;
import java.util.Calendar;
import java.util.TimeZone;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterUserActivity extends AppCompatActivity {
    private ExtendedFloatingActionButton nextButton;
    private TextInputEditText dob;
    private MaterialDatePicker.Builder dobBuilder;
    private MaterialDatePicker dobPicker;
    private long today;
    private Calendar calendar;
    private CalendarConstraints.Builder dobCon;
    private View view;
    private ProcessRegistration processRegistration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        view = ((Activity)this).getWindow().getDecorView().findViewById(android.R.id.content);
        //calendar to pick first date
        calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        //set constraints
        dobCon = new CalendarConstraints.Builder();
        //elements
        nextButton = findViewById(R.id.submit);
        dob = findViewById(R.id.eDOB);
        //build material calendar
        dobBuilder = MaterialDatePicker.Builder.datePicker();
        today = MaterialDatePicker.todayInUtcMilliseconds();
        calendar.setTimeInMillis(today);
        dobBuilder.setTitleText("Date of Birth");
        dobBuilder.setSelection(today);
        dobCon.setValidator(DateValidatorPointBackward.now());
        dobBuilder.setCalendarConstraints(dobCon.build());
        dobPicker = dobBuilder.build();
        //getActionBar().setDisplayHomeAsUpEnabled(false);
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d("REGISTER", "ACTIVITY PAUSED");
    }

    @Override
    protected void onStart(){
        super.onStart();
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 4/4/2020 Invoke asyncx loader
                 Log.i("ASYMNC", today+"");
                 new ProcessRegistration(RegisterUserActivity.this, view).execute(today);
            }
        });

        dob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    dobPicker.show(getSupportFragmentManager(), "DOB_PICKER");
                }
            }
        });
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dobPicker.show(getSupportFragmentManager(), "DOB_PICKER");
            }
        });


        dobPicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                final Calendar stamp = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                long timeStamp = Long.parseLong(selection.toString());
                stamp.setTimeInMillis(timeStamp);
                int year = stamp.get(Calendar.YEAR);
                int month = stamp.get(Calendar.MONTH) + 1;
                int date = stamp.get(Calendar.DAY_OF_MONTH);
                //TODO: might have to check preceding 0's
                dob.setText(month + "/" + date + "/" + year);
                dob.setError(null);
            }
        });


        dobPicker.addOnNegativeButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dob.getText().toString().isEmpty())
                    dob.setError("Required Field");
            }
        });


    }



}
