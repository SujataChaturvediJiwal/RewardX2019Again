package com.kryptoblocks.rewardx2019.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
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
import com.kryptoblocks.rewardx2019.DiscoverDescriptionActivity;
import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.SignUpActivity;
import com.kryptoblocks.rewardx2019.apiInterfaces.ApiInterface;
import com.kryptoblocks.rewardx2019.network.ApiClient;
import com.kryptoblocks.rewardx2019.pojo.DiscoverData;
import com.kryptoblocks.rewardx2019.pojo.GetAllVendors;
import com.kryptoblocks.rewardx2019.pojo.GetRegisteredVendors;
import com.kryptoblocks.rewardx2019.pojo.IsCustomerRegistered;
import com.kryptoblocks.rewardx2019.pojo.RegisterToRewardsProgramInput;
import com.kryptoblocks.rewardx2019.pojo.RegisterToRewardsProgramOutput;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

import static android.content.Context.MODE_PRIVATE;
import static com.kryptoblocks.rewardx2019.adapter.RegisteredVendorRewardsAdapter.rewardsPojoList;
import static com.kryptoblocks.rewardx2019.fragments.RedeemFragment.mypreferenceLogin;
//import static com.kryptoblocks.rewardx2019.SocialLoginActivity.user_uuid;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.MyViewHolder>{

    Context mContext;
    List<DiscoverData> discoverDataList;
    DiscoverData discoverDataSam;
    public  static  String vendor_uuid;
    ApiInterface apiInterface;
    //public final DiscoverAdapter.MyViewHolder holder;
    public static final String mypreferenceLogin = "mypref";
    SharedPreferences sharedPreferencesDiscoverAdapter;
    public String user_id_profile_discover_adapter;
    DiscoverAdapter.MyViewHolder holder;
    public String v_uuid;
    public static String points, img, vendor_name,offer_name, summary, end_time_reward_date;
    String vendor_id_discover;
    public static String vendor_name_discover;
    SharedPreferences sharedPreferencesDiscover;
    String name;
    public static Integer flagRegistered =0;
    List<GetRegisteredVendors> rewardsPojos;
    RegisteredVendorRewardsAdapter rewardsAdapter;
    String registeredMerchantId, end_date;

    String id = "1fvzg1xwjvcmp41m";


    public final class  MyViewHolder extends RecyclerView.ViewHolder {
        public TextView discover_offer_name, discover_company_name, discover_offer_token_value;
        public ImageView img_discover;
        public CardView cardViewVendor;
        public Button btn_add_rewardsProgram;
        LinearLayout linear_layout_discoverTab;




        public MyViewHolder(View view) {
            super(view);

            discover_company_name = view.findViewById(R.id.discover_shop_name);
            img_discover = view.findViewById(R.id.discover_image);
          //  cardViewVendor=view.findViewById(R.id.cardview_discover_vendors);
            //btn_add_rewardsProgram = view.findViewById(R.id.add_reward_program_button);
            linear_layout_discoverTab = view.findViewById(R.id.linear_layout_discoverTab);
            discover_offer_name = view.findViewById(R.id.discover_title_name);
            discover_offer_token_value = view.findViewById(R.id.offer_token_value_discover);


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
        synchronized(list) {
            sharedPreferencesDiscoverAdapter = mContext.getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
            //retrieving data of shared preferences
            user_id_profile_discover_adapter = sharedPreferencesDiscoverAdapter.getString("user_unique_id", "hi");

            //Toast.makeText(mContext, "User id:" + user_id_profile_discover_adapter, Toast.LENGTH_SHORT).show();

            System.out.println("userId======" + user_id_profile_discover_adapter);

            holder.discover_company_name.setText(list.getOwnerName());
            holder.discover_offer_name.setText(list.getName());
            holder.discover_offer_token_value.setText(String.valueOf(list.getTotalPoints()));
            // holder.discover_company_name.setText(String.valueOf(list.getN()));
            Glide.with(mContext).load(list.getImageLink()).error(R.drawable.ic_launcher_foreground).into(holder.img_discover);

            vendor_uuid = list.getVendorUuid();
            SharedPreferences.Editor editor = sharedPreferencesDiscoverAdapter.edit();
            editor.putString("vendor_id", vendor_uuid);
            editor.commit();
            System.out.println("vendor uuid-----" + position + "------" + vendor_uuid);
            end_date = list.getEndTime();
            dateConverter();


            fetchVendorNamesDiscover();
            displayRegisteredVendor();

            compareDates();

            /*sharedPreferencesDiscover = mContext.getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
            name = sharedPreferencesDiscover.getString("vendor_discover_name","hi");
            holder.discover_company_name.setText(name);*/

           // holder.discover_company_name.setText(vendor_name_discover);
            System.out.println("vendor name inside-----" + position + "------" + vendor_name_discover);
           // Toast.makeText(mContext, "vendor name discover-----"+vendor_name_discover, Toast.LENGTH_LONG).show();


           /* holder.discover_company_name.setText(vendor_name_discover);
            Toast.makeText(mContext, "vendor name discover-----"+vendor_name_discover, Toast.LENGTH_LONG).show();*/


            holder.linear_layout_discoverTab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                    sharedPreferencesDiscoverAdapter = mContext.getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
                    //retrieving data of shared preferences
                    String merch_id_registered = sharedPreferencesDiscoverAdapter.getString("registeredMerchantIdKey","hi");

                   // fetchVendorNamesDiscover();
                    System.out.println("registeredMerchantId in adapter-----" + position + "------" + merch_id_registered);
                    System.out.println("MerchantId in adapter-----" + position + "------" + list.getVendorUuid());
                    System.out.println("flagRegistered in adapter outside-----" + position + "------" + flagRegistered);
                    if(merch_id_registered.equals(list.getVendorUuid()))
                    {
                        flagRegistered = 1;
                        System.out.println("flagRegistered in adapter-----" + position + "------" + flagRegistered);
                    }
                    else
                    {
                        flagRegistered = 0;
                    }
                    SharedPreferences.Editor vendor_name = sharedPreferencesDiscoverAdapter.edit();
                    vendor_name.putString("vendor_discover_name", vendor_name_discover);
                    vendor_name.commit();

                    points = String.valueOf(list.getTotalPoints());
                    img = list.getImageLink();
                    //vendor_name
                    offer_name = list.getName();
                    summary = list.getSummary();

                    Intent i = new Intent(mContext, DiscoverDescriptionActivity.class);
                    mContext.startActivity(i);
                }
            });

            //System.out.println("vendor_id_profile_discover_adapter-----"+position+"------"+vendor_id_profile_discover_adapter);

         // isRegistered(holder,position);

        }

    }

    @Override
    public int getItemCount() {
        return discoverDataList.size();
    }


    public void fetchVendorNamesDiscover() {


        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);

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


                    for(int i=0;i<response.body().getData().size();i++)
                    {
                        vendor_id_discover = response.body().getData().get(i).getOwnerUuid();
                        if(vendor_id_discover.toString().equals(vendor_uuid))
                        {
                            vendor_name_discover = response.body().getData().get(i).getOwnerName();
                            System.out.println("vendor_name----------" +i+"-----------" + vendor_name_discover);

                            }
                        else
                        {
                            System.out.println("elsev part of display redeem----------"+i);
                        }
                    }


                    // startActivity(new Intent(getApplication(),ProfileActivity.class));

                    Log.i(TAG, "Success of add with  body, valid membership----" + response);
                   // Toast.makeText(mContext, "Success register+++++++++", Toast.LENGTH_LONG).show();

                }

                else
                {
                    Log.i(TAG, "Not submitted----" + response);
                    //Toast.makeText(mContext, "Unsuccess register+++++++++", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<GetAllVendors> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to register API.");
                //Toast.makeText(mContext, "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    public void displayRegisteredVendor() {


        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
        // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        sharedPreferencesDiscover = mContext.getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
       String user_id_ = sharedPreferencesDiscover.getString("user_unique_id","hi");

        Call<GetRegisteredVendors> call1 = apiInterface.getRegisteredCustomers(user_id_);

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
                    rewardsAdapter = new RegisteredVendorRewardsAdapter(mContext, response.body().getData());
                    RecyclerView.LayoutManager subLayoutManager =
                            new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);

                    int size = rewardsPojoList.size();
                    System.out.println("SIze----------" + size);

                    for(int i = 0 ;i<size;i++) {
                        registeredMerchantId = response.body().getData().get(i).getOwnerUuid();
                        System.out.println("Value of vendor registeredMerchantId uuid-----------" + registeredMerchantId);
                        try {
                            SharedPreferences.Editor registerd_vendor_id_editor = mContext.getSharedPreferences(SignUpActivity.mypreferenceLogin, MODE_PRIVATE).edit();
                            registerd_vendor_id_editor.putString("registeredMerchantIdKey", registeredMerchantId);
                            registerd_vendor_id_editor.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                   /* recyle_rewards.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
                    recyle_rewards.setLayoutManager(subLayoutManager);

                    recyle_rewards.setItemAnimator(new DefaultItemAnimator());
                    recyle_rewards.setAdapter(rewardsAdapter);*/

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


  /*  public void registerToRewardsProgram() {

        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
        // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        //Call<LoginCustomerOutput> call1 =  apiInterface.customerLogin("abc","123");

        RegisterToRewardsProgramInput rewardsProgramInput = new RegisterToRewardsProgramInput();

       *//* sharedPreferencesDiscoverAdapter = mContext.getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
        user_id_profile_discover_adapter = sharedPreferencesDiscoverAdapter.getString("user_unique_id","hi");

        Toast.makeText(mContext, "User id:" + user_id_profile_discover_adapter , Toast.LENGTH_SHORT).show();

        System.out.println("userId======"+ user_id_profile_discover_adapter);*//*

        sharedPreferencesDiscoverAdapter = mContext.getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
         String vendor_id_profile_discover_adapter = sharedPreferencesDiscoverAdapter.getString("vendor_id","hiii");

        rewardsProgramInput.setUserUuid(user_id_profile_discover_adapter);
        rewardsProgramInput.setVendorUuid(v_uuid);
        rewardsProgramInput.setRegisteredVia("mobile");


        System.out.println("userId======"+ user_id_profile_discover_adapter);
        System.out.println("vendorId======"+v_uuid);


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
*/
   // public void isRegistered(final DiscoverAdapter.MyViewHolder holder,final int positionn) {

     public boolean isRegistered(final MyViewHolder holder,  final int position) {

   //  public void isRegistered() {

        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
        // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

        sharedPreferencesDiscoverAdapter = mContext.getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
         //retrieving data of shared preferences
         final String vendor_id_profile_discover_adapter = sharedPreferencesDiscoverAdapter.getString("vendor_id","hiii");
         System.out.println("vendor_id_profile_discover_adapter in api call---------" + vendor_id_profile_discover_adapter);

        Call<IsCustomerRegistered> callLogin =  apiInterface.isCustomerRegistered(user_id_profile_discover_adapter,vendor_id_profile_discover_adapter);


        System.out.println("callll====="+callLogin);

        callLogin.enqueue(new Callback<IsCustomerRegistered>() {


            @Override
            public void onResponse(Call<IsCustomerRegistered> call, Response<IsCustomerRegistered> response) {


                try {

                    if(response.code()==200 && response.body().getData()==null) {

                   // if(TextUtils.isEmpty(value)) {

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
                                System.out.println("Position---------------"+position);

                                discoverDataSam = discoverDataList.get(position);
                                v_uuid = discoverDataSam.getUuid();
                               // final String vendor_id_profile_discover_adapter = sharedPreferencesDiscoverAdapter.getString("vendor_id","hiii");
                                System.out.println("vendor_id_profile_discover_adapter in api call---------" + v_uuid);
                                //registerToRewardsProgram();
                            }
                        });


                        /*Intent i = new Intent(getApplication(), DiscoverAddProgramsActivity.class);
                        startActivity(i);*/
                        //  DiscoverFragment();

                        Log.i(TAG, " successful" + response);
                    }
                    //else if((!TextUtils.isEmpty(value)))
                    else if((response.code()==200||response.code()==300) && response.body().getData()!=null)
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
                        Log.i(TAG, "else part " + response);
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
         return false;
     }

    void dateConverter() {
        DateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

        long milliSeconds= Long.parseLong(end_date);
        System.out.println(milliSeconds);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        end_time_reward_date = formatter.format(calendar.getTime());
        System.out.println("Date----"+end_time_reward_date);
    }

    void compareDates()
    {
        //current date
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date = new SimpleDateFormat("dd MMM yyyy").format(new Date());
        System.out.println("Current date-------"+date);

        try {
            //comparing
            if (!sdf.parse(date).after(sdf.parse(end_time_reward_date))) {
                Toast.makeText(mContext, "true", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(mContext, "False", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
