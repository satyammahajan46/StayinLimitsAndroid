package com.example.satyam.stayinlimits;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(isFirstRun()){
            Intent firstRun = new Intent(this, RegisterUser.class);
            startActivity(firstRun);
        }
        else{
            Intent normalRun  = new Intent(this, NormalRun.class);
            startActivity(normalRun);
//            Toast.makeText(this, "Not Implemented Yet!!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        if(isFirstRun()){
            Intent firstRun = new Intent(this, RegisterUser.class);
            startActivity(firstRun);
        }
        else{
            Intent normalRun  = new Intent(this, NormalRun.class);
            startActivity(normalRun);
//            Toast.makeText(this, "Not Implemented Yet!!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        //Implement exit method
        Toast.makeText(this, "Exit Menu", Toast.LENGTH_LONG);
    }

    private boolean isFirstRun(){
        final String PREFRENCE_FNAME = "prefs";
        final String VERSION = "version_code";
        final int NOTFOUND = -1;
        boolean isFRun = false;
        SharedPreferences pref = getSharedPreferences(PREFRENCE_FNAME, MODE_PRIVATE);
        int savedVersion = pref.getInt(VERSION, NOTFOUND);
        int currentVersion = BuildConfig.VERSION_CODE;
        //Not a First Run
        if(currentVersion == savedVersion)
            isFRun = false;
        //Is a first run
        else if(savedVersion == NOTFOUND)
            isFRun = true;
        //Code if an upgrade

        //changes in pref file
        pref.edit().putInt(VERSION, 1).commit();
        //return condition
        return true;
    }


}