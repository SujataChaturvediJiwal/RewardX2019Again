package com.kryptoblocks.rewardx2019.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kryptoblocks.rewardx2019.FilterActivity;
import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.adapter.DiscoverAdapter;
import com.kryptoblocks.rewardx2019.adapter.DiscoverTabForOffersAdapter;
import com.kryptoblocks.rewardx2019.adapter.OffersAdapter;
import com.kryptoblocks.rewardx2019.apiInterfaces.ApiInterface;
import com.kryptoblocks.rewardx2019.network.ApiClient;
import com.kryptoblocks.rewardx2019.pojo.Discover;
import com.kryptoblocks.rewardx2019.pojo.DiscoverData;
import com.kryptoblocks.rewardx2019.pojo.DiscoverTabForOffers;
import com.kryptoblocks.rewardx2019.pojo.IsCustomerRegistered;
import com.kryptoblocks.rewardx2019.pojo.OffersPojo;
import com.kryptoblocks.rewardx2019.pojo.RegisterCustomerInput;
import com.kryptoblocks.rewardx2019.pojo.RegisterCustomerOutput;
import com.kryptoblocks.rewardx2019.pojo.RegisterToRewardsProgramInput;
import com.kryptoblocks.rewardx2019.pojo.RegisterToRewardsProgramOutput;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.kryptoblocks.rewardx2019.SocialLoginActivity.user_uuid;
import static com.kryptoblocks.rewardx2019.adapter.DiscoverAdapter.vendor_uuid;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {

    List<DiscoverTabForOffers> discoverOffers;
    DiscoverTabForOffersAdapter offersAdapter;
    RecyclerView offersDiscovertab_recyclerView, recyle_discover;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    TextView sort_option_discover, filter_option_discover;
    Button add_program_btn;

    List<DiscoverData> discover_pojo;
    DiscoverAdapter discoverAdapter;
    ApiInterface apiInterface;
    static String vendor_uuid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discover, container, false);

        add_program_btn = view.findViewById(R.id.add_reward_program_button);

        sort_option_discover = view.findViewById(R.id.sort_discover);
        sort_option_discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sort_popup();
            }
        });

        filter_option_discover = view.findViewById(R.id.filter_discover);
        recyle_discover = view.findViewById(R.id.recyclerView_discover);


        filter_option_discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), FilterActivity.class);
                startActivity(i);
            }
        });



        /////////////
        offersDiscovertab_recyclerView = view.findViewById(R.id.recyclerView_discover_topBrands);



       // offersSet();

        displayNearByVendor();

       //

        displayDiscoverTopOffers();

        return view;


    }

    public void sort_popup()
    {
        final AlertDialog builder = new AlertDialog.Builder(getContext()).create();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View alertView = inflater.inflate(R.layout.sort_menu_layout, null);

        ImageView close_popup = alertView.findViewById(R.id.close_sort_menu_image);
       // CheckBox email_checkbox = alertView.findViewById(R.id.checkBox_email);
       // CheckBox sms_checkbox = alertView.findViewById(R.id.checkBox_sms);
        //CheckBox notifications_checkbox = alertView.findViewById(R.id.checkBox_notifications);

        //email_checkbox.setChecked(true);
        //sms_checkbox.setChecked(true);
        //notifications_checkbox.setChecked(true);

        //builder.setMessage();
        //builder.setTitle("I would like to subscribe via:");
        builder.setView(alertView);
        // builder_main.dismiss();

        builder.show();

        close_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });
    }

    public void displayNearByVendor() {


        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
       // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        Call<Discover> call1 = apiInterface.discoverVendors();

        System.out.println("callll====="+call1);

        call1.enqueue(new Callback<Discover>() {


            @Override
            public void onResponse(Call<Discover> call, Response<Discover> response) {

                if(response.code()== 200) {
                    // if (passwordRegister == rePassword_register.getText().toString()) {
                    int statusCode = response.code();

                    System.out.println("Code" + statusCode);

                    System.out.println("body" + response.body().getData());



                    discover_pojo = new ArrayList<>();
                    discoverAdapter = new DiscoverAdapter(getContext(), response.body().getData());
                    RecyclerView.LayoutManager subLayoutManager =
                            new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

                    recyle_discover.setLayoutManager(subLayoutManager);
                    recyle_discover.setLayoutManager(subLayoutManager);
                    // home_prod_recycler.addItemDecoration(new Home_Product_Details_Fragment().GridSpacingItemDecoration(2, dpToPx(10), true));
                    recyle_discover.setItemAnimator(new DefaultItemAnimator());
                    recyle_discover.setAdapter(discoverAdapter);

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
            public void onFailure(Call<Discover> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to register API.");
                Toast.makeText(getContext(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    public void displayDiscoverTopOffers() {


        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
        // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        Call<Discover> call1 = apiInterface.discoverVendors();

        System.out.println("callll====="+call1);

        call1.enqueue(new Callback<Discover>() {


            @Override
            public void onResponse(Call<Discover> call, Response<Discover> response) {

                if(response.code()== 200) {
                    // if (passwordRegister == rePassword_register.getText().toString()) {
                    int statusCode = response.code();

                    System.out.println("Code" + statusCode);

                    System.out.println("body" + response.body().getData());



                    discoverOffers = new ArrayList<>();
                    offersAdapter = new DiscoverTabForOffersAdapter(getContext(), response.body().getData());
                    RecyclerView.LayoutManager subLayoutManager =
                            new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

                    offersDiscovertab_recyclerView.setLayoutManager(subLayoutManager);
                    offersDiscovertab_recyclerView.setLayoutManager(subLayoutManager);
                    // home_prod_recycler.addItemDecoration(new Home_Product_Details_Fragment().GridSpacingItemDecoration(2, dpToPx(10), true));
                    offersDiscovertab_recyclerView.setItemAnimator(new DefaultItemAnimator());
                    offersDiscovertab_recyclerView.setAdapter(offersAdapter);

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
            public void onFailure(Call<Discover> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to register API.");
                Toast.makeText(getContext(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    private void offersSet() {


        DiscoverTabForOffers discoverTab = new DiscoverTabForOffers("hiii","byeeee","");
        discoverOffers.add(discoverTab);

        discoverTab = new DiscoverTabForOffers("280", "Company car", "");
        discoverOffers.add(discoverTab);

        discoverTab = new DiscoverTabForOffers("960", "Company food", " ");
        discoverOffers.add(discoverTab);

        discoverTab = new DiscoverTabForOffers("280", "Company car", "");
        discoverOffers.add(discoverTab);

        discoverTab = new DiscoverTabForOffers("280", "Company car", "");
        discoverOffers.add(discoverTab);

        discoverTab = new DiscoverTabForOffers("280", "Company car", "");
        discoverOffers.add(discoverTab);

        discoverTab = new DiscoverTabForOffers("280", "Company car", "");
        discoverOffers.add(discoverTab);

        offersAdapter.notifyDataSetChanged();
    }

}
