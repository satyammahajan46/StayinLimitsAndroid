package com.example.satyam.stayinlimits.infiniteloader;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;

import com.example.satyam.stayinlimits.DatabaseUtils.DatabaseBuilder;
import com.example.satyam.stayinlimits.DatabaseUtils.PrefDataFetch;
import com.example.satyam.stayinlimits.NormalRunActivity;
import com.example.satyam.stayinlimits.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.ContentLoadingProgressBar;


@SuppressWarnings("ALL")
public class ProcessRegistration extends AsyncTask<Long, Void, Boolean> {
    private Context context;
    private View view;
    private double dMoney, dCLimit, dCBalance;
    private boolean isValid;
    private boolean isACatch;
    private TextInputEditText name;
    private TextInputEditText dob;
    private  TextInputEditText money;
    private TextInputEditText cLimit;
    private TextInputEditText cBalance;
    private ExtendedFloatingActionButton button;
    private AlphaAnimation inAnimation, outAnimation;
    private CoordinatorLayout progressBarHolder;
    private ContentLoadingProgressBar progressBar;
    private HashMap<TextInputEditText, String> errorMessage;
    public ProcessRegistration(Context c, View v){
        context = c;
        view = v;
        dMoney = dCBalance = dCLimit = Double.NaN;
        isValid = true;
        isACatch = false;
        name = (TextInputEditText) view.findViewById(R.id.eName);
        dob = (TextInputEditText) view.findViewById(R.id.eDOB);
        money = (TextInputEditText) view.findViewById(R.id.eCurrentBalance);
        cLimit = (TextInputEditText) view.findViewById(R.id.eCreditLimit);
        cBalance = (TextInputEditText) view.findViewById(R.id.eCreditBalance);
        button = view.findViewById(R.id.submit);
        progressBarHolder = view.findViewById(R.id.progressBarHolder);
        progressBar = view.findViewById(R.id.progressBar);
        errorMessage = new HashMap<>(2);
    }
    @Override
    protected void onPreExecute() {
        inAnimation = new AlphaAnimation(0f, 1f);
        inAnimation.setDuration(200);
        progressBarHolder.setAnimation(inAnimation);
        progressBarHolder.setVisibility(View.VISIBLE);
        progressBar.show();
        button.setEnabled(false);
        //String regex = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";

    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        for (Map.Entry<TextInputEditText, String> entry : errorMessage.entrySet()) {
            entry.getKey().setError(entry.getValue());
        }
        outAnimation = new AlphaAnimation(1f, 0f);
        outAnimation.setDuration(200);
        progressBarHolder.setAnimation(outAnimation);
        progressBar.hide();
        progressBarHolder.setVisibility(View.GONE);
        button.setEnabled(true);
        if(aBoolean){
            //progressDialog.setMessage("Successful!");
            Toast.makeText(context, "Successful", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, NormalRunActivity.class);
            context.startActivity(intent);
        }
        else{
            Toast.makeText(context, "Invalid Entry", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected Boolean doInBackground(Long... longs) {
        long today = longs[0];

        return processData(view, today);
    }

    private void sharedPrefInput(int day, double limit, double balance, double money){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PrefDataFetch.DATA_FILE, Context.MODE_PRIVATE);
        SharedPreferences pref = context.getSharedPreferences(PrefDataFetch.PREFRENCE_FNAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(PrefDataFetch.BILLING_DAY, day).commit();
        sharedPreferences.edit().putFloat(PrefDataFetch.CREDIT_LIMIT, (float) limit).commit();
        sharedPreferences.edit().putFloat(PrefDataFetch.CREDIT_BALANCE, (float) balance).commit();
        sharedPreferences.edit().putFloat(PrefDataFetch.AVAILABLE_MONEY, (float) money).commit();
        //insert first run complete
        pref.edit().putInt(PrefDataFetch.VERSION, 1).commit();
    }

    private Boolean processData(View v, long today) {
        String regex = "^([0]?[0-9]|1[0-2])/([0-2]?[0-9]|3[0-1])/([12][0-9]{3})$";
        Pattern dobPattern = Pattern.compile(regex);

        if(name == null || name.getText().toString().isEmpty()){
            //name.setError("Required Field");
            errorMessage.put(name, "Required Field");
            isValid = false;
        }
        //age check
        if(dob == null || dob.getText().toString().isEmpty()) {
            //dob.setError("Required Field");
            errorMessage.put(dob, "Required Field");
            isValid = false;
        }
        else{
            Matcher dobMatch = dobPattern.matcher(dob.getText().toString());
            if(!dobMatch.matches()){
                errorMessage.put(dob, "Invalid Entry");
                //dob.setError("Invalid Entry");
                isValid = false;
            }
        }

        //Money check
        try{
            if(money.getText().toString().isEmpty()){
                //money.setError("Required Field");
                errorMessage.put(money, "Required Field");
                isValid = false;
            }
            else{
                dMoney =  Double.parseDouble(money.getText().toString());
            }

        }
        catch(NumberFormatException e){
            //money.setError("Invalid Entry");
            errorMessage.put(money, "Invalid Entry");
            isValid = false;
            isACatch = true;
        }
        finally {
            if(!isACatch) {
                if (money == null || dMoney == Double.NaN || dMoney < 0) {
                    //money.setError("Enter a number greater than 0");
                    errorMessage.put(money, "Enter a number greater than 0");
                    isValid = false;
                }
            }
            isACatch = false;
        }

        //check credit limit
        try{
            if(cLimit.getText().toString().isEmpty()){
                //cLimit.setError("Required Field");
                errorMessage.put(cLimit, "Required Field");
                isValid = false;
            }
            else{
                dCLimit =  Double.parseDouble(cLimit.getText().toString());
            }
        }
        catch(NumberFormatException e){
            //cLimit.setError("Invalid Entry");
            errorMessage.put(cLimit, "Invalid Entry");
            isValid = false;
            isACatch = true;
        }
        finally {
            if(!isACatch) {
                if(cLimit == null || dCLimit < 0){
                    //cLimit.setError("Enter a number greater than or equal to 0");
                    errorMessage.put(cLimit, "Enter a number greater than or equal to 0");
                    isValid = false;
                }
            }
            isACatch = false;
        }

        // check credit balance
        try{
            if(cBalance.getText().toString().isEmpty()){
                //cBalance.setError("Required Field");
                errorMessage.put(cBalance, "Required Field");
                isValid = false;
            }
            else {
                dCBalance = Double.parseDouble(cBalance.getText().toString());
            }
        }
        catch(NumberFormatException e){
            //cBalance.setError("Invalid Entry");
            errorMessage.put(cBalance, "Invalid Entry");
            isValid = false;
            isACatch = true;
        }
        finally {
            if(!isACatch) {
                if(cBalance == null || dCBalance < 0){
                    //cBalance.setError("Enter a number greater than 0");
                    errorMessage.put(cBalance, "Enter a number greater than");
                    isValid = false;
                }
            }
            //isACatch = false;
        }

        DatabaseBuilder db = new DatabaseBuilder(context);
        // create a date stamp
        final Calendar stamp = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        stamp.setTimeInMillis(today);
        int year = stamp.get(Calendar.YEAR);
        int month = stamp.get(Calendar.MONTH) + 1;
        int date = stamp.get(Calendar.DAY_OF_MONTH);
        String stampDate = month + "/" + date + "/" + year;
        if (isValid && DatabaseBuilder.isOpenDatabase(db)) {
            //Snackbar.make(v, "Data processed", Snackbar.LENGTH_LONG).setAction("Action: ", null).show();
            db.addUserProfile(name.getText().toString(), dob.getText().toString(), dMoney, dCLimit, dCBalance, stampDate);
            sharedPrefInput(date, dCLimit, dCBalance, dMoney);
            //Intent normalRun = new Intent(this, NormalRun.class);
            //startActivity(normalRun);
            return true;
        }
        return false;
    }
}
