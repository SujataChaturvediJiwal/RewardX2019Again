package com.kryptoblocks.rewardx2019;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kryptoblocks.rewardx2019.apiInterfaces.ApiInterface;
import com.kryptoblocks.rewardx2019.fragments.DiscoverFragment;
import com.kryptoblocks.rewardx2019.network.ApiClient;
import com.kryptoblocks.rewardx2019.pojo.IsCustomerRegistered;
import com.kryptoblocks.rewardx2019.pojo.LoginCustomerOutput;
import com.kryptoblocks.rewardx2019.pojo.RegisterToRewardsProgramInput;
import com.kryptoblocks.rewardx2019.pojo.RegisterToRewardsProgramOutput;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import static com.kryptoblocks.rewardx2019.SocialLoginActivity.user_uuid;

//import static com.kryptoblocks.rewardx2019.SocialLoginActivity.user_uuid;
//import static com.kryptoblocks.rewardx2019.adapter.DiscoverAdapter.vendor_uuid;

public class DiscoverAddProgramsActivity extends AppCompatActivity {

    private void DiscoverFragment() {

        DiscoverFragment discoverFragment = new DiscoverFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, discoverFragment);
        fragmentTransaction.commit();
    }

    Button btn_addRewardPrograms;
    ApiInterface apiInterface;
    String vendor_uuid;
    private static final String TAG = DiscoverAddProgramsActivity.class.getSimpleName();

    public static final String mypreferenceLogin = "mypref";
    SharedPreferences sharedPreferencesDiscoverAddProg;
    String user_id_discover_add_pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_add_programs);

        sharedPreferencesDiscoverAddProg = getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
        user_id_discover_add_pro = sharedPreferencesDiscoverAddProg.getString("user_unique_id","hi");

       // Toast.makeText(DiscoverAddProgramsActivity.this, "User id:" + user_id_discover_add_pro , Toast.LENGTH_SHORT).show();

        btn_addRewardPrograms = findViewById(R.id.button_joinRewardsProgram);
        btn_addRewardPrograms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent i = new Intent(getApplication(),AddProgramActivity.class);
                startActivity(i);*/
                isRegistered();

            }
        });
    }


  /*  public void registerToRewardsProgram() {

        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
       // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        //Call<LoginCustomerOutput> call1 =  apiInterface.customerLogin("abc","123");

        RegisterToRewardsProgramInput rewardsProgramInput = new RegisterToRewardsProgramInput();

        rewardsProgramInput.setUserUuid(user_id_discover_add_pro);
       // rewardsProgramInput.setVendorUuid(vendor_uuid);
        rewardsProgramInput.setRegisteredVia("mobile");


        System.out.println("userId======"+ user_id_discover_add_pro);
        //System.out.println("vendorId======"+vendor_uuid);


        Call<RegisterToRewardsProgramOutput> callLogin =  apiInterface.registerToRewardsProgram(rewardsProgramInput);


        System.out.println("callll====="+callLogin);

        callLogin.enqueue(new Callback<RegisterToRewardsProgramOutput>() {


            @Override
            public void onResponse(Call<RegisterToRewardsProgramOutput> call, Response<RegisterToRewardsProgramOutput> response) {

              *//*  System.out.println("call====="+call);
                System.out.println("body" + response.body());
                int statusCode = response.code();
                System.out.println("Code" + statusCode);*//*

                try {

                    if (response.code()==200) {

                        int statusCode = response.code();

                    System.out.println("Code" + statusCode);

                        System.out.println("body" + response.body().toString());


                        *//*Intent i = new Intent(getApplication(), DiscoverAddProgramsActivity.class);
                        startActivity(i);*//*
                        DiscoverFragment();

                        Log.i(TAG, "login successful" + response);
                    }
                    else {
                        Log.i(TAG, "post not submitted to API." + response.errorBody());
                        Toast.makeText(getApplicationContext(), "Unsuccess login+++++++++", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error----------"+e);
                }
            }


            @Override
            public void onFailure(Call<RegisterToRewardsProgramOutput> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to login API.");
                Toast.makeText(getApplicationContext(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }*/

    public void isRegistered() {


        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
       // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        Call<IsCustomerRegistered> callLogin =  apiInterface.isCustomerRegistered(user_id_discover_add_pro,vendor_uuid);


        System.out.println("callll====="+callLogin);

        callLogin.enqueue(new Callback<IsCustomerRegistered>() {


            @Override
            public void onResponse(Call<IsCustomerRegistered> call, Response<IsCustomerRegistered> response) {


                try {

                    if((response.code()==200)&&(response.body().toString().isEmpty())) {

                        int statusCode = response.code();

                        System.out.println("Code" + statusCode);

                        System.out.println("body" + response.body().toString());

                        //registerToRewardsProgram();

                        /*Intent i = new Intent(getApplication(), DiscoverAddProgramsActivity.class);
                        startActivity(i);*/
                      //  DiscoverFragment();

                        Log.i(TAG, " successful" + response);
                    }
                    else {
                        Log.i(TAG, "post not submitted to API." + response);
                        //Toast.makeText(getApplicationContext(), "Unsuccess +++++++++", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error----------"+e);
                }
            }


            @Override
            public void onFailure(Call<IsCustomerRegistered> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to  API.");
                //Toast.makeText(getApplicationContext(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }
}





