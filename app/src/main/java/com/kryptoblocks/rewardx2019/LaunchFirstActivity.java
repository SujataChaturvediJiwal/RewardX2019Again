package com.kryptoblocks.rewardx2019;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kryptoblocks.rewardx2019.fragments.IntroScreenOneFragment;

public class LaunchFirstActivity extends AppCompatActivity {

    private void IntroScreenOneFragment() {

        IntroScreenOneFragment introScreenOneFragment = new IntroScreenOneFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.first_launch_frame, introScreenOneFragment);
        fragmentTransaction.commit();
    }


    private static final String FIRST_LAUNCH = "first_launch";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getBoolean(FIRST_LAUNCH, true)) {
            //Display window
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(FIRST_LAUNCH, false);
            edit.commit();

        } else {
            startActivity(new Intent(LaunchFirstActivity.this, SocialLoginActivity.class));
            finish();
            // Toast.makeText(this, "no lanch", Toast.LENGTH_SHORT).show();
        }

        setContentView(R.layout.activity_launch_first);

        IntroScreenOneFragment();
    }
}
