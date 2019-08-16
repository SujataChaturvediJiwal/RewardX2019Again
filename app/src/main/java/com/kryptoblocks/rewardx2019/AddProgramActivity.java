package com.kryptoblocks.rewardx2019;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.kryptoblocks.rewardx2019.adapter.NewAdapter;
import com.kryptoblocks.rewardx2019.adapter.RegisteredVendorRewardsAdapter;
//import com.kryptoblocks.rewardx2019.adapter.VendorNamesListAdapter;
import com.kryptoblocks.rewardx2019.apiInterfaces.ApiInterface;
import com.kryptoblocks.rewardx2019.fragments.ProfileFragment;
import com.kryptoblocks.rewardx2019.network.ApiClient;
import com.kryptoblocks.rewardx2019.pojo.GetAllVendors;
import com.kryptoblocks.rewardx2019.pojo.GetAllVendorsData;
import com.kryptoblocks.rewardx2019.pojo.GetRegisteredVendors;
import com.kryptoblocks.rewardx2019.pojo.IsCustomerMembershipValid;
import com.kryptoblocks.rewardx2019.pojo.RegisterToRewardsProgramInput;
import com.kryptoblocks.rewardx2019.pojo.RegisterToRewardsProgramOutput;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.kryptoblocks.rewardx2019.TranslucentDiscoverActivity.flag_join;
import static com.kryptoblocks.rewardx2019.adapter.DiscoverAdapter.vendor_name_discover;
import static com.kryptoblocks.rewardx2019.adapter.DiscoverAdapter.vendor_uuid;


//import static com.kryptoblocks.rewardx2019.adapter.VendorNamesListAdapter.vendor_business_uuid;

public class AddProgramActivity extends AppCompatActivity {


    Button save_add_program;
    CheckBox combine_add_checkbox;
   // public EditText vendor_membership_numbers;
    ApiInterface apiInterface;
    String membership_id;
    static String vendor_id_add_program;
    ArrayList<GetAllVendors> vendorData;
    //VendorNamesListAdapter vendorNamesListAdapter;
    ImageView back_arrow_add_prgram;

    public static final String mypreferenceLogin = "mypref";
    SharedPreferences sharedPreferencesAddProgram;
    ListView listViewOfVendorNames;
    public static EditText business_vendor_name,vendor_membership_numbers ;
    View view;
    NewAdapter newAdapter;
    String local_vendor_id_var;
    public static String vendor_id;
    static int join_var_flag=0;

    private void ProfileFragment() {

        ProfileFragment profileFragment = new ProfileFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, profileFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_program);

        save_add_program = findViewById(R.id.save_add_program_button);
        //combine_add_checkbox = findViewById(R.id.checkbox_combineAdd_rewards);
        business_vendor_name = findViewById(R.id.vendor_business_name);
        vendor_membership_numbers = findViewById(R.id.vendor_membership_number);
        listViewOfVendorNames = findViewById(R.id.list_view_vendor_names);
        back_arrow_add_prgram = findViewById(R.id.add_program_back_arrow);

        fetchAllVendorNames();



/*
            local_vendor_id_var = vendor_id;
            System.out.println("vendor_id in profile-----"+vendor_id);
            System.out.println("vendor_id in profile-----"+local_vendor_id_var);
            fetchAllVendorNames();*/

        System.out.println("flag join value else if in profile-----"+flag_join);
            //local_vendor_id_var = vendor_id;


        save_add_program.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // isCustomerMembershipValid();
               if(join_var_flag == 1)
               {

                   System.out.println("flag join value else if in profile-----"+join_var_flag);
                   System.out.println("Vendor business id -aga inside if------------"+local_vendor_id_var);
                   isCustomerMembershipValid();
               }
               else if(join_var_flag ==0)
               {

                   System.out.println("flag join value else if in profile-----"+join_var_flag);
                   System.out.println("Vendor business id -aga inside if------------"+local_vendor_id_var);
                   isCustomerMembershipValid();
               }
               // registerToRewardsProgram();

            }
        });

        back_arrow_add_prgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // finish();
                /*Intent i = new Intent(AddProgramActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);*/
               finish();
               // new ProfileFragment();
            }
        });

       /* combine_add_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((combine_add_checkbox).isChecked()){
                    System.out.println("Checked");
                    isCustomerMembershipValid();
                } else {
                    System.out.println("Un-Checked");
                    isCustomerMembershipValid();
                }
            }
        });*/

        business_vendor_name.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                //if(cs.length()>3) {
                    //listViewOfVendorNames.setVisibility(VISIBLE);
                    //fetchAllVendorNames();
                try {
                    if ((TextUtils.isEmpty(cs)) && listViewOfVendorNames.getVisibility() != view.INVISIBLE) {

                        //AddProgramActivity.this.vendorNamesListAdapter.getFilter().filter(cs);

                        newAdapter.filter("");
                        //vendorNamesListAdapter.getFilter().filter("");
                        listViewOfVendorNames.clearTextFilter();
                        listViewOfVendorNames.setVisibility(INVISIBLE);
                    } else {
                        newAdapter.filter(cs.toString());
                       // AddProgramActivity.this.vendorNamesListAdapter.getFilter().filter(cs);
                        listViewOfVendorNames.setVisibility(VISIBLE);
                    }
                }
                catch (Exception e)
                {
                    System.out.println("Exception in filter---"+e);
                }
               // fetchAllVendorNames();
                //}
               /* else
                {
                    listViewOfVendorNames.setVisibility(INVISIBLE);
                }*/
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                /*AddProgramActivity.this.vendorNamesListAdapter.getFilter().filter(arg0);
                fetchAllVendorNames();*/
            }
        });

        checkTrial();


        listViewOfVendorNames.setVisibility(INVISIBLE);
    }

    public void isCustomerMembershipValid() {


        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
        // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

        membership_id = vendor_membership_numbers.getText().toString();
        vendor_id_add_program = business_vendor_name.getText().toString();




        //System.out.println("Vendor business id -------------"+vendor_id);
        //System.out.print("Vendor business id -aga outside-----------"+local_vendor_id_var);

        Call<IsCustomerMembershipValid> call1 = apiInterface.isCustomerMembershipValid(membership_id,local_vendor_id_var);

        System.out.println("callll====="+call1);

        call1.enqueue(new Callback<IsCustomerMembershipValid>() {


            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<IsCustomerMembershipValid> call, Response<IsCustomerMembershipValid> response) {

                if((response.code()== 200) && (response.body().getData().getError()==null)) {
                    // if (passwordRegister == rePassword_register.getText().toString()) {
                    int statusCode = response.code();

                    System.out.println("Code" + statusCode);
                    System.out.println("body" + response.body().getData());

                    registerToRewardsProgram();


                    Log.i(TAG, "Success of add with  body, valid membership----" + response);
                    Toast.makeText(getApplication(), "Success register+++++++++", Toast.LENGTH_LONG).show();

                }

                else if((response.code() == 200) && (response.body().getData().getError()!=null))
                {
                    vendor_membership_numbers.setError("Invalid id");
                    vendor_membership_numbers.requestFocus();
                    Log.i(TAG, "already registered with this membership id----" + response);

                }
                else if(response.code() == 500)
                {
                   // System.out.println("body" + response.body().getMessage());
                    vendor_membership_numbers.setError("Wrong id");
                    vendor_membership_numbers.requestFocus();
                    vendor_membership_numbers.setText("");
                    //vendor_membership_numbers.onTouchEvent(vendor_membership_numbers.setText(""));
                }
                else
                {
                    Log.i(TAG, "Not submitted----" + response);
                  //  Toast.makeText(getApplication(), "Unsuccess register+++++++++", Toast.LENGTH_LONG).show();
                }
            }



            @Override
            public void onFailure(Call<IsCustomerMembershipValid> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to register API.");
              //  Toast.makeText(getApplication(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    public void registerToRewardsProgram() {

        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);


        RegisterToRewardsProgramInput rewardsProgramInput = new RegisterToRewardsProgramInput();

        sharedPreferencesAddProgram = getSharedPreferences(mypreferenceLogin, Context.MODE_PRIVATE);
        //retrieving data of shared preferences
        String user_id_add = sharedPreferencesAddProgram.getString("user_unique_id","hiii");

        rewardsProgramInput.setUserUuid(user_id_add);
       /* if(flag_join == 1)
        {
            rewardsProgramInput.setVendorUuid(vendor_uuid);
            //flag_join = 0;
        }
        else {
            rewardsProgramInput.setVendorUuid(vendor_id);
        }*/
        rewardsProgramInput.setVendorUuid(local_vendor_id_var);
        rewardsProgramInput.setMembershipUuid(membership_id);


        System.out.println("userId======"+ user_id_add);
        System.out.println("vendorId======"+vendor_id);


        Call<RegisterToRewardsProgramOutput> callLogin =  apiInterface.registerCustomerWithVendor(rewardsProgramInput);


        System.out.println("callll====="+callLogin);

        callLogin.enqueue(new Callback<RegisterToRewardsProgramOutput>() {


            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<RegisterToRewardsProgramOutput> call, Response<RegisterToRewardsProgramOutput> response) {


                try {

                    if (response.code()==200  && response.body().getData().getError()==null) {

                        int statusCode = response.code();

                        System.out.println("Code" + statusCode);
                        System.out.println("body" + response.body().toString());

                        //startActivity(new Intent(getApplication(),ProfileActivity.class));
                       Intent i = new Intent(AddProgramActivity.this,MainActivity.class);

                       startActivity(i);
                        //ProfileFragment();


                       // onBackPressed();



                        Log.i(TAG, "login successful" + response);
                    }
                    else if(response.code()==500 && response.body().getData().getError()!=null)
                    {
                       // Toast.makeText(getApplication(), "Already registered +++++++++", Toast.LENGTH_LONG).show();
                        business_vendor_name.setError("Already registered with this vendor");
                    }
                    else if(response.code()==500)
                    {
                        //Toast.makeText(getApplication(), "Already registered +++++++++", Toast.LENGTH_LONG).show();
                        vendor_membership_numbers.setError("Membership Uuid is already registered");

                    }
                    else {
                        Log.i(TAG, "post not submitted to API." + response.errorBody());
                        Toast.makeText(getApplication(), "Unsuccess login+++++++++", Toast.LENGTH_LONG).show();
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
                //Toast.makeText(getApplication(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    public void fetchAllVendorNames() {


        apiInterface =  ApiClient.getInstance().getClient().create(ApiInterface.class);
        // apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


        Call<GetAllVendors> call1 = apiInterface.getAllVendors();

        System.out.println("callll====="+call1);

        call1.enqueue(new Callback<GetAllVendors>() {


            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<GetAllVendors> call, final Response<GetAllVendors> response) {

                if(response.code()== 200) {
                    // if (passwordRegister == rePassword_register.getText().toString()) {
                    int statusCode = response.code();

                    System.out.println("Code" + statusCode);
                    System.out.println("body----" + response.body().getData());

                   // vendorData= new ArrayList<>();

                    /*vendorNamesListAdapter= new VendorNamesListAdapter(getApplicationContext(), (ArrayList<GetAllVendorsData>) response.body().getData());
                    listViewOfVendorNames.setAdapter(vendorNamesListAdapter);*/

                    newAdapter= new NewAdapter(getApplicationContext(), response.body().getData());
                    listViewOfVendorNames.setAdapter(newAdapter);

                    listViewOfVendorNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            // the position of the item clicked will come in as the 3rd parameter of the onItemClick callback
                            // which is 'position'. You can use the value to do whatever you want
                            vendor_id = response.body().getData().get(position).getVendorUuid();
                            local_vendor_id_var = vendor_id;
                            business_vendor_name.setText(response.body().getData().get(position).getVendorName());
                            System.out.println("vendor id main-----------" + vendor_id);
                            Toast.makeText(getApplication(), "clicked on item+++++++++", Toast.LENGTH_LONG).show();


                        }
                    });

                   // startActivity(new Intent(getApplication(),ProfileActivity.class));

                    Log.i(TAG, "Success of add with  body, valid membership----" + response);
                   // Toast.makeText(getApplication(), "Success register+++++++++", Toast.LENGTH_LONG).show();

                }

                else
                {
                    Log.i(TAG, "Not submitted----" + response);
                    //Toast.makeText(getApplication(), "Unsuccess register+++++++++", Toast.LENGTH_LONG).show();
                }
            }



            @Override
            public void onFailure(Call<GetAllVendors> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to register API.");
                //Toast.makeText(getApplication(), "Failed+++++++++", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    public void checkTrial()
    {
        if(flag_join == 1)
        {
            business_vendor_name.setText(vendor_name_discover);
            //vendor_uuid
            business_vendor_name.setFocusable(false);
            local_vendor_id_var = vendor_uuid;
            System.out.println("flag join value in profile-----"+flag_join);
            System.out.println("Vendor business id inside if------------"+local_vendor_id_var);

            //isCustomerMembershipValid();
            flag_join =0;
            join_var_flag = 1;
        }
        else if(flag_join == 0)
        {
           // local_vendor_id_var = vendor_id;
            System.out.println("value-----"+vendor_id);
            System.out.println("value outside------------"+local_vendor_id_var);
            join_var_flag = 0;
            //isCustomerMembershipValid();
        }
    }


}
