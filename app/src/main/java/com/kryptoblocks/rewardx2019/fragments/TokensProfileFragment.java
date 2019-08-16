package com.kryptoblocks.rewardx2019.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kryptoblocks.rewardx2019.CashOutActivity;
import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.adapter.AllVendorsListAdapter;
import com.kryptoblocks.rewardx2019.adapter.DiscoverTabForOffersAdapter;
import com.kryptoblocks.rewardx2019.adapter.NewAdapter;
import com.kryptoblocks.rewardx2019.adapter.SearchSubCategoriesAdapter;
import com.kryptoblocks.rewardx2019.apiInterfaces.ApiInterface;
import com.kryptoblocks.rewardx2019.network.ApiClient;
import com.kryptoblocks.rewardx2019.pojo.GetAllVendors;
import com.kryptoblocks.rewardx2019.pojo.GetAllVendorsData;
import com.kryptoblocks.rewardx2019.pojo.ParentPojo;
import com.kryptoblocks.rewardx2019.pojo.SearchAllSubCategories;
import com.kryptoblocks.rewardx2019.pojo.TotalCustomerRewardPoints;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;
import static com.kryptoblocks.rewardx2019.SocialLoginActivity.mypreferenceLogin;
import static com.kryptoblocks.rewardx2019.SocialLoginActivity.user_fullName;
import static com.kryptoblocks.rewardx2019.SocialLoginActivity.user_uuid;
import static com.kryptoblocks.rewardx2019.adapter.SearchMainCategoriesAdapter.search_category_id;

/**
 * A simple {@link Fragment} subclass.
 */
public class TokensProfileFragment extends Fragment {


    public TokensProfileFragment() {
        // Required empty public constructor
    }
    ApiInterface apiInterface;
    TextView cashOut_text;
    TextView total_points;
    SharedPreferences sharedPreferencesToeknProfileFrag;
    String user_id_total_reward_points;
    public  String totalCustomerRewardPoints;
    RecyclerView recycle_registeredVendors;
    NewAdapter newAdapter;
    List<GetAllVendorsData> getAllVendorsList;
    AllVendorsListAdapter allVendorsListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tokens_profile, container, false);


       // cashOut_text = view.findViewById(R.id.cash_out_textView);
        //total_points = view.findViewById(R.id.total_customer_tokens_textView);
        recycle_registeredVendors = view.findViewById(R.id.recyclerView_registeredVendors);

       /* cashOut_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), CashOutActivity.class);
                startActivity(i);
            }
        });*/

        sharedPreferencesToeknProfileFrag = this.getActivity().getSharedPreferences(mypreferenceLogin, MODE_PRIVATE);
        //retrieving data of shared preferences
        user_id_total_reward_points = sharedPreferencesToeknProfileFrag.getString("user_unique_id","hi");

       // getTotalCustomerRewardPoints();
        fetchAllRegisteredVendorNames();

        return view;
    }

    public void getTotalCustomerRewardPoints() {


        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
        // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        Call<TotalCustomerRewardPoints> call1 = apiInterface.getTotalCustomerRewardPoints(user_id_total_reward_points);

        System.out.println("callll====="+call1);

        call1.enqueue(new Callback<TotalCustomerRewardPoints>() {


            @Override
            public void onResponse(Call<TotalCustomerRewardPoints> call, Response<TotalCustomerRewardPoints> response) {

                if(response.code()== 200) {
                    // if (passwordRegister == rePassword_register.getText().toString()) {

                    int statusCode = response.code();

                    System.out.println("Code" + statusCode);

                    System.out.println("body" + response.body().getData());

                     totalCustomerRewardPoints = response.body().getData().toString();
                    System.out.println("Total points-------------"+totalCustomerRewardPoints);
                    total_points.setText(totalCustomerRewardPoints);

                    SharedPreferences.Editor total_points_pref = getContext().getSharedPreferences(mypreferenceLogin, MODE_PRIVATE).edit();
                    total_points_pref.putString("user_total_points", String.valueOf(totalCustomerRewardPoints));
                    total_points_pref.commit();


                    Log.i(TAG, "  success to API." + response);
                    Toast.makeText(getContext(), "Success register+++++++++", Toast.LENGTH_LONG).show();

                }

                else
                {
                    Log.i(TAG, "post not submitted to API." + response);
                    Toast.makeText(getContext(), "Unsuccess register+++++++++", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TotalCustomerRewardPoints> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to register API.");
                Toast.makeText(getContext(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    public void fetchAllRegisteredVendorNames() {


        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
        // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        Call<GetAllVendors> call1 = apiInterface.getAllVendors();

        System.out.println("callll====="+call1);

        call1.enqueue(new Callback<GetAllVendors>() {


            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<GetAllVendors> call, Response<GetAllVendors> response) {

                if(response.code()== 200) {
                    // if (passwordRegister == rePassword_register.getText().toString()) {
                    int statusCode = response.code();

                    System.out.println("Code" + statusCode);
                    System.out.println("body----" + response.body().getData());

                    // vendorData= new ArrayList<>();

                    /*vendorNamesListAdapter= new VendorNamesListAdapter(getApplicationContext(), (ArrayList<GetAllVendorsData>) response.body().getData());
                    listViewOfVendorNames.setAdapter(vendorNamesListAdapter);*/

                    getAllVendorsList = new ArrayList<>();
                    allVendorsListAdapter = new AllVendorsListAdapter(getContext(), response.body().getData());
                    RecyclerView.LayoutManager subLayoutManager =
                            new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                   recycle_registeredVendors.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
                    recycle_registeredVendors.setLayoutManager(subLayoutManager);

                    // home_prod_recycler.addItemDecoration(new Home_Product_Details_Fragment().GridSpacingItemDecoration(2, dpToPx(10), true));
                    recycle_registeredVendors.setItemAnimator(new DefaultItemAnimator());
                    recycle_registeredVendors.setAdapter(allVendorsListAdapter);


                    // startActivity(new Intent(getApplication(),ProfileActivity.class));

                    Log.i(TAG, "Success of add with  body, valid membership----" + response);
                    //Toast.makeText(getContext(), "Success register+++++++++", Toast.LENGTH_LONG).show();

                }

                else
                {
                    Log.i(TAG, "Not submitted----" + response);
                   // Toast.makeText(getContext(), "Unsuccess register+++++++++", Toast.LENGTH_LONG).show();
                }
            }



            @Override
            public void onFailure(Call<GetAllVendors> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to register API.");
                //Toast.makeText(getContext(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

}
