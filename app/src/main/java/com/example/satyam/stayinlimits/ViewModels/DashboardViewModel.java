package com.example.satyam.stayinlimits.ViewModels;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.satyam.stayinlimits.DatabaseUtils.PrefDataFetch;
import com.example.satyam.stayinlimits.models.HomeFragData;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends AndroidViewModel {
    private MutableLiveData<HomeFragData> data;
    private PrefDataFetch preferences;
    private MutableLiveData<Boolean> isUpdating;
    public DashboardViewModel(@NonNull Application application) {
        super(application);

    }

    public LiveData<Boolean> isUpdating(){
        if(isUpdating==null){
            isUpdating = new MutableLiveData<Boolean>(false);
        }
        return isUpdating;
    }

    public LiveData<HomeFragData> getData(){
        if(data==null){
            data = new MutableLiveData<HomeFragData>();
            loadData();
        }
        return data;
    }

    private void loadData() {

        preferences = preferences.getInstance(getApplication().getApplicationContext());
        HomeFragData d = new HomeFragData(preferences.getAvailableMoney(), preferences.getBillingCycle(), preferences.getCreditLimit(), preferences.getCreditBalance());
        data.setValue(d);
    }
}
