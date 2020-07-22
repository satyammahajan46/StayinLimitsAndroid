package com.example.satyam.stayinlimits;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.satyam.stayinlimits.ViewModels.DashboardViewModel;
import com.example.satyam.stayinlimits.models.HomeFragData;
import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private TextInputEditText totalMoney;
    private TextInputEditText goalMoney;
    private TextInputEditText eCreditMoney;
    private TextInputEditText eCreditBalance;
    private DashboardViewModel viewModel;
    private ProgressBar progressBar;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        totalMoney = view.findViewById(R.id.totalMoney);
        goalMoney = view.findViewById(R.id.goalMoney);
        eCreditBalance = view.findViewById(R.id.eCardBalance);
        eCreditMoney = view.findViewById(R.id.eCardLimit);
        progressBar = view.findViewById(R.id.homeFragmentProgress);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this, getDefaultViewModelProviderFactory()).get(DashboardViewModel.class);

        viewModel.isUpdating().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    //Todo: show progress bar
                    progressBar.setVisibility(View.VISIBLE);
                }
                else{
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

        });

        viewModel.getData().observe(getViewLifecycleOwner(), new Observer<HomeFragData>() {
            @Override
            public void onChanged(HomeFragData homeFragData) {
                //TODO: what to do with data
                totalMoney.setText(homeFragData.getSavings() + "");
                goalMoney.setText(homeFragData.getGoal() + "");
                eCreditBalance.setText(homeFragData.getBalance() + "");
                eCreditMoney.setText(homeFragData.getLimit() + "");
            }
        });


    }
}
