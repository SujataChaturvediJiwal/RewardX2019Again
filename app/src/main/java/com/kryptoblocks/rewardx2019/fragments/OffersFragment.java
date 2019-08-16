package com.kryptoblocks.rewardx2019.fragments;


import android.content.Context;
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

import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.adapter.ActivitiesAdapter;
import com.kryptoblocks.rewardx2019.adapter.DiscoverAdapter;
import com.kryptoblocks.rewardx2019.adapter.OffersAdapter;
import com.kryptoblocks.rewardx2019.apiInterfaces.ApiInterface;
import com.kryptoblocks.rewardx2019.network.ApiClient;
import com.kryptoblocks.rewardx2019.pojo.ActivitiesPojo;
import com.kryptoblocks.rewardx2019.pojo.Discover;
import com.kryptoblocks.rewardx2019.pojo.GetAllVendorOffers;
import com.kryptoblocks.rewardx2019.pojo.GetAllVendorOffersData;
import com.kryptoblocks.rewardx2019.pojo.OffersPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
//import static com.kryptoblocks.rewardx2019.SocialLoginActivity.user_uuid;
import static com.kryptoblocks.rewardx2019.adapter.RegisteredVendorRewardsAdapter.vendor_reward_uuid;
import static com.kryptoblocks.rewardx2019.fragments.DiscoverFragment.vendor_uuid;

/**
 * A simple {@link Fragment} subclass.
 */
public class OffersFragment extends Fragment {


    public OffersFragment() {
        // Required empty public constructor
    }

    RecyclerView recyle_redeem;
    List<GetAllVendorOffersData> act_pojo;
    OffersAdapter offersAdapter;
    ApiInterface apiInterface;
    TextView empty_offers_textView;

    public static final String mypreferenceLogin = "mypref";
    SharedPreferences sharedPreferencesOffersFrag;
    String user_id_offers_frag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_offers, container, false);

        recyle_redeem= view.findViewById(R.id.recyclerView_offers);
        empty_offers_textView = view.findViewById(R.id.empty_offers_textView);

        act_pojo = new ArrayList<>();
        /*offersAdapter = new OffersAdapter(getContext(), act_pojo);
        RecyclerView.LayoutManager subLayoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyle_redeem.setLayoutManager(subLayoutManager);
        recyle_redeem.setLayoutManager(subLayoutManager);
        // home_prod_recycler.addItemDecoration(new Home_Product_Details_Fragment().GridSpacingItemDecoration(2, dpToPx(10), true));
        recyle_redeem.setItemAnimator(new DefaultItemAnimator());
        recyle_redeem.setAdapter(offersAdapter);*/

        sharedPreferencesOffersFrag = this.getActivity().getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
        user_id_offers_frag = sharedPreferencesOffersFrag.getString("user_unique_id","hi");

        //Toast.makeText(getContext(), "User id:" + user_id_offers_frag , Toast.LENGTH_SHORT).show();
        displayAllVendorOffers();

       // redeemSet();

        return view;

    }
   /* private void redeemSet() {


        OffersPojo trans = new OffersPojo("50", "Company spa", "Spa", "280");
        act_pojo.add(trans);

        trans = new OffersPojo("28", "Company car", "Travel", "20 ");
        act_pojo.add(trans);

        trans = new OffersPojo("6", "Company food", "Food", "79 ");
        act_pojo.add(trans);

        trans = new OffersPojo("15", "Company car", "Travel", "70 ");
        act_pojo.add(trans);

        trans = new OffersPojo("49", "Company car", "Travel", "45");
        act_pojo.add(trans);

        trans = new OffersPojo("35", "Company car", "Travel", "32");
        act_pojo.add(trans);

        trans = new OffersPojo("12", "Company car", "Travel", "56 ");
        act_pojo.add(trans);

        trans = new OffersPojo("30", "Company car", "Travel", "200 ");
        act_pojo.add(trans);

        trans = new OffersPojo("21", "Company car", "Travel", "110 ");
        act_pojo.add(trans);

        trans = new OffersPojo("5", "Company car", "Travel", "180 ");
        act_pojo.add(trans);

        offersAdapter.notifyDataSetChanged();
    }*/

    public void displayAllVendorOffers() {


        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
        // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        Call<GetAllVendorOffers> call1 = apiInterface.getAllVendorsOffers(user_id_offers_frag);

        System.out.println("callll====="+call1);

        call1.enqueue(new Callback<GetAllVendorOffers>() {


            @Override
            public void onResponse(Call<GetAllVendorOffers> call, Response<GetAllVendorOffers> response) {

                if(response.code()== 200 && !response.body().getData().isEmpty()) {
                    // if (passwordRegister == rePassword_register.getText().toString()) {
                    int statusCode = response.code();

                    System.out.println("Code" + statusCode);

                    System.out.println("body" + response.body().getData());



                    act_pojo = new ArrayList<>();
                    offersAdapter = new OffersAdapter(getContext(), response.body().getData());
                    RecyclerView.LayoutManager subLayoutManager =
                            new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    recyle_redeem.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
                    recyle_redeem.setLayoutManager(subLayoutManager);
                    recyle_redeem.setLayoutManager(subLayoutManager);
                    // home_prod_recycler.addItemDecoration(new Home_Product_Details_Fragment().GridSpacingItemDecoration(2, dpToPx(10), true));
                    recyle_redeem.setItemAnimator(new DefaultItemAnimator());
                    recyle_redeem.setAdapter(offersAdapter);


                    Log.i(TAG, "  success to API." + response);
                    Toast.makeText(getContext(), "Success register+++++++++", Toast.LENGTH_LONG).show();

                }

                else if (response.code()== 200 && response.body().getData().isEmpty())
                {
                    empty_offers_textView.setVisibility(View.VISIBLE);
                }

                else
                {
                    Log.i(TAG, "post not submitted to API." + response);
                    //Toast.makeText(getContext(), "Unsuccess register+++++++++", Toast.LENGTH_LONG).show();
                }
            }



            @Override
            public void onFailure(Call<GetAllVendorOffers> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to register API.");
                //Toast.makeText(getContext(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

}
