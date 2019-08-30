package com.kryptoblocks.rewardx2019.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kryptoblocks.rewardx2019.FilterActivity;
import com.kryptoblocks.rewardx2019.MainActivity;
import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.adapter.DiscoverAdapter;
import com.kryptoblocks.rewardx2019.adapter.DiscoverTabForOffersAdapter;
import com.kryptoblocks.rewardx2019.adapter.OffersAdapter;
import com.kryptoblocks.rewardx2019.adapter.RefineCategoryAdapter;
import com.kryptoblocks.rewardx2019.apiInterfaces.ApiInterface;
import com.kryptoblocks.rewardx2019.network.ApiClient;
import com.kryptoblocks.rewardx2019.pojo.ChildPojo;
import com.kryptoblocks.rewardx2019.pojo.Discover;
import com.kryptoblocks.rewardx2019.pojo.DiscoverData;
import com.kryptoblocks.rewardx2019.pojo.DiscoverTabForOffers;
import com.kryptoblocks.rewardx2019.pojo.IsCustomerRegistered;
import com.kryptoblocks.rewardx2019.pojo.OffersPojo;
import com.kryptoblocks.rewardx2019.pojo.ParentPojo;
import com.kryptoblocks.rewardx2019.pojo.RegisterCustomerInput;
import com.kryptoblocks.rewardx2019.pojo.RegisterCustomerOutput;
import com.kryptoblocks.rewardx2019.pojo.RegisterToRewardsProgramInput;
import com.kryptoblocks.rewardx2019.pojo.RegisterToRewardsProgramOutput;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
//import static com.kryptoblocks.rewardx2019.SocialLoginActivity.user_uuid;
//import static com.kryptoblocks.rewardx2019.adapter.DiscoverAdapter.vendor_uuid;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener{

    List<DiscoverTabForOffers> discoverOffers;
    DiscoverTabForOffersAdapter offersAdapter;
    RecyclerView offersDiscovertab_recyclerView, recyle_discover;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    TextView sort_option_discover, filter_option_discover, refine_dicover;
    Button add_program_btn ;

    List<DiscoverData> discover_pojo;
    DiscoverAdapter discoverAdapter;
    ApiInterface apiInterface;
    static String vendor_uuid;
    DrawerLayout refine_drawer_layout;
    ActionBarDrawerToggle toggle_refine;

    public static final String mypreferenceLogin = "mypref";
    SharedPreferences sharedPreferencesDiscoverFrag;
    String user_id_profileDiscoverFrag, u_lati, u_longi;
    NavigationView navigationView;
    RecyclerView recyclerView_expandable;
    RefineCategoryAdapter refineCategoryAdapter;
    List<ParentPojo> parent_pojo;
    Button refine_btn, cancel_btn;


    String distance = "1000000";
    String temp_longi = "77.59223" , temp_lati ="13.017908333333333";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discover, container, false);



       /* sort_option_discover = view.findViewById(R.id.sort_discover);
        sort_option_discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sort_popup();
            }
        });*/

        //filter_option_discover = view.findViewById(R.id.refine_discover);
        recyle_discover = view.findViewById(R.id.recyclerView_discover);

        refine_btn = view.findViewById(R.id.refine_button_refine);
        cancel_btn = view.findViewById(R.id.cancel_button_refine);


        refine_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refine_drawer_layout.closeDrawer(Gravity.RIGHT);
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refine_drawer_layout.closeDrawer(Gravity.RIGHT);
            }
        });
        /*filter_option_discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), FilterActivity.class);
                startActivity(i);
            }
        });*/




        /////////////
        offersDiscovertab_recyclerView = view.findViewById(R.id.recyclerView_discover_topBrands);


        sharedPreferencesDiscoverFrag = this.getActivity().getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
        user_id_profileDiscoverFrag = sharedPreferencesDiscoverFrag.getString("user_unique_id","hi");

        //Toast.makeText(getContext(), "User id:" + user_id_profileDiscoverFrag , Toast.LENGTH_SHORT).show();

       // offersSet();

        displayNearByVendor();

       //
      //  displayDiscoverTopOffers();

        refine_dicover = view.findViewById(R.id.refine_discover);

        //refine drawer layout
        refine_drawer_layout = view.findViewById(R.id.drawer_layout_refine);

        refine_dicover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sort_popup();
              /*
                //for navigation drawer filter option
                toggle_refine = new ActionBarDrawerToggle(
                        getActivity(), refine_drawer_layout ,null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                refine_drawer_layout.addDrawerListener(toggle_refine);

               *//* if (refine_drawer_layout.isDrawerOpen(Gravity.RIGHT)) {
                    refine_drawer_layout.closeDrawer(Gravity.RIGHT);
                }
                else {
                    refine_drawer_layout.openDrawer(Gravity.RIGHT);
                }
                refine_drawer_layout.openDrawer(Gravity.RIGHT);*//*
               // toggle_refine.syncState();
                refine_drawer_layout.openDrawer(Gravity.RIGHT);
                 navigationView =  view.findViewById(R.id.nav_view_discover);*/

            }
        });

        //expandable recycler view
        recyclerView_expandable =view.findViewById(R.id.expandable_recyclerView);

        //instantiate your adapter with the list of genres
        ArrayList<ParentPojo> mainCategory = new ArrayList<>();

        ArrayList<ChildPojo> fruitsList = new ArrayList<>();
        fruitsList.add(new ChildPojo("Apple"));
        fruitsList.add(new ChildPojo("Mango"));
        fruitsList.add(new ChildPojo("Banana"));
        fruitsList.add(new ChildPojo("Pineapple"));

        ParentPojo fruits = new ParentPojo("Fruits", fruitsList);
        mainCategory.add(fruits);

        ArrayList<ChildPojo> citiesList = new ArrayList<>();
        citiesList.add(new ChildPojo("Bangalore"));
        citiesList.add(new ChildPojo("Uttrakhand"));
        citiesList.add(new ChildPojo("Chandigarh"));
        citiesList.add(new ChildPojo("Shimla"));

        ParentPojo cities = new ParentPojo("Cities", citiesList);
        mainCategory.add(cities);

        RecyclerView.LayoutManager subLayoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView_expandable.setLayoutManager(subLayoutManager);


        parent_pojo = new ArrayList<>();
        refineCategoryAdapter = new RefineCategoryAdapter(mainCategory);

        recyclerView_expandable.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView_expandable.setItemAnimator(new DefaultItemAnimator());
        recyclerView_expandable.setAdapter(refineCategoryAdapter);
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

        sharedPreferencesDiscoverFrag = this.getActivity().getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
        u_lati = sharedPreferencesDiscoverFrag.getString("shared_lati","hi");
        u_longi = sharedPreferencesDiscoverFrag.getString("shared_longi","Bye");

        //Call<Discover> call1 = apiInterface.discoverVendorsbyDistance(u_longi,u_lati,distance);
        Call<Discover> call1 = apiInterface.discoverVendorsbyDistance(temp_longi,temp_lati,distance);

        System.out.println("callll====="+call1);

        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getContext());
       // progressDoalog.setMax(50);
        progressDoalog.setMessage("Loading....");
       // progressDoalog.setTitle("ProgressDialog bar example");
       // progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // show it
        progressDoalog.show();

        call1.enqueue(new Callback<Discover>() {

            @Override
            public void onResponse(Call<Discover> call, Response<Discover> response) {

                if(response.code()== 200) {
                    // if (passwordRegister == rePassword_register.getText().toString()) {
                    int statusCode = response.code();

                    if (progressDoalog.isShowing())
                        progressDoalog.dismiss();

                    System.out.println("Code" + statusCode);

                    System.out.println("body" + response.body().getData());

////////////////////////////trila
                    /*JSONArray mArray;
                    try {
                        mArray = new JSONArray(response.body().getData());
                        for (int i = 1; i <= mArray.length(); i++) {
                            JSONObject mJsonObject = mArray.getJSONObject(i);
                            Log.d("OutPut**************", mJsonObject.getString("type"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*/

                    discover_pojo = new ArrayList<>();
                    discoverAdapter = new DiscoverAdapter(getContext(), response.body().getData());
                    RecyclerView.LayoutManager subLayoutManager =
                            new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                   //recyle_discover.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

                    recyle_discover.setLayoutManager(subLayoutManager);
                    // home_prod_recycler.addItemDecoration(new Home_Product_Details_Fragment().GridSpacingItemDecoration(2, dpToPx(10), true));
                    recyle_discover.setItemAnimator(new DefaultItemAnimator());
                    recyle_discover.setAdapter(discoverAdapter);

                    Log.i(TAG, "  success to API." + response);
                   // Toast.makeText(getContext(), "Success register+++++++++", Toast.LENGTH_LONG).show();

                    }

                    else
                {
                    Log.i(TAG, "post not submitted to API." + response);
                   // Toast.makeText(getContext(), "Unsuccess register+++++++++", Toast.LENGTH_LONG).show();
                }
            }



            @Override
            public void onFailure(Call<Discover> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to register API.");
               // Toast.makeText(getContext(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
                if (progressDoalog.isShowing())
                    progressDoalog.dismiss();
            }
        });
    }

    public void displayDiscoverTopOffers() {


        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
        // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        sharedPreferencesDiscoverFrag = this.getActivity().getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
        u_lati = sharedPreferencesDiscoverFrag.getString("shared_lati","hi");
        u_longi = sharedPreferencesDiscoverFrag.getString("shared_longi","Bye");


        //Call<Discover> call1 = apiInterface.discoverVendorsbyDistance(u_longi,u_lati,distance);
        Call<Discover> call1 = apiInterface.discoverVendorsbyDistance(temp_longi,temp_lati,distance);


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
                    offersDiscovertab_recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL));
                    offersDiscovertab_recyclerView.setLayoutManager(subLayoutManager);
                    offersDiscovertab_recyclerView.setLayoutManager(subLayoutManager);
                    // home_prod_recycler.addItemDecoration(new Home_Product_Details_Fragment().GridSpacingItemDecoration(2, dpToPx(10), true));
                    offersDiscovertab_recyclerView.setItemAnimator(new DefaultItemAnimator());
                    offersDiscovertab_recyclerView.setAdapter(offersAdapter);

                    Log.i(TAG, "  success to API." + response);
                   // Toast.makeText(getContext(), "Success register+++++++++", Toast.LENGTH_LONG).show();

                }

                else
                {
                    Log.i(TAG, "post not submitted to API." + response);
                    //Toast.makeText(getContext(), "Unsuccess register+++++++++", Toast.LENGTH_LONG).show();
                }
            }



            @Override
            public void onFailure(Call<Discover> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to register API.");
                //Toast.makeText(getContext(), "Failed+++++++++", Toast.LENGTH_LONG).show();
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer =getActivity().findViewById(R.id.drawer_layout);
        drawer.closeDrawer(Gravity.RIGHT);

        return true;
    }
}
