package com.example.satyam.stayinlimits.infiniteloader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.example.satyam.stayinlimits.BuildConfig;
import com.example.satyam.stayinlimits.DatabaseUtils.PrefDataFetch;
import com.example.satyam.stayinlimits.NormalRunActivity;
import com.example.satyam.stayinlimits.RegisterUserActivity;
import com.example.satyam.stayinlimits.interfaces.ProgressBarHolder;


@SuppressWarnings("ALL")
public class LoadApplication extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    //private ProgressBarHolder progressBarHolder;


    public LoadApplication(Context c){
        context = c;
    }
  /*  @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if( (Activity)context instanceof ProgressBarHolder ){
            ((ProgressBarHolder) context).showProgressBarHolder();
            ((ProgressBarHolder) context).showProgressBar();
        }
        else throw new RuntimeException("ProgressBarHolder not implemented");

    }*/

    @Override
    protected void onPostExecute(Boolean result) {
        //progressDialog.dismiss();
        if(result){
            Intent firstRun = new Intent(context, RegisterUserActivity.class);
            context.startActivity(firstRun);
        }
        else{
            Intent run = new Intent(context, NormalRunActivity.class);
            context.startActivity(run);
        }
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        if(isFirstRun()){
            return true;
        }
        else{
            return false;
        }
    }


    private boolean isFirstRun(){

        boolean isFRun = false;
        SharedPreferences pref = context.getSharedPreferences(PrefDataFetch.PREFRENCE_FNAME, Context.MODE_PRIVATE);
        int savedVersion = pref.getInt(PrefDataFetch.VERSION, PrefDataFetch.NOTFOUND);
        int currentVersion = BuildConfig.VERSION_CODE;
        //Not a First Run
        if(currentVersion == savedVersion)
            isFRun = false;
            //Is a first run
        else if(savedVersion == PrefDataFetch.NOTFOUND)
            isFRun = true;
        //Code if an upgrade

        //changes in pref file
        //pref.edit().putInt(PrefDataFetch.VERSION, 1).commit();
        //return condition
        ;
        return isFRun;
    }


}
