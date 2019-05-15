package com.kryptoblocks.rewardx2019.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kryptoblocks.rewardx2019.DiscoverAddProgramsActivity;
import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.apiInterfaces.ApiInterface;
import com.kryptoblocks.rewardx2019.network.ApiClient;
import com.kryptoblocks.rewardx2019.pojo.ActivitiesPojo;
import com.kryptoblocks.rewardx2019.pojo.DiscoverData;
import com.kryptoblocks.rewardx2019.pojo.IsCustomerRegistered;
import com.kryptoblocks.rewardx2019.pojo.RegisterToRewardsProgramInput;
import com.kryptoblocks.rewardx2019.pojo.RegisterToRewardsProgramOutput;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.kryptoblocks.rewardx2019.SocialLoginActivity.user_uuid;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.MyViewHolder>{

    Context mContext;
    List<DiscoverData> discoverDataList;
    public static String vendor_uuid;
    ApiInterface apiInterface;
    //public final DiscoverAdapter.MyViewHolder holder;



    public final class  MyViewHolder extends RecyclerView.ViewHolder {
        public TextView discover_offer_name, discover_company_name;
        public ImageView img_discover;
        public CardView cardViewVendor;
        public Button btn_add_rewardsProgram;
        LinearLayout linear_layout_discoverTab;


        public MyViewHolder(View view) {
            super(view);

            discover_company_name = view.findViewById(R.id.discover_shop_name);
            img_discover = view.findViewById(R.id.discover_image);
            cardViewVendor=view.findViewById(R.id.cardview_discover_vendors);
            btn_add_rewardsProgram = view.findViewById(R.id.add_reward_program_button);
            linear_layout_discoverTab = view.findViewById(R.id.linear_layout_discoverTab);

        }
    }


    public DiscoverAdapter(Context mContext, List<DiscoverData> discoverData) {
        this.mContext = mContext;
        this.discoverDataList = discoverData;
    }
    @Override
    public DiscoverAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_discover_layout, parent, false);

       // MyViewHolder myViewHolder = new MyViewHolder(itemView);

        return new DiscoverAdapter.MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(final DiscoverAdapter.MyViewHolder holder, final int position) {

        final DiscoverData list = discoverDataList.get(position);

        vendor_uuid = list.getUuid();
        System.out.print("vendor uuid-----"+position+"------"+vendor_uuid);
        isRegistered(holder);

        holder.discover_company_name.setText(String.valueOf(list.getVendorName()));
        Glide.with(mContext).load(list.getLogoLink()).error(R.drawable.ic_launcher_foreground).into(holder.img_discover);


      /*  holder.btn_add_rewardsProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vendor_uuid = list.getUuid();
              //  isRegistered(holder,position);
                Log.i(TAG, "clicked successful" );
                Toast.makeText(mContext, "clicked btn+++++++++", Toast.LENGTH_LONG).show();
            }
        });*/

       /* vendor_uuid = list.getUuid();
        System.out.print("vendor uuid-----"+position+"------"+vendor_uuid);*/
     //   isRegistered(holder,position);

       /* holder.btn_add_rewardsProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vendor_uuid = list.getUuid();
                isRegistered(holder,position);
                *//*Intent i = new Intent(mContext, DiscoverAddProgramsActivity.class);
                mContext.startActivity(i);*//*
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return discoverDataList.size();
    }


    public void registerToRewardsProgram() {

        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
        // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        //Call<LoginCustomerOutput> call1 =  apiInterface.customerLogin("abc","123");

        RegisterToRewardsProgramInput rewardsProgramInput = new RegisterToRewardsProgramInput();

        rewardsProgramInput.setUserUuid(user_uuid);
        rewardsProgramInput.setVendorUuid(vendor_uuid);
        rewardsProgramInput.setRegisteredVia("mobile");


        System.out.println("userId======"+ user_uuid);
        System.out.println("vendorId======"+vendor_uuid);


        Call<RegisterToRewardsProgramOutput> callLogin =  apiInterface.registerToRewardsProgram(rewardsProgramInput);


        System.out.println("callll====="+callLogin);

        callLogin.enqueue(new Callback<RegisterToRewardsProgramOutput>() {


            @Override
            public void onResponse(Call<RegisterToRewardsProgramOutput> call, Response<RegisterToRewardsProgramOutput> response) {

              /*  System.out.println("call====="+call);
                System.out.println("body" + response.body());
                int statusCode = response.code();
                System.out.println("Code" + statusCode);*/

                try {

                    if (response.code()==200) {

                        int statusCode = response.code();

                        System.out.println("Code" + statusCode);

                        System.out.println("body" + response.body().toString());


                        /*Intent i = new Intent(getApplication(), DiscoverAddProgramsActivity.class);
                        startActivity(i);*/
                        // DiscoverFragment();

                        Log.i(TAG, "login successful" + response);
                    }
                    else {
                        Log.i(TAG, "post not submitted to API." + response.errorBody());
                        Toast.makeText(mContext, "Unsuccess login+++++++++", Toast.LENGTH_LONG).show();
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
                Toast.makeText(mContext, "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

   // public void isRegistered(final DiscoverAdapter.MyViewHolder holder,final int positionn) {

     public void isRegistered(final MyViewHolder holder) {

   //  public void isRegistered() {

        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
        // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        Call<IsCustomerRegistered> callLogin =  apiInterface.isCustomerRegistered(user_uuid,vendor_uuid);


        System.out.println("callll====="+callLogin);

        callLogin.enqueue(new Callback<IsCustomerRegistered>() {


            @Override
            public void onResponse(Call<IsCustomerRegistered> call, Response<IsCustomerRegistered> response) {


                try {

                    String  value = response.body().getData().toString();
                    System.out.println("Body of discover-----------------"+value);
                   // if(response.code()==200 && !response.body().getData().isEmpty()) {
                    if(response.code()== 200 && response.body().getData().toString().isEmpty()) {

                       holder.btn_add_rewardsProgram.setVisibility(View.VISIBLE);
                        holder.btn_add_rewardsProgram.setText("Join now");

                       // holderr.btn_add_rewardsProgram.setBackground();

                        int statusCode = response.code();

                        System.out.println("Code" + statusCode);

                        System.out.println("body" + response.body().toString());
                        holder.btn_add_rewardsProgram.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                holder.btn_add_rewardsProgram.setText("Joined program");
                                Toast.makeText(mContext, "clicked register +++++++++", Toast.LENGTH_LONG).show();
                                registerToRewardsProgram();
                            }
                        });


                        /*Intent i = new Intent(getApplication(), DiscoverAddProgramsActivity.class);
                        startActivity(i);*/
                        //  DiscoverFragment();

                        Log.i(TAG, " successful" + response);
                    }
                    else if((value!=null) && (response.code()==200))
                    {
                        // holderr.btn_add_rewardsProgram.setVisibility(View.GONE);

                        holder.btn_add_rewardsProgram.setText("Joined program");

                       // holderr.linear_layout_discoverTab.getBackground().setAlpha(100);
                        //holderr.cardViewVendor.setBackgroundColor(mContext.getResources().getColor(R.color.colorLightBlue));
                        Log.i(TAG, "Already registered" + response);
                       Toast.makeText(mContext, "Unsuccess +++++++++", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Log.i(TAG, "else part" + response);
                        Toast.makeText(mContext, "else Unsuccess +++++++++", Toast.LENGTH_LONG).show();
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
                Toast.makeText(mContext, "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }


}
