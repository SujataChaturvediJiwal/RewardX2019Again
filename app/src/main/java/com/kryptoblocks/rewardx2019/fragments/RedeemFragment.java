package com.kryptoblocks.rewardx2019.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import android.widget.Toast;

import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.adapter.DisplayRedeemTokensAdapter;
import com.kryptoblocks.rewardx2019.apiInterfaces.ApiInterface;
import com.kryptoblocks.rewardx2019.network.ApiClient;
import com.kryptoblocks.rewardx2019.pojo.GetAllUserRewardPoints;
import com.kryptoblocks.rewardx2019.pojo.GetAllUserRewardPointsData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
//import static com.kryptoblocks.rewardx2019.SocialLoginActivity.user_uuid;

/**
 * A simple {@link Fragment} subclass.
 */
public class RedeemFragment extends Fragment {


    public RedeemFragment() {
        // Required empty public constructor
    }

    RecyclerView recycle_redeem;
    List<GetAllUserRewardPointsData> redeemTokenPojos;
    DisplayRedeemTokensAdapter displayRedeemTokensAdapter;
    ApiInterface apiInterface;

    public static final String mypreferenceLogin = "mypref";
    SharedPreferences sharedPreferencesRedeemFrag;
    String user_id_RedeemFrag;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_redeem, container, false);

        recycle_redeem= view.findViewById(R.id.recyclerView_redeem_tokens);

     /* redeemTokenPojos = new ArrayList<>();
        displayRedeemTokensAdapter = new DisplayRedeemTokensAdapter(getContext(), redeemTokenPojos);
        RecyclerView.LayoutManager subLayoutManager = new GridLayoutManager(getContext(),2);


        recycle_redeem.setLayoutManager(subLayoutManager);
                // home_prod_recycler.addItemDecoration(new Home_Product_Details_Fragment().GridSpacingItemDecoration(2, dpToPx(10), true));
        recycle_redeem.setItemAnimator(new DefaultItemAnimator());
        recycle_redeem.setAdapter(displayRedeemTokensAdapter);
*/
       // activitiesSet();

        sharedPreferencesRedeemFrag = this.getActivity().getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
        user_id_RedeemFrag = sharedPreferencesRedeemFrag.getString("user_unique_id","hi");

        //Toast.makeText(getContext(), "User id:" + user_id_RedeemFrag , Toast.LENGTH_SHORT).show();

        rewardPointDetails();

        return  view;
    }

  /* private void activitiesSet() {


       TokenPojo trans = new TokenPojo("Spa company", "22");
        redeemTokenPojos.add(trans);

        trans = new TokenPojo("Food Company", "0");
        redeemTokenPojos.add(trans);

        trans = new TokenPojo("Health Company", "50");
        redeemTokenPojos.add(trans);

        trans = new TokenPojo("Travel Company", "100");
        redeemTokenPojos.add(trans);

        trans = new TokenPojo("ABC", "14");
        redeemTokenPojos.add(trans);

        trans = new TokenPojo("XYZ", "89");
        redeemTokenPojos.add(trans);

        trans = new TokenPojo("IIT", "67");
        redeemTokenPojos.add(trans);

        trans = new TokenPojo("JKL", "39");
        redeemTokenPojos.add(trans);

        trans = new TokenPojo("MNO", "120");
        redeemTokenPojos.add(trans);

        trans = new TokenPojo("EEE", "0");
        redeemTokenPojos.add(trans);

        displayRedeemTokensAdapter.notifyDataSetChanged();
    }*/

   /* public void displayAllTokens() {


        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
        // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        Call<GetAllUserRewardPoints> call1 = apiInterface.getAllUserRewardPoints(user_id_RedeemFrag);

        System.out.println("callll====="+call1);

        call1.enqueue(new Callback<GetAllUserRewardPoints>() {


            @Override
            public void onResponse(Call<GetAllUserRewardPoints> call, Response<GetAllUserRewardPoints> response) {

                if(response.code()== 200) {

                    int statusCode = response.code();
                    System.out.println("Code" + statusCode);
                    System.out.println("body" + response.body().getData());

                    redeemTokenPojos = new ArrayList<>();
                    displayRedeemTokensAdapter = new DisplayRedeemTokensAdapter(getContext(), response.body().getData());
                    RecyclerView.LayoutManager subLayoutManager = new GridLayoutManager(getContext(),2);
                    recycle_redeem.setLayoutManager(subLayoutManager);
                    recycle_redeem.setItemAnimator(new DefaultItemAnimator());
                    recycle_redeem.setHasFixedSize(true);
                    recycle_redeem.setAdapter(displayRedeemTokensAdapter);

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
            public void onFailure(Call<GetAllUserRewardPoints> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to register API.");
                Toast.makeText(getContext(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }*/

    public void rewardPointDetails() {


        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
        // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        Call<GetAllUserRewardPoints> call1 = apiInterface.getAllUserRewardPoints(user_id_RedeemFrag);

        System.out.println("callll====="+call1);

        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getContext());
        // progressDoalog.setMax(50);
        progressDoalog.setMessage("Its loading....");
        // progressDoalog.setTitle("ProgressDialog bar example");
        // progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // show it
        progressDoalog.show();


        call1.enqueue(new Callback<GetAllUserRewardPoints>() {


            @Override
            public void onResponse(Call<GetAllUserRewardPoints> call, Response<GetAllUserRewardPoints> response) {

                if(response.code()== 200) {


                    if (progressDoalog.isShowing())
                        progressDoalog.dismiss();

                    int statusCode = response.code();
                    System.out.println("Code" + statusCode);
                    System.out.println("body" + response.body().getData());

                    redeemTokenPojos = new ArrayList<>();
                    displayRedeemTokensAdapter = new DisplayRedeemTokensAdapter(getContext(), response.body().getData());
                   // RecyclerView.LayoutManager subLayoutManager = new GridLayoutManager(getContext(),2);
                    RecyclerView.LayoutManager subLayoutManager =
                            new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                   // recycle_redeem.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
                    recycle_redeem.setLayoutManager(subLayoutManager);
                    recycle_redeem.setItemAnimator(new DefaultItemAnimator());
                    recycle_redeem.setHasFixedSize(true);
                    recycle_redeem.setAdapter(displayRedeemTokensAdapter);


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
            public void onFailure(Call<GetAllUserRewardPoints> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to register API.");
                //Toast.makeText(getContext(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();


                if (progressDoalog.isShowing())
                    progressDoalog.dismiss();
            }
        });
    }
}
