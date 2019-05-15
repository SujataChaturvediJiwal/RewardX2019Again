package com.kryptoblocks.rewardx2019;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kryptoblocks.rewardx2019.fragments.AccountsFragment;
import com.kryptoblocks.rewardx2019.fragments.DistanceMenuFragment;
import com.kryptoblocks.rewardx2019.fragments.FilterMenuOptionsFragment;

import org.w3c.dom.Text;

public class FilterActivity extends AppCompatActivity {


    private void FilterMenuOptionsFragment() {

        FilterMenuOptionsFragment filterMenuOptionsFragment = new FilterMenuOptionsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_filter, filterMenuOptionsFragment);
        fragmentTransaction.commit();
    }
    private void DistanceMenuFragment() {

        DistanceMenuFragment distanceMenuFragment = new DistanceMenuFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_filter, distanceMenuFragment);
        fragmentTransaction.commit();
    }
    TextView options_text, distance_menu_filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        options_text = findViewById(R.id.options_menu_filter);
        distance_menu_filter = findViewById(R.id.distance_menu_filter);

        options_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilterMenuOptionsFragment();
            }
        });

        distance_menu_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DistanceMenuFragment();
            }
        });
    }
}
