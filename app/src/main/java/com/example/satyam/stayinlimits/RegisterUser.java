package com.example.satyam.stayinlimits;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.lang.*;

public class RegisterUser extends Activity {
    private ExtendedFloatingActionButton nextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        nextButton = findViewById(R.id.submit);

        //getActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    protected void onStart(){
        super.onStart();
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Next is clicked", Snackbar.LENGTH_LONG).setAction("Action: ", null).show();
            }
        });

    }
/*
    public void processData(View v) {
        EditText name = (EditText) findViewById(R.id.eName);
        EditText age = (EditText) findViewById(R.id.eAge);
        EditText money = (EditText) findViewById(R.id.eCurrentBalance);
        EditText cLimit = (EditText) findViewById(R.id.eCreditLimit);
        EditText cBalance = (EditText) findViewById(R.id.eCreditBalance);
        TextView invalidName = (TextView) findViewById(R.id.invalidName);
        TextView invalidAge = (TextView) findViewById(R.id.invalidAge);
        TextView invalidMoney = (TextView) findViewById(R.id.invalidMoney);
        TextView invalidCLimit = (TextView) findViewById(R.id.invalidLimit);
        TextView invalidCBalance = (TextView) findViewById(R.id.invalidBalance);
        int iAge = Integer.MIN_VALUE;

        double dMoney, dCLimit, dCBalance;
        dMoney = dCBalance = dCLimit = Double.NaN;

        DatabaseBuilder db = new DatabaseBuilder(this);
        boolean isValid = true;
        boolean isACatch = false;
        if(name == null || name.getText().toString().isEmpty()){
            invalidName.setText("Please enter a valid name");
            invalidName.setVisibility(View.VISIBLE);
            isValid = false;
        }
        else{
            invalidName.setVisibility(View.GONE);
        }
        //age check
        try{
            iAge= Integer.parseInt(age.getText().toString());
        }
        catch(NumberFormatException e){
            invalidAge.setText("Required Field");
            invalidAge.setVisibility(View.VISIBLE);
            isValid = false;
            isACatch = true;

        }
        finally {
            if(!isACatch) {
                if (age == null || iAge == Integer.MIN_VALUE || (iAge <= 0 || iAge > 110)) {
                    invalidAge.setText("Enter a number greater than 0 and less than 110");
                    invalidAge.setVisibility(View.VISIBLE);
                    isValid = false;
                }
                else{
                    invalidAge.setVisibility(View.GONE);
                }
            }
            isACatch = false;
        }
        //Money check
        try{
            dMoney =  Double.parseDouble(money.getText().toString());
        }
        catch(NumberFormatException e){
            invalidMoney.setText("Required Field");
            invalidMoney.setVisibility(View.VISIBLE);
            isValid = false;
            isACatch = true;
        }
        finally {
            if(!isACatch) {
                if (money == null || dMoney == Double.NaN || dMoney < 0) {
                    invalidMoney.setText("Enter a number greater than 0");
                    invalidMoney.setVisibility(View.VISIBLE);
                    isValid = false;
                }
                else{
                    invalidMoney.setVisibility(View.GONE);
                }
            }
            isACatch = false;
        }

        //check credit limit
        try{
            dCLimit =  Double.parseDouble(cLimit.getText().toString());
        }
        catch(NumberFormatException e){
            invalidCLimit.setText("Required Field");
            invalidCLimit.setVisibility(View.VISIBLE);
            isValid = false;
            isACatch = true;
        }
        finally {
            if(!isACatch) {
                if(cLimit == null || dCLimit < 0){
                    invalidCLimit.setText("Enter a number greater than or equal to 0");
                    invalidCLimit.setVisibility(View.VISIBLE);
                    isValid = false;
                }
                else{
                    invalidCLimit.setVisibility(View.GONE);
                }
            }
            isACatch = false;
        }

        // check credit balance
        try{
            dCBalance =  Double.parseDouble(cBalance.getText().toString());
        }
        catch(NumberFormatException e){
            invalidCBalance.setText("Required Field");
            invalidCBalance.setVisibility(View.VISIBLE);
            isValid = false;
            isACatch = true;
        }
        finally {
            if(!isACatch) {
                if(cBalance == null || dCBalance < 0){
                    invalidCBalance.setText("Enter a number greater than 0");
                    invalidCBalance.setVisibility(View.VISIBLE);
                    isValid = false;
                }
                else{
                    invalidCBalance.setVisibility(View.GONE);
                }
            }
            //isACatch = false;
        }

        if (isValid && DatabaseBuilder.isOpenDatabase(db)) {
            db.addUserProfile(name.getText().toString(), iAge, dMoney, dCLimit, dCBalance);
            Intent normalRun = new Intent(this, NormalRun.class);
            startActivity(normalRun);
        }
    }
*/
}
