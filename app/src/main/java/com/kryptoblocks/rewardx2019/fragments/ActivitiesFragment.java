package com.kryptoblocks.rewardx2019.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
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
import com.kryptoblocks.rewardx2019.apiInterfaces.ApiInterface;
import com.kryptoblocks.rewardx2019.network.ApiClient;
import com.kryptoblocks.rewardx2019.pojo.ActivitiesPojo;
import com.kryptoblocks.rewardx2019.pojo.Discover;
import com.kryptoblocks.rewardx2019.pojo.GetRecentActivities;
import com.kryptoblocks.rewardx2019.pojo.GetRecentActivitiesData;
import com.kryptoblocks.rewardx2019.pojo.TotalCustomerRewardPoints;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;
import static com.kryptoblocks.rewardx2019.MainActivity.navigationView;
import static com.kryptoblocks.rewardx2019.MainActivity.tab_home;
import static com.kryptoblocks.rewardx2019.SocialLoginActivity.mypreferenceLogin;
import static com.kryptoblocks.rewardx2019.SocialLoginActivity.user_uuid;

//import static com.kryptoblocks.rewardx2019.SocialLoginActivity.user_uuid;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivitiesFragment extends Fragment {


    public ActivitiesFragment() {
        // Required empty public constructor
    }

  RecyclerView recyle_activities;
    List<GetRecentActivitiesData> act_pojo;
    ActivitiesAdapter activitiesAdapter;
    TextView text_activities_empty, activity_total_points;

    public static final String mypreferenceLogin = "mypref";
    SharedPreferences sharedPreferencesActivitiesFrag;
    String user_id_activitiesFrag;
    SharedPreferences sharedPreferencesActivities;
    String total_points, user_id_total_reward_points, totalCustomerRewardPoints;

    ApiInterface apiInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activities, container, false);


        recyle_activities= view.findViewById(R.id.recycler_view_activities);
        text_activities_empty= view.findViewById(R.id.empty_activities_textView);
        activity_total_points = view.findViewById(R.id.total_points_activities);

        tab_home.setVisibility(View.GONE);
        getActivity().setTitle("Activities");

        /*sharedPreferencesActivities = getContext().getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
        total_points = sharedPreferencesActivities.getString("user_total_points","");

        activity_total_points.setText(total_points);*/

        //navigationView.getMenu().getItem(2).setEnabled(true);

        // navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(1).setChecked(true);

      /*  v;
        activitiesAdapter = new ActivitiesAdapter(getContext(), act_pojo);
        RecyclerView.LayoutManager subLayoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyle_activities.setLayoutManager(subLayoutManager);
        recyle_activities.setLayoutManager(subLayoutManager);
        // home_prod_recycler.addItemDecoration(new Home_Product_Details_Fragment().GridSpacingItemDecoration(2, dpToPx(10), true));
        recyle_activities.setItemAnimator(new DefaultItemAnimator());
        recyle_activities.setAdapter(activitiesAdapter);
*/
        //activitiesSet();

        sharedPreferencesActivitiesFrag = this.getActivity().getSharedPreferences(mypreferenceLogin, MODE_PRIVATE);
        //retrieving data of shared preferences
        user_id_total_reward_points = sharedPreferencesActivitiesFrag.getString("user_unique_id","hi");

        getTotalCustomerRewardPoints();


        sharedPreferencesActivitiesFrag = this.getActivity().getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
        user_id_activitiesFrag = sharedPreferencesActivitiesFrag.getString("user_unique_id","hi");

        //Toast.makeText(getContext(), "User id:" + user_id_activitiesFrag , Toast.LENGTH_SHORT).show();

        recentActivities();

        return view;
    }

   /* private void activitiesSet() {


        ActivitiesPojo trans = new ActivitiesPojo("500", "Company spa", "Health", "12 May");
        act_pojo.add(trans);

        trans = new ActivitiesPojo("280", "Company car", "Travel", "28 March ");
        act_pojo.add(trans);

        trans = new ActivitiesPojo("960", "Company food", "Food", "01August ");
        act_pojo.add(trans);

        trans = new ActivitiesPojo("280", "Company car", "Travel", "28 March ");
        act_pojo.add(trans);

        trans = new ActivitiesPojo("280", "Company car", "Travel", "28 March ");
        act_pojo.add(trans);

        trans = new ActivitiesPojo("280", "Company car", "Travel", "28 March ");
        act_pojo.add(trans);

        trans = new ActivitiesPojo("280", "Company car", "Travel", "28 March ");
        act_pojo.add(trans);

        trans = new ActivitiesPojo("280", "Company car", "Travel", "28 March ");
        act_pojo.add(trans);

        trans = new ActivitiesPojo("280", "Company car", "Travel", "28 March ");
        act_pojo.add(trans);

        trans = new ActivitiesPojo("280", "Company car", "Travel", "28 March ");
        act_pojo.add(trans);

        activitiesAdapter.notifyDataSetChanged();
    }*/

    public void recentActivities() {


        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
        // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        Call<GetRecentActivities> call1 = apiInterface.getRecentActivities(user_id_activitiesFrag);

        System.out.println("callll====="+call1);

        call1.enqueue(new Callback<GetRecentActivities>() {


            @Override
            public void onResponse(Call<GetRecentActivities> call, Response<GetRecentActivities> response) {

                if(response.code()== 200 && !response.body().getData().isEmpty() ) {
                    // if (passwordRegister == rePassword_register.getText().toString()) {

                    int statusCode = response.code();

                    System.out.println("Code" + statusCode);

                    System.out.println("body" + response.body().getData());



                    act_pojo = new ArrayList<>();
                    activitiesAdapter = new ActivitiesAdapter(getContext(), response.body().getData());
                    RecyclerView.LayoutManager subLayoutManager =
                            new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

                    recyle_activities.setLayoutManager(subLayoutManager);
                    recyle_activities.setHasFixedSize(true);
                    // home_prod_recycler.addItemDecoration(new Home_Product_Details_Fragment().GridSpacingItemDecoration(2, dpToPx(10), true));
                    recyle_activities.setItemAnimator(new DefaultItemAnimator());
                    recyle_activities.setAdapter(activitiesAdapter);



                    System.out.println("body size activity-------------------" + response.body().getData().size());
                    System.out.println("body size activity-------------------" + response.body().getData());

                    Log.i(TAG, "  success to API." + response);

                    //Toast.makeText(getContext(), "Success register+++++++++", Toast.LENGTH_LONG).show();

                }

                else if(response.code()== 200 && response.body().getData().isEmpty() ) {
                    text_activities_empty.setVisibility(View.VISIBLE);

                }
                else
                {
                    Log.i(TAG, "post not submitted to API." + response);
                    //Toast.makeText(getContext(), "Unsuccess register+++++++++", Toast.LENGTH_LONG).show();
                }
            }



            @Override
            public void onFailure(Call<GetRecentActivities> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to register API.");
                //Toast.makeText(getContext(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    public void getTotalCustomerRewardPoints() {


        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
        // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        Call<TotalCustomerRewardPoints> call1 = apiInterface.getTotalCustomerRewardPoints(user_id_total_reward_points);

        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();


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
                    activity_total_points.setText(totalCustomerRewardPoints);

                    SharedPreferences.Editor total_points_pref = getContext().getSharedPreferences(mypreferenceLogin, MODE_PRIVATE).edit();
                    total_points_pref.putString("user_total_points", String.valueOf(totalCustomerRewardPoints));
                    total_points_pref.commit();

                    if (progressDoalog.isShowing())
                        progressDoalog.dismiss();

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
            public void onFailure(Call<TotalCustomerRewardPoints> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to register API.");
                //Toast.makeText(getContext(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
                if (progressDoalog.isShowing())
                    progressDoalog.dismiss();
            }
        });
    }

}
