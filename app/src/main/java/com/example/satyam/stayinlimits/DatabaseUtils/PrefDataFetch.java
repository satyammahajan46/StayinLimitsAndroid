package com.example.satyam.stayinlimits.DatabaseUtils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefDataFetch {
    public static final String CREDIT_LIMIT = "CREDIT_LIMIT";
    public static final String CREDIT_BALANCE = "CREDIT_BALANCE";
    public static final String AVAILABLE_MONEY = "AVAILABLE_MONEY";
    public static final String DATA_FILE = "DATA_FILE";
    public static final String BILLING_DAY = "BILLING_CYCLE";
    public static final String PREFRENCE_FNAME = "prefs";
    public static final String VERSION = "version_code";
    public static final int NOTFOUND = -1;
    private SharedPreferences dataFile;
    private Context context;

    private static PrefDataFetch instance;

    public static synchronized PrefDataFetch getInstance(Context c){
        if(instance == null){
            instance = new PrefDataFetch(c);
        }
        return instance;
    }

    public PrefDataFetch(Context c) {
        context = c;
        dataFile = c.getSharedPreferences(this.DATA_FILE, Context.MODE_PRIVATE);
    }

    public double getCreditLimit(){
        return dataFile.getFloat(this.CREDIT_LIMIT, Float.NaN);
    }

    public double getCreditBalance(){
        return dataFile.getFloat(this.CREDIT_BALANCE, Float.NaN);
    }

    public double getAvailableMoney(){
        return dataFile.getFloat(this.AVAILABLE_MONEY, Float.NaN);
    }

    public double getBillingCycle(){
        return dataFile.getInt(this.BILLING_DAY, Integer.MIN_VALUE);
    }


}
