package com.kryptoblocks.rewardx2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kryptoblocks.rewardx2019.adapter.ParticularVendorRewardsAdapter;
import com.kryptoblocks.rewardx2019.apiInterfaces.ApiInterface;
import com.kryptoblocks.rewardx2019.network.ApiClient;
import com.kryptoblocks.rewardx2019.pojo.GetAllIncentives;
import com.kryptoblocks.rewardx2019.pojo.GetAllIncentivesData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.kryptoblocks.rewardx2019.adapter.RegisteredVendorRewardsAdapter.vendorRewards_name;
import static com.kryptoblocks.rewardx2019.adapter.RegisteredVendorRewardsAdapter.vendor_reward_uuid;

public class AllVendorRewardsActivity extends AppCompatActivity {

    ApiInterface apiInterface;

    RecyclerView recyclerView_display_all_rewards_ofVendor;
    List<GetAllIncentives> getAllIncentives;
    ParticularVendorRewardsAdapter particularVendorRewardsAdapter;
    TextView empty_rewards, reward_merchant_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_vendor_rewards);

        recyclerView_display_all_rewards_ofVendor = findViewById(R.id.recyclerView_particular_vendor_reward);
        empty_rewards = findViewById(R.id.no_rewards_textView);
        reward_merchant_name = findViewById(R.id.reward_merchant_name);

        reward_merchant_name.setText(vendorRewards_name);

        displayRewardDetails();
    }

    public void displayRewardDetails() {


        apiInterface =  ApiClient.getInstance().getClient2().create(ApiInterface.class);


        Call<GetAllIncentives> call1 = apiInterface.getAllIncentives(vendor_reward_uuid);

        System.out.println("callll====="+call1);

        call1.enqueue(new Callback<GetAllIncentives>() {


            @Override
            public void onResponse(Call<GetAllIncentives> call, Response<GetAllIncentives> response) {

                    if (response.code() == 200 && !response.body().getData().isEmpty()) {
                        empty_rewards.setVisibility(View.INVISIBLE);
                        // if (passwordRegister == rePassword_register.getText().toString()) {
                        int statusCode = response.code();

                        System.out.println("Code" + statusCode);

                        System.out.println("body" + response.body().getData());

                        getAllIncentives = new ArrayList<>();
                        particularVendorRewardsAdapter = new ParticularVendorRewardsAdapter(getApplication(), response.body().getData());
                        RecyclerView.LayoutManager subLayoutManager = new GridLayoutManager(getApplication(), 2);

                        recyclerView_display_all_rewards_ofVendor.setLayoutManager(subLayoutManager);

                        recyclerView_display_all_rewards_ofVendor.setItemAnimator(new DefaultItemAnimator());
                        recyclerView_display_all_rewards_ofVendor.setAdapter(particularVendorRewardsAdapter);


                        //trial---------

                        try {
                           // JSONObject obj = new JSONObject(response.body().getData().toString());
                            JSONObject ob = new JSONObject();

                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            JSONArray heroArray = ob.getJSONArray(response.body().toString());

                            //now looping through all the elements of the json array
                            for (int i = 0; i < heroArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                //creating a hero object and giving them the values from json object
                                GetAllIncentivesData hero = new GetAllIncentivesData(heroObject.getString("image_link"), heroObject.getInt("points"), heroObject.getString("name"));
                            System.out.println("herosssssss--------------"+hero);

                            }
                            Log.i(TAG, "  success to API." + response);
                            Toast.makeText(getApplicationContext(), "Success register+++++++++", Toast.LENGTH_LONG).show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                else if(response.code()== 200 && response.body().getData().isEmpty())
                {
                    empty_rewards.setVisibility(View.VISIBLE);
                    Log.i(TAG, "Empty body." + response);
                    Toast.makeText(getApplicationContext(), "Empty body+++++++++", Toast.LENGTH_LONG).show();
                }
              else  {
                    Log.i(TAG, "post not submitted to API." + response);
                    Toast.makeText(getApplicationContext(), "Unsuccess register+++++++++", Toast.LENGTH_LONG).show();
                }
            }



            @Override
            public void onFailure(Call<GetAllIncentives> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to register API.");
                Toast.makeText(getApplicationContext(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }


    /*void loadHeroList() {
        //getting the progressbar

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_
                URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //hiding the progressbar after completion
                //  progressBar.setVisibility(View.INVISIBLE);


                try {
                    //getting the whole json object from the response
                    JSONObject obj = new JSONObject(response);

                    //we have the array named hero inside the object
                    //so here we are getting that json array
                    JSONArray heroArray = obj.getJSONArray("heroes");

                    //now looping through all the elements of the json array
                    for (int i = 0; i < heroArray.length(); i++) {
                        //getting the json object of the particular index inside the array
                        JSONObject heroObject = heroArray.getJSONObject(i);

                        //creating a hero object and giving them the values from json object
                        GetAllIncentivesData hero = new GetAllIncentivesData(heroObject.getString("name"), heroObject.getString("imageurl"));

                        //adding the hero to herolist
                        heroList.add(hero);
                    }

                    //creating custom adapter object
                    ListViewAdapter adapter = new ListViewAdapter(heroList, getApplicationContext());

                    //adding the adapter to listview
                    listView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }*/
}
