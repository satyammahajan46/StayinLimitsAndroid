package com.example.satyam.stayinlimits;

import android.app.Activity;
import android.os.Bundle;

import com.example.satyam.stayinlimits.infiniteloader.LoadApplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new LoadApplication(this).execute();
    }
}
