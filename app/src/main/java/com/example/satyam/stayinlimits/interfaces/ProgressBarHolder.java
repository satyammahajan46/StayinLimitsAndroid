package com.example.satyam.stayinlimits.interfaces;

import android.widget.ProgressBar;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

public interface ProgressBarHolder {

    void showProgressBarHolder();
    void showProgressBar();
    CoordinatorLayout getProgressBarHolder();
    ProgressBar getProgressBar();
    void removeProgressBarHolder();
    void removeProgressBar();

}
