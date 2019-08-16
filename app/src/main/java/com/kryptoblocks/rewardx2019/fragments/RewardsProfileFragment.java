package com.kryptoblocks.rewardx2019.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kryptoblocks.rewardx2019.AddProgramActivity;
import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.adapter.RegisteredVendorRewardsAdapter;
import com.kryptoblocks.rewardx2019.apiInterfaces.ApiInterface;
import com.kryptoblocks.rewardx2019.network.ApiClient;
import com.kryptoblocks.rewardx2019.pojo.GetRegisteredVendors;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.kryptoblocks.rewardx2019.TranslucentDiscoverActivity.flag_join;
//import static com.kryptoblocks.rewardx2019.SocialLoginActivity.user_uuid;

/**
 * A simple {@link Fragment} subclass.
 */
public class RewardsProfileFragment extends Fragment {


    TextView add_text_view;
    RecyclerView recyle_rewards;
    List<GetRegisteredVendors> rewardsPojos;
    RegisteredVendorRewardsAdapter rewardsAdapter;
    public static String vendor_reward_uuid;
    ApiInterface apiInterface;

    public static final String mypreferenceLogin = "mypref";
    SharedPreferences sharedPreferencesRewardsProfile;
    String user_id_rewardsProfile;

    public RewardsProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rewards_profile, container, false);


        add_text_view = view.findViewById(R.id.add_rewards_text);

        add_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // flag_join = 0;
                Intent i = new Intent(getContext(), AddProgramActivity.class);
                startActivity(i);
            }
        });

        recyle_rewards= view.findViewById(R.id.recycleView_rewards);

        sharedPreferencesRewardsProfile = this.getActivity().getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
        user_id_rewardsProfile = sharedPreferencesRewardsProfile.getString("user_unique_id","hi");

        //Toast.makeText(getContext(), "User id:" + user_id_rewardsProfile , Toast.LENGTH_SHORT).show();

        displayRegisteredVendor();


      //  rewardsSet();

        return view;
    }

   /* private void rewardsSet() {


        RewardsPojo rewardsPo = new RewardsPojo("Red Airline", "April", "2019", "1122334455 ");
        rewardsPojos.add(rewardsPo);

        rewardsPo = new RewardsPojo("Blue Airline", "Jan", "2017", "123444555");
        rewardsPojos.add(rewardsPo);

        rewardsPo = new RewardsPojo("Slate Gym", "July", "2015", "120000000 ");
        rewardsPojos.add(rewardsPo);

        rewardsPo = new RewardsPojo("Next Gym", "Feb", "2016", "908900000");
        rewardsPojos.add(rewardsPo);

        rewardsPo = new RewardsPojo("A Gym", "Dec", "2017", "162869300");
        rewardsPojos.add(rewardsPo);

        rewardsPo = new RewardsPojo("Pilot Gym", "Jan", "2018", "190000000");
        rewardsPojos.add(rewardsPo);

        rewardsPo = new RewardsPojo("Foodies Court", "Feb", "2019", "188000099 ");
        rewardsPojos.add(rewardsPo);

        rewardsPo = new RewardsPojo("30", "Company car", "Travel", "200 ");
        rewardsPojos.add(rewardsPo);

        rewardsPo = new RewardsPojo("21", "Company car", "Travel", "110 ");
        rewardsPojos.add(rewardsPo);

        rewardsPo = new RewardsPojo("5", "Company car", "Travel", "180 ");
        rewardsPojos.add(rewardsPo);

        rewardsAdapter.notifyDataSetChanged();
    }*/

    public void displayRegisteredVendor() {


        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
       // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        Call<GetRegisteredVendors> call1 = apiInterface.getRegisteredCustomers(user_id_rewardsProfile);

        System.out.println("callll====="+call1);

        call1.enqueue(new Callback<GetRegisteredVendors>() {


            @Override
            public void onResponse(Call<GetRegisteredVendors> call, Response<GetRegisteredVendors> response) {

                if(response.code()== 200) {
                    // if (passwordRegister == rePassword_register.getText().toString()) {
                    int statusCode = response.code();

                    System.out.println("Code" + statusCode);

                    System.out.println("body" + response.body().getData());

                    rewardsPojos = new ArrayList<>();
                    rewardsAdapter = new RegisteredVendorRewardsAdapter(getContext(), response.body().getData());
                    RecyclerView.LayoutManager subLayoutManager =
                            new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

                    recyle_rewards.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
                    recyle_rewards.setLayoutManager(subLayoutManager);

                    recyle_rewards.setItemAnimator(new DefaultItemAnimator());
                    recyle_rewards.setAdapter(rewardsAdapter);

                    Log.i(TAG, "  success to API." + response);
                    //Toast.makeText(getContext(), "Success register+++++++++", Toast.LENGTH_LONG).show();

                }

                else
                {
                    Log.i(TAG, "post not submitted to API." + response);
                    //Toast.makeText(getContext(), "Unsuccess register+++++++++", Toast.LENGTH_LONG).show();
                }
            }



            @Override
            public void onFailure(Call<GetRegisteredVendors> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to register API.");
                //Toast.makeText(getContext(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }


}
