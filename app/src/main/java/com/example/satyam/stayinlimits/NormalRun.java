package com.example.satyam.stayinlimits;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class NormalRun extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_run);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        setHeading();
    }

    private void setHeading() {
        TextView wel =  (TextView) findViewById(R.id.normalRun);
        DatabaseBuilder db = new DatabaseBuilder(this);
        if(DatabaseBuilder.isOpenDatabase(db)) {
            String set = db.fetchUserData();
            wel.setText( getString(R.string.normalRunWelcome) + set);
        }
    }

}
